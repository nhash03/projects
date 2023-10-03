package model;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResidences {
    Residences residences;

    @BeforeEach
    void setup() {
        residences = new Residences();
    }

    @Test
    public void testConstructors() {
        assertEquals(4, residences.getResidences().size());
        assertEquals("Walter Gage", residences.getResidences().get(0).getName());
        assertEquals("Place Vanier", residences.getResidences().get(3).getName());
    }

    @Test
    public void testToJson() {
        JSONObject jsonObject = residences.toJson();
        Iterator<String> keys = jsonObject.keys();
        List<String> keyList = new ArrayList<>();
        while (keys.hasNext()) {
            keyList.add(keys.next());
        }
        assertTrue(keyList.contains("Walter Gage"));
        assertTrue(keyList.contains("Totem Park"));
        assertTrue(keyList.contains("Place Vanier"));
        assertTrue(keyList.contains("Exchange"));
    }
}
