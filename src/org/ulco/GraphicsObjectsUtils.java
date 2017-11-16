package org.ulco;

public class GraphicsObjectsUtils {

    public static GraphicsObjects select(Layer layer, Point pt, double distance){
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer.getList()) {
            object.addIfClosedObject(list, pt, distance);
        }
        return list;
    }

    public static GraphicsObjects select(Document document, Point pt, double distance){
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : document.getLayers()) {
            list.addAll(GraphicsObjectsUtils.select(layer, pt, distance));
        }
        return list;
    }
}
