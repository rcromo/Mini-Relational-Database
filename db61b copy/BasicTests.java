package db61b;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Stack;

/** Test basic functionality of:
  * 1) The Row Class
  * 2) The Table Class
  * 3) The TableIterator Class
  * @author Roberto Romo
  */
public class BasicTests {
    @Test
    public void testRow() {
        Row r1 = new Row(new String[] {"The", "length", "this", "is", "six"});
        assertEquals(5, r1.size());
        assertEquals("this", r1.get(2));

        List<Column> c1 = new Stack<>();

        Literal lt1 = new Literal("Cal");
        Literal lt2 = new Literal("if");
        Literal lt3 = new Literal("for");
        Literal lt4 = new Literal("nia");

        c1.add(lt1);
        c1.add(lt2);
        c1.add(lt3);
        c1.add(lt4);

        Row r2 = new Row(c1);
        assertEquals(4, r2.size());
        assertEquals("for", r2.get(2));

        Row r3 = new Row(new String[] {"Big", "Game"});
        assertEquals(2, r3.size());
        assertEquals("Big", r3.get(0));

        Row r4 = r3.make(c1);
        assertEquals(4, r4.size());
        assertEquals("nia", r4.get(3));

        assertTrue(r4.equals(r2));

    }

    @Test
    public void testTableandTableIterator1() {
        String[] s1 = new String[]{"Player", "Country", "Number"};
        Table t1 = new Table("Test Table", s1);
        Row r1 = new Row(new String[] {"Kaka", "Brazil", "10"});
        Row r2 = new Row(new String[] {"Pele", "Brazil", "10"});

        assertEquals(3, t1.numColumns());
        assertEquals("Test Table", t1.name());
        assertEquals("Country", t1.title(1));
        assertEquals(1, t1.columnIndex("Country"));
        assertEquals(0, t1.size());


        assertTrue(t1.add(r1));
        assertFalse(t1.add(r1));
        assertTrue(t1.add(r2));
        assertEquals(2, t1.size());
        assertEquals(3, r1.size());


        List<String> ls = new Stack<>();
        ls.add("Club");
        ls.add("Position");
        Table t2 = new Table("Table Test", ls);

        assertEquals(1, t2.columnIndex("Position"));
        assertEquals("Position", t2.title(1));

        assertEquals(0, t2.size());

        TableIterator tit = new TableIterator(t1);
        assertEquals(1, tit.columnIndex("Country"));
        assertEquals("Kaka", tit.value(0));
        assertTrue(tit.hasRow());
        tit.next();
        assertEquals("Pele", tit.value(0));
        assertEquals("10", tit.value(2));
        assertTrue(tit.hasRow());
        tit.reset();
        assertEquals("Kaka", tit.value(0));
        tit.next();
        assertEquals("Pele", tit.value(0));
        assertEquals(2, tit.columnIndex("Number"));
    }

    public static void main(String[]  args) {
        System.exit(ucb.junit.textui.runClasses(BasicTests.class));
    }
}

