package org.xokyopo.massiveandsorting.treegenerator;

import org.xokyopo.massiveandsorting.treegenerator.interfaces.XmlTreeExportImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyTreeBuilder implements XmlTreeExportImpl {

    @Override
    public String getXMLTree(String[] nodes) {
        return this.getXmlTree(
                this.getTreeStructure(
                        this.creatCompanyList(nodes)
                )
        );
    }

    private List<Company> creatCompanyList(String[] nodes) {
        List<Company> companyList = new ArrayList<>();
        Arrays.asList(nodes).forEach(node->{
            companyList.add(this.creatCompanyByNode(node));
        });
        return companyList;
    }

    private Company creatCompanyByNode(String node) {
        return new Company(this.getCompanyId(node), this.getParentId(node));
    }

    //TODO сделать проверку.
    private int getParentId(String node) {
        return Integer.parseInt(node.split( " ")[1]);
    }

    private int getCompanyId(String node) {
        return Integer.parseInt(node.split(" ")[0]);
    }

    private Company getTreeStructure(List<Company> companyList) {
        Company rootCompany = new Company(0,-1);

        companyList.listIterator().forEachRemaining(company -> {
            company.getChildList().addAll(this.getChildList(companyList, company.getId()));
            if (company.getParentId() == 0) rootCompany.getChildList().add(company);
        });

        return rootCompany;
    }

    private List<Company> getChildList(List<Company> companyList, int parentId) {
        List<Company> childList = new ArrayList<>();
        companyList.listIterator().forEachRemaining(company -> {
            if (company.getParentId() == parentId) childList.add(company);
        });
        return childList;
    }

    private String getXmlTree(Company company) {

        StringBuffer outerString = new StringBuffer();

        outerString.append("<").append(company.getId());

        if (company.getChildList().isEmpty()) {
            outerString.append("/>");
        } else {
            outerString.append(">");
            company.getChildList().forEach(child-> {
                outerString.append(this.getXmlTree(child));
            });
            outerString.append("</").append(company.getId()).append(">");
        }

        return outerString.toString();
    }
}
