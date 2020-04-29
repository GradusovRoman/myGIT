package org.xokyopo.wsdlclient;

import gb.xokyopo.servlets.ui.remote.soap.SOAPService;
import gb.xokyopo.servlets.ui.remote.soap.SOAPServiceService;

import java.net.MalformedURLException;
import java.net.URL;

public class SOAPClient {
    public static void main(String[] args) throws MalformedURLException {
        URL wsdlUrl = new URL("http://localhost:8080/webAppJSF/SOAPService?WSDL");
        SOAPServiceService soapServiceService = new SOAPServiceService(wsdlUrl);
        SOAPService soapService = soapServiceService.getSOAPServicePort();
        soapService.getAllProducts().forEach(productRep -> {
            System.out.println(
                    String.format("id: %s\nname:%s\nprice:%s\ncategory:%s\n",
                    productRep.getId(), productRep.getName(), productRep.getPrice(), productRep.getCategoryRep().getName())
            );
        });
    }
}
