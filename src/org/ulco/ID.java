package org.ulco;

public class ID {
    private static ID instance = null;
    static public int id;

    private ID(){
        this.id = 0;
    }

    public static ID getInstance() {
        if(instance == null)
            instance = new ID();
        return instance;
    }

    public int getNextId() {
        return ++this.id;
    }

    public int getId() {
        return this.id;
    }
}