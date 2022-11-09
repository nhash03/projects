package persistence;

import model.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    private Item i1;
    private Item i2;
    private Seller s1;
    private Seller s2;
    private Seller s3;
    private Seller s4;
    private Buyer b1;
    private Buyer b2;
    private Residences residences1;
    private Residence wg;
    private Residence tp;
    private Residence ex;
    private Residence pv;

    @BeforeEach
    void setup() {
        residences1 = new Residences();
        wg = residences1.getResidences().get(0);
        ex = residences1.getResidences().get(1);
        tp = residences1.getResidences().get(2);
        pv = residences1.getResidences().get(3);
        s1 = new Seller("s1", wg, "abc", "123");
        s2 = new Seller("s2", ex, "def", "456");
        s3 = new Seller("s3", tp, "ghi", "789");
        s4 = new Seller("s4", pv, "jkl", "012");
        i1 = new Item("i1", 1.5, wg, s1.getId());
        i2 = new Item("i2", 3.75, pv, s4.getId());
        b1 = new Buyer("b1", wg, "aaa", "111");
        b2 = new Buyer("b2", tp, "bbb", "222");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Residences residences = new Residences();
            JsonWriter writer = new JsonWriter("./data1/rss.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyResidences() {
        try {
            Residences residences = new Residences();
            JsonWriter writer = new JsonWriter("./data/Residences.json");
            writer.open();
            writer.write(residences);
            writer.close();

            JsonReader reader = new JsonReader("./data/Residences.json");
            residences = reader.read();
            assertEquals("Walter Gage", residences.getResidences().get(0).getName());
            assertEquals("Exchange", residences.getResidences().get(1).getName());
            assertEquals("Totem Park", residences.getResidences().get(2).getName());
            assertEquals("Place Vanier", residences.getResidences().get(3).getName());
            assertTrue(residences.getResidences().get(0).isEmpty());
            assertTrue(residences.getResidences().get(1).isEmpty());
            assertTrue(residences.getResidences().get(2).isEmpty());
            assertTrue(residences.getResidences().get(3).isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralResidences() {
        try {
            wg.addNewItem(i1);
            pv.addNewItem(i2);
            b1.addItemToBuy(i1);
            b1.setBalance(10);
            b1.payForItems();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralResidences.json");
            writer.open();
            writer.write(residences1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralResidences.json");
            residences1 = reader.read();
            assertEquals("Walter Gage", residences1.getResidences().get(0).getName());
            assertEquals("Exchange", residences1.getResidences().get(1).getName());
            assertEquals("Totem Park", residences1.getResidences().get(2).getName());
            assertEquals("Place Vanier", residences1.getResidences().get(3).getName());
            assertEquals(2, wg.getItems().size());
            assertEquals(0, ex.getItems().size());
            assertEquals(1, wg.getBuyersAccounts().size());
            assertEquals(2, pv.getItems().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
