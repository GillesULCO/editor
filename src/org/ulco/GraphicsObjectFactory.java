package org.ulco;
import java.lang.reflect.*;

public class GraphicsObjectFactory {
    public  static GraphicsObject createObjectFromJSON(String type, String string){
        try {
            String className = "org.ulco." + type.toUpperCase().charAt(0) + type.substring(1);
            Class<?> c = Class.forName(className);
            Constructor<?> constructor = c.getDeclaredConstructor(String.class);
            return (GraphicsObject) constructor.newInstance(string);
        }catch(InvocationTargetException | ClassNotFoundException | InstantiationException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException e){
            return null;
        }
    }
}
