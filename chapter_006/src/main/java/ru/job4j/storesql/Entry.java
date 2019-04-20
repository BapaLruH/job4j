package ru.job4j.storesql;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entry {
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
