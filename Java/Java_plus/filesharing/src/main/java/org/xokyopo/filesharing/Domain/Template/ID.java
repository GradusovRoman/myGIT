package org.xokyopo.filesharing.Domain.Template;

/**
 * Ссылка на FilePathID В DataBase
 */

public class ID {
    private final String id;

    public ID(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
