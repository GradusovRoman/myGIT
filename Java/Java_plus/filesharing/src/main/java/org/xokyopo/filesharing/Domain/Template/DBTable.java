package org.xokyopo.filesharing.Domain.Template;

/*
Описывает структуру базы данных

 */

public enum DBTable {
    TABLENAME("TABLENAME"), IDFIELD("idFieldName"), VALUE("valueFieldName"), DATETIME("DateTimeFieldName");
    final String name;

    DBTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
