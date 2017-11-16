package org.ulco;

import java.util.Vector;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID = ID.getInstance().getNextId();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    public boolean isGroup() {
        return false;
    }

    public int size(){
        return 1;
    }

    public void addIfClosedObject (Vector<GraphicsObject> v, Point pt, double distance){
        if(!isGroup() && isClosed(pt, distance)){
            v.add(this);
        }
    }

    private int m_ID;
}
