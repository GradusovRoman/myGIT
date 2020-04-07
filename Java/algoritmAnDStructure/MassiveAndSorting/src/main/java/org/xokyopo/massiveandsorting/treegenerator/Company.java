package org.xokyopo.massiveandsorting.treegenerator;

/*
Company {
Integer id;
Integer parentId;
List<Company> list;
}
 */

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Integer id;
    private Integer parentId;
    private List<Company> list;

    public Company(Integer id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
        this.list = new ArrayList<>();
    }

    public List<Company> getChildList() {
        return this.list;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }
}
