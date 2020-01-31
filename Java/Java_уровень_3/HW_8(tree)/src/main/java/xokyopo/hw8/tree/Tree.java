package xokyopo.hw8.tree;

import xokyopo.hw8.tree.template.MetaObject;

import java.util.*;

public class Tree {
    public List<MetaObject> treeList= new ArrayList<>();

    public Tree(List<Integer[]> list) {
        this.creatingTree(
                this.creatingMetaObjectList(list)
        );
    }

    public List<MetaObject> getMetaObjectList() {
        return this.treeList;
    }

    private Map<Integer, MetaObject> creatingMetaObjectList(List<Integer[]> arr) {
        Map<Integer, MetaObject> metaObjectMap = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            int countNumber = arr.get(i)[0];
            int parentNumber = arr.get(i)[1];

            MetaObject metaObject = new MetaObject(countNumber, parentNumber);
            metaObjectMap.put(countNumber, metaObject);

            if (parentNumber == 0) this.treeList.add(metaObject);
        }

        return metaObjectMap;
    }

    private void creatingTree(Map<Integer, MetaObject> list) {
        list.forEach((number, object) -> {
            MetaObject parentObject = list.get(object.getPARENT_ID());
            if (parentObject != null) parentObject.add(object);
        });
    }
}
