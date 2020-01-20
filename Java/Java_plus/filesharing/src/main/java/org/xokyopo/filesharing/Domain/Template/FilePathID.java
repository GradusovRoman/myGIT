package org.xokyopo.filesharing.Domain.Template;

/**
 *Ссылка на данные Данные из Repository
 */

public class FilePathID {
    private final String ID;

    public FilePathID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }
}
