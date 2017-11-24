package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject {

    public Group() {
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().getNextId();
    }

    public Group(String json) {
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        StringParseUtils.parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2), m_objectList);
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public void add(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {
            g.add(o);
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        for (GraphicsObject o : m_objectList) {
            o.move(delta);
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = StringParseUtils.searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;
        for(GraphicsObject g : m_objectList ){
            size += g.size();
        }
        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";
        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(!element.isGroup()) {
                str += element.toJson();
                if (i < m_objectList.size() - 1 && m_objectList.size() > 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element.isGroup()) {
                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject nextElement = null;
            GraphicsObject element = m_objectList.elementAt(i);
            if(i < m_objectList.size() - 1){
                nextElement = m_objectList.elementAt(i+1);
            }
            if(!element.isGroup()) {
                str += element.toString();
                if (i < m_objectList.size() - 1 && !nextElement.isGroup()) {
                    str += ", ";
                }
            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element.isGroup()) {
                str += element.toString();
            }
        }
        return str + "]]";
    }

    public boolean isClosed(Point pt, double distance){
        return true;
    }

    public boolean isGroup() {
        return true;
    }

    public void addIfClosedObject (Vector<GraphicsObject> v, Point pt, double distance){
        for(GraphicsObject o : m_objectList){
            o.addIfClosedObject(v, pt, distance);
        }
    }

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
