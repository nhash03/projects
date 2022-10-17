package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResidence {
    Residence residence;
    Buyer buyer1;
    Buyer b2;
    Seller s1;
    Seller s2;
    Item i1;
    Item i2;

    @BeforeEach
    public void setup() {
        residence = new Residence("WG");
        buyer1 = new Buyer("Negin", residence, "nhash03", "123abc");
        b2 = new Buyer("Elina", residence, "eligh", "a1b2");
        s1 = new Seller("Shadan", residence, "shad", "123");
        s2 = new Seller("Ava", residence, "avahmadi", "abc-1");
        i1 = new Item("Milk", 2.50, residence, s1);
        i2 = new Item("Yoghurt", 3.778, residence, s2);
    }

    @Test
    public void testConstructors() {
        Residence emptyRes = new Residence("Blah blah");
        assertEquals("Blah blah", emptyRes.getName());
        assertEquals(0, emptyRes.getBuyers().size());
        assertEquals(0,emptyRes.getSellers().size());
        assertEquals(0, emptyRes.getItems().size());
        assertEquals(0, emptyRes.getBuyersAccounts().size());
        assertEquals(0, emptyRes.getSellersAccounts().size());

        residence.addNewItem(i1);
        residence.addNewItem(i2);
        assertEquals(2, residence.getItems().size());
        assertTrue(residence.getItems().contains(i1));
        assertTrue(residence.getItems().contains(i2));

        assertEquals(2, residence.getBuyers().size());
        assertTrue(residence.getBuyers().contains(b2));
        assertTrue(residence.getBuyers().contains(buyer1));
        assertEquals(2, residence.getBuyersAccounts().size());
        assertEquals("123abc", residence.getBuyersAccounts().get("nhash03"));
        assertEquals("a1b2", residence.getBuyersAccounts().get("eligh"));

        assertEquals(2, residence.getSellers().size());
        assertTrue(residence.getSellers().contains(s1));
        assertTrue(residence.getSellers().contains(s2));
        assertEquals(2, residence.getSellersAccounts().size());
        assertEquals("123", residence.getSellersAccounts().get("shad"));
        assertEquals("abc-1", residence.getSellersAccounts().get("avahmadi"));
    }

    @Test
    public void testAddNewSeller() {
        Residence newRes = new Residence("aaa");
        Seller newSell = new Seller("new", newRes, "meow", "1234");
        residence.addNewSeller(newSell);
        assertTrue(residence.getSellers().contains(newSell));
        assertEquals(3, residence.getSellers().size());
        assertEquals(3, residence.getSellersAccounts().size());
        assertEquals("1234", residence.getSellersAccounts().get("meow"));

        Seller newSell2 = new Seller("new2", newRes, "meow2", "12345");
        residence.addNewSeller(newSell2);
        assertTrue(residence.getSellers().contains(newSell));
        assertTrue(residence.getSellers().contains(newSell2));
        assertEquals(4, residence.getSellers().size());
        assertEquals(4, residence.getSellersAccounts().size());
        assertEquals("12345", residence.getSellersAccounts().get("meow2"));
    }

    @Test
    public void testAddNewBuyer() {
        Residence newRes = new Residence("aaa");
        Buyer newBuy = new Buyer("new", newRes, "meow", "1234");
        residence.addNewBuyer(newBuy);
        assertTrue(residence.getBuyers().contains(newBuy));
        assertEquals(3, residence.getBuyers().size());
        assertEquals(3, residence.getBuyersAccounts().size());
        assertEquals("1234", residence.getBuyersAccounts().get("meow"));

        Buyer newBuy2 = new Buyer("new2", newRes, "meow2", "12345");
        residence.addNewBuyer(newBuy2);
        assertTrue(residence.getBuyers().contains(newBuy));
        assertTrue(residence.getBuyers().contains(newBuy2));
        assertEquals(4, residence.getBuyers().size());
        assertEquals(4, residence.getBuyersAccounts().size());
        assertEquals("12345", residence.getBuyersAccounts().get("meow2"));

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
    public void testSetDefaultItems() {
        Residence walterGage = new Residence("Walter Gage");
        walterGage.setDefaultItems();
        assertEquals("milk", walterGage.getItems().get(0).getName());
        assertEquals("yoghurt", walterGage.getItems().get(1).getName());
        assertEquals("pencil", walterGage.getItems().get(2).getName());
        assertEquals("honey", walterGage.getItems().get(3).getName());
        Residence placeVanier = new Residence("Place Vanier");
        placeVanier.setDefaultItems();
        assertEquals("coke", placeVanier.getItems().get(0).getName());
        assertEquals("coffee", placeVanier.getItems().get(1).getName());
        assertEquals("honey", placeVanier.getItems().get(2).getName());
        Residence exchange = new Residence("Exchange");
        exchange.setDefaultItems();
        assertEquals("coke", exchange.getItems().get(0).getName());
        assertEquals("sugar", exchange.getItems().get(1).getName());
        assertEquals("honey", exchange.getItems().get(2).getName());
        Residence totemPark = new Residence("Totem Park");
        totemPark.setDefaultItems();
        assertEquals("banana", totemPark.getItems().get(0).getName());
        assertEquals("apple", totemPark.getItems().get(1).getName());
        assertEquals("pear", totemPark.getItems().get(2).getName());
        assertEquals("TV", totemPark.getItems().get(3).getName());

        assertEquals(4, walterGage.getItems().size());
        assertEquals(3, placeVanier.getItems().size());
        assertEquals(4, totemPark.getItems().size());
        assertEquals(3, exchange.getItems().size());
    }
}
