package gb.xokyopo.servlets.service.represantations;

import java.io.Serializable;

public class CategoryRep implements Serializable {
    private int id;
    private String name;
    private String description;

    public CategoryRep() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
