package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;

public class LayerTest extends TestCase {
    @Test
    public void testType() throws Exception {
        Document document = new Document();
        int oldID = ID.getInstance().getId();
        Layer layer = document.createLayer();

        layer.add(new Square(new Point(2, 8), 10));

        assertEquals(layer.getID(), oldID + 1);
        assertEquals(layer.get(0).getID(), oldID + 2);
    }

    @Test
    public void testJSON() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);

        l.add(s);
        l.add(c);
        assertEquals(l.toJson(), "{ type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } } }");
    }

    @Test
    public void testSelect() throws Exception {
        Group g = new Group();
        Square s = new Square(new Point(2, 0), 5);
        Circle c = new Circle(new Point(4, 0), 4);
        g.add(s);
        g.add(c);

        Layer l = new Layer();
        Circle c1 = new Circle(new Point(6, 0), 4);
        l.add(c1);
        l.add(g);

        assertEquals(3, GraphicsObjectsUtils.select(l, new Point(3,1), 15).size());
    }

    public void testSelect2() throws Exception {
        Group g = new Group();
        Square s = new Square(new Point(2, 0), 5);
        Circle c = new Circle(new Point(40, 60), 4);
        g.add(s);
        g.add(c);

        Layer l = new Layer();
        Circle c1 = new Circle(new Point(6, 0), 4);
        l.add(c1);
        l.add(g);

        assertEquals(2, GraphicsObjectsUtils.select(l, new Point(3,1), 8).size());
    }
}