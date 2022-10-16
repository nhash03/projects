package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResidence {
    Residence residence;
    Buyer b1;
    Buyer b2;
    Seller s1;
    Seller s2;
    Item i1;
    Item i2;

    @BeforeEach
    public void setup() {
        residence = new Residence("Walter Gage");
        b1 = new Buyer("Negin", residence, "nhash03", "123abc");
        b2 = new Buyer("Elina", residence, "eligh", "a1b2");
        s1 = new Seller("Shadan", residence, "shad", "123");
        s2 = new Seller("Ava", residence, "avahmadi", "abc-1");
        i1 = new Item("Milk", 2.50, residence, s1);
        i2 = new Item("Yoghurt", 3.778, residence, s2);
    }

    @Test
    public void testConstructors() {
        assertEquals("Walter Gage", residence.getName());
        assertEquals(0, residence.getBuyers().size());
        assertEquals(0,residence.getSellers().size());
        assertEquals(0, residence.getItems().size());

        residence.addNewItem(i1);
        residence.addNewItem(i2);
        assertEquals(2, residence.getItems().size());
        assertTrue(residence.getItems().contains(i1));
        assertTrue(residence.getItems().contains(i2));

        residence.addNewBuyer(b1);
        residence.addNewBuyer(b2);
        assertEquals(2, residence.getBuyers().size());
        assertTrue(residence.getBuyers().contains(b2));
        assertTrue(residence.getBuyers().contains(b1));

        residence.addNewSeller(s1);
        residence.addNewSeller(s2);
        assertEquals(2, residence.getSellers().size());
        assertTrue(residence.getSellers().contains(s1));
        assertTrue(residence.getSellers().contains(s2));
    }

    @Test
    public void testAddNewSeller() {
        residence.addNewSeller(s1);
        assertTrue(residence.getSellers().contains(s1));
        assertEquals(1, residence.getSellers().size());

        residence.addNewSeller(s2);
        assertTrue(residence.getSellers().contains(s1));
        assertTrue(residence.getSellers().contains(s2));
        assertEquals(2, residence.getSellers().size());

        residence.addNewSeller(s1);
        assertTrue(residence.getSellers().contains(s1));
        assertTrue(residence.getSellers().contains(s2));
        assertEquals(2, residence.getSellers().size());
    }

    @Test
    public void testAddNewBuyer() {
        residence.addNewBuyer(b1);
        assertTrue(residence.getBuyers().contains(b1));
        assertEquals(1, residence.getBuyers().size());

        residence.addNewBuyer(b2);
        assertTrue(residence.getBuyers().contains(b1));
        assertTrue(residence.getBuyers().contains(b2));
        assertEquals(2, residence.getBuyers().size());

        residence.addNewBuyer(b1);
        assertTrue(residence.getBuyers().contains(b1));
        assertTrue(residence.getBuyers().contains(b2));
        assertEquals(2, residence.getBuyers().size());
    }

    @Test
    public void testAddNewItem() {
        residence.addNewItem(i1);
        assertTrue(residence.getItems().contains(i1));
        assertEquals(1, residence.getItems().size());

        residence.addNewItem(i1);
        assertTrue(residence.getItems().contains(i1));
        assertEquals(2, residence.getItems().size());

        residence.addNewItem(i2);
        assertTrue(residence.getItems().contains(i1));
        assertTrue(residence.getItems().contains(i2));
        assertEquals(3, residence.getItems().size());
    }

    @Test
    public void testHandleAddingObject() {
        residence.addNewBuyer(b1);
        assertEquals("Such a buyer has already existed in the residence",
                residence.handleAddingObject(b1, residence.getBuyers(), "buyer"));

        assertEquals("The buyer successfully added to the residence's list",
                residence.handleAddingObject(b2, residence.getBuyers(), "buyer"));
    }
}
