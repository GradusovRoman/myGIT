package gb.xokyopo.servlets.service.represantations;

import java.io.Serializable;
import java.util.List;

public class UserRep implements Serializable {
    private int id;
    private String name;
    private String pass;
    private List<GroupRep> groupRepList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<GroupRep> getGroupRepList() {
        return groupRepList;
    }

    public void setGroupRepList(List<GroupRep> groupRepList) {
        this.groupRepList = groupRepList;
    }
}
