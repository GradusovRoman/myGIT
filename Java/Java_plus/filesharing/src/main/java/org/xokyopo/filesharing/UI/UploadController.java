package org.xokyopo.filesharing.UI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xokyopo.filesharing.Domain.Function.Get;
import org.xokyopo.filesharing.Domain.Function.Save;
import org.xokyopo.filesharing.Domain.Template.ID;

import java.io.File;
import java.net.MalformedURLException;

@Controller
public class UploadController {
    private Save save;
    private Get get;

    @Autowired
    public UploadController(Save save, Get get) {
        this.save = save;
        this.get = get;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @PostMapping("/")
    public String uploadingFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String fileCode = this.save.save(file).getId();

        Logger logger = LoggerFactory.getLogger(UploadController.class);
        logger.info("message ----------->" +  "/download/" + fileCode);

        redirectAttributes.addFlashAttribute("message", "/download/" + fileCode);
        return "redirect:/";
    }

    @GetMapping("/download/{fileCode}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileCode) {
        File file = this.get.get(new ID(fileCode));
        try {
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
