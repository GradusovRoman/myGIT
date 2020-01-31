package xokyopo.hw8.tree.template;

import java.util.ArrayList;
import java.util.List;

public class MetaObject{
    private final int ID;
    private final int PARENT_ID;
    private List<MetaObject> listOfChild = null;

    public MetaObject(int ID, int PARENT_ID) {
        this.ID = ID;
        this.PARENT_ID = PARENT_ID;
    }

    public void add(MetaObject children) {
        if (this.listOfChild == null) {
            this.listOfChild = new ArrayList<>();
        }
        this.listOfChild.add(children);
    }

    public void printInfo() {
        this.printInfo(1);
    }

    protected void printInfo(int n) {
        StringBuffer msg = new StringBuffer("|");
        for (int i = 1 ; i < n; i++) {
            msg.append((i < n -1)? "\t" : "\t|");
        }
        msg.append("->");
        msg.append(ID + " (ParentID = " + this.PARENT_ID + ")");
        System.out.println(msg.toString());

        if (this.listOfChild != null) {
            this.listOfChild.forEach(child -> child.printInfo(n + 1));
        }
    }

    public List<MetaObject> getListOfChild() {
        return listOfChild;
    }

    public int getID() {
        return ID;
    }

    public int getPARENT_ID() {
        return PARENT_ID;
    }
}
