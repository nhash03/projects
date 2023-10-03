package persistence;

import model.Residence;
import model.Residences;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Residences ress = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // working properly
        }
    }

    @Test
    void testReaderEmptyResidences() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyResidences.json");
        try {
            Residences ress = reader.read();
            assertEquals("Walter Gage", ress.getResidences().get(0).getName());
            assertEquals("Exchange", ress.getResidences().get(1).getName());
            assertEquals("Totem Park", ress.getResidences().get(2).getName());
            assertEquals("Place Vanier", ress.getResidences().get(3).getName());
            assertTrue(ress.getResidences().get(0).isEmpty());
            assertTrue(ress.getResidences().get(1).isEmpty());
            assertTrue(ress.getResidences().get(2).isEmpty());
            assertTrue(ress.getResidences().get(3).isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralResidences.json");
        try {
            Residences residences = reader.read();
            Residence wg = residences.getResidences().get(0);
            Residence ex = residences.getResidences().get(1);
            Residence tp = residences.getResidences().get(2);
            Residence pv = residences.getResidences().get(3);
            assertEquals("Walter Gage", wg.getName());
            assertEquals("Exchange", residences.getResidences().get(1).getName());
            assertEquals("Totem Park", residences.getResidences().get(2).getName());
            assertEquals("Place Vanier", residences.getResidences().get(3).getName());
            assertEquals(1, wg.getItems().size());
            assertEquals(0, ex.getItems().size());
            assertEquals(1, wg.getBuyersAccounts().size());
            assertEquals(1, wg.getSellersAccounts().size());
            assertEquals(1, wg.getBuyers().size());
            assertEquals(1, wg.getSellers().size());
            assertEquals(1, pv.getItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
