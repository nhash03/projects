package model;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestResidence {
    Residences residences;
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
        i1 = new Item("Milk", 2.50, residence, s1.getId());
        i2 = new Item("Yoghurt", 3.778, residence, s2.getId());
        residences = new Residences();
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
    public void testConstructor2() {
        Residence newRes = new Residence("blah", new HashMap<>(), new HashMap<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        newRes.getItems().add(i1);
        newRes.getSellersAccounts().put("a", "a");
        newRes.getBuyersAccounts().put("b", "b");
        newRes.getBuyers().add(buyer1);
        newRes.getSellers().add(s1);
        assertEquals(1, newRes.getItems().size());
        assertEquals(1, newRes.getSellers().size());
        assertEquals(1, newRes.getBuyers().size());
        assertEquals(1, newRes.getSellersAccounts().size());
        assertEquals(1, newRes.getBuyersAccounts().size());
        assertTrue(newRes.getItems().contains(i1));
        assertTrue(newRes.getBuyers().contains(buyer1));
        assertTrue(newRes.getSellersAccounts().containsKey("a"));
        assertTrue(newRes.getBuyersAccounts().containsKey("b"));
        assertTrue(newRes.getSellers().contains(s1));
        assertEquals("blah", newRes.getName());
    }

    @Test
    public void testSetItems() {
        List<Item> items = new ArrayList<>();
        Item item = new Item("a", 1.75, residence, 123L);
        items.add(item);
        residence.setItems(items);
        assertEquals(1, residence.getItems().size());
        assertTrue(residence.getItems().contains(item));
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
//        residence.addNewItem(i1);
        assertTrue(residence.getItems().contains(i1));
        assertEquals(2, residence.getItems().size());

        residence.addNewItem(i1);
        assertTrue(residence.getItems().contains(i1));
        assertEquals(3, residence.getItems().size());

        residence.addNewItem(i2);
        assertTrue(residence.getItems().contains(i1));
        assertTrue(residence.getItems().contains(i2));
        assertEquals(4, residence.getItems().size());
    }

    @Test
    public void testParseToRes() {
        Residence wg = Residence.parseToRes("Walter Gage", residences);
        Residence ex = Residence.parseToRes("Exchange", residences);
        Residence tp = Residence.parseToRes("Totem Park", residences);
        Residence pv = Residence.parseToRes("Place Vanier", residences);
        assertEquals("Walter Gage", wg.getName());
        assertEquals("Exchange", ex.getName());
        assertEquals("Totem Park", tp.getName());
        assertEquals("Place Vanier", pv.getName());
    }

    @Test
    public void testIsEmpty() {
        Residence emptyResidence = new Residence("Empty Res");
        assertTrue(emptyResidence.isEmpty());
        emptyResidence.addNewItem(i1);
        assertFalse(emptyResidence.isEmpty());
        emptyResidence.setItems(new ArrayList<>());
        emptyResidence.addNewSeller(s1);
        assertFalse(emptyResidence.isEmpty());
        emptyResidence.setSellers(new ArrayList<>());
        emptyResidence.setSellersAccounts(new HashMap<>());
        emptyResidence.addNewBuyer(b2);
        assertFalse(emptyResidence.isEmpty());
        emptyResidence.setBuyers(new ArrayList<>());
        assertFalse(emptyResidence.isEmpty());
        emptyResidence.setBuyersAccounts(new HashMap<>());
        emptyResidence.getSellersAccounts().put("a", "a");
        assertFalse(emptyResidence.isEmpty());
    }

    @Test
    public void testToJson() {
        JSONObject jsonObject = residence.toJson();
        assertEquals(2, jsonObject.getJSONArray("resBuyers").length());
        assertEquals(2, jsonObject.getJSONArray("resSellers").length());
        assertEquals(2, jsonObject.getJSONArray("resItems").length());
        assertEquals(2, jsonObject.getJSONObject("buyerAccounts").length());
        assertEquals(2, jsonObject.getJSONObject("sellerAccounts").length());
        assertEquals("123abc", jsonObject.getJSONObject("buyerAccounts").get("nhash03"));
    }
}
