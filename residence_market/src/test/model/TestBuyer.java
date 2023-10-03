package model;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBuyer {

    Buyer buyer1;
    Buyer testingBuyer;
    Residence walterGage;
    Residence exchange;
    Item milk;
    Item yoghurt;
    Seller mainSeller;

    @BeforeEach
    public void setup() {
        walterGage = new Residence("Walter Gage");
        exchange = new Residence("Exchange");
        buyer1 = new Buyer("Negin", walterGage, "nhash03", "12abc");
        testingBuyer = new Buyer("Maggie", exchange, "magmag", "123e");
        mainSeller = new Seller("Parshan", walterGage, "pjavan", "123a1");
        milk = new Item("Milk", 2.50, walterGage, mainSeller.getId());
        yoghurt = new Item("Yoghurt", 3.778, walterGage, mainSeller.getId());
        EventLog.getInstance().clear();
    }

    @Test
    public void testConstructors(){
        assertEquals("Negin", buyer1.getName());
        assertEquals(0, buyer1.getBalance());
        assertEquals(0, buyer1.getItemsToBuy().size());
        assertEquals(walterGage, buyer1.getLocation());
        assertEquals("nhash03", buyer1.getUsername());
        assertEquals("12abc", buyer1.getPassword());
        assertEquals(0, buyer1.getAlreadyBought().size());
        assertTrue(walterGage.getBuyers().contains(buyer1));
        assertEquals("12abc",walterGage.getBuyersAccounts().get("nhash03"));
    }

    @Test
    public void testSetBalance() {
        testingBuyer.setBalance(12.5);
        assertEquals(12.5, testingBuyer.getBalance());
    }

    @Test
    public void testSetLocation() {
        testingBuyer.setLocation(exchange);
        assertEquals(exchange, testingBuyer.getLocation());
    }

    @Test
    public void testSetPassword() {
        testingBuyer.setPassword("123-abc");
        assertEquals("123-abc", testingBuyer.getPassword());
    }

    @Test
    public void testGetName() {
        assertEquals("Maggie", testingBuyer.getName());
    }

    @Test
    public void testGetLocation() {
        testingBuyer.setLocation(exchange);
        assertEquals(exchange, testingBuyer.getLocation());
    }

    @Test
    public void testGetBalance() {
        testingBuyer.setBalance(2000);
        assertEquals(2000, testingBuyer.getBalance());
    }

    @Test
    public void testGetPassword() {
        testingBuyer.setPassword("a1-2b;");
        assertEquals("a1-2b;", testingBuyer.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("magmag", testingBuyer.getUsername());
    }

    @Test
    public void testAddItemstoBuy() {
        buyer1.addItemToBuy(milk);
        assertEquals(1, buyer1.getItemsToBuy().size());
        assertTrue(buyer1.getItemsToBuy().contains(milk));

        buyer1.addItemToBuy(yoghurt);
        assertEquals(2, buyer1.getItemsToBuy().size());
        assertTrue(buyer1.getItemsToBuy().contains(milk));
        assertTrue(buyer1.getItemsToBuy().contains(yoghurt));
    }

    @Test
    public void testHowMuchtoPay(){
        assertEquals(0, buyer1.howMuchToPay());

        buyer1.addItemToBuy(milk);
        assertEquals(2.5, buyer1.howMuchToPay(), 0.01);

        buyer1.addItemToBuy(yoghurt);
        assertEquals(6.278, buyer1.howMuchToPay(), 0.01);
    }

    @Test
    public void testPayForItems(){

        buyer1.addItemToBuy(milk);
        buyer1.payForItems();
        assertEquals(0, buyer1.getBalance());
        assertEquals(1, buyer1.getItemsToBuy().size());
        assertEquals(0, buyer1.getAlreadyBought().size());

        buyer1.setBalance(2.5);
        buyer1.payForItems();
        assertEquals(0, buyer1.getBalance(),0.01);
        assertEquals(0, buyer1.getItemsToBuy().size());
        assertEquals(1, buyer1.getAlreadyBought().size());
        assertTrue(buyer1.getAlreadyBought().contains(milk));

        buyer1.setBalance(6);
        buyer1.addItemToBuy(milk);
        buyer1.addItemToBuy(yoghurt);
        buyer1.payForItems();
        assertEquals(3.5, buyer1.getBalance(), 0.01);
        assertEquals(1, buyer1.getItemsToBuy().size());
        assertEquals(2, buyer1.getAlreadyBought().size());
        assertTrue(buyer1.getAlreadyBought().contains(milk));
        assertTrue(buyer1.getItemsToBuy().contains(yoghurt));

        buyer1.setBalance(6.28);
        buyer1.addItemToBuy(milk);
        buyer1.payForItems();
        assertEquals(0, buyer1.getBalance(), 0.01);
        assertEquals(0, buyer1.getItemsToBuy().size());
        assertEquals(4, buyer1.getAlreadyBought().size());
        assertTrue(buyer1.getAlreadyBought().contains(milk));
        assertTrue(buyer1.getAlreadyBought().contains(yoghurt));
    }

    @Test
    public void testNotEnoughBalance() {
        assertEquals("You don't have enough money to buy Milk",
                buyer1.notEnoughBalance(milk));
    }

    @Test
    public void testRemoveFromWishlist(){
        buyer1.removeFromWishlist(milk);
        assertEquals(0, buyer1.getItemsToBuy().size());

        buyer1.addItemToBuy(milk);
        buyer1.removeFromWishlist(yoghurt);
        assertEquals(1, buyer1.getItemsToBuy().size());
        assertTrue(buyer1.getItemsToBuy().contains(milk));

        buyer1.addItemToBuy(yoghurt);
        buyer1.removeFromWishlist(milk);
        assertEquals(1, buyer1.getItemsToBuy().size());
        assertTrue(buyer1.getItemsToBuy().contains(yoghurt));
    }

    @Test
    public void testToJson() {
        buyer1.addItemToBuy(milk);
        buyer1.setBalance(3);
        buyer1.payForItems();
        JSONObject jsonObject = buyer1.toJson();
        System.out.println(jsonObject.toString());
        assertEquals("nhash03",jsonObject.get("username"));
        assertEquals(buyer1.getId(),jsonObject.optLong("id"));
    }

    @Test
    public void testBuyerEvents() {
        Buyer test = new Buyer("test", walterGage, "test", "test");
        test.addBuyerEvent();
        Item testItem = new Item("new", 22, walterGage, test.getId());
        test.addItemToBuy(testItem);
        test.removeFromWishlist(testItem);
        test.addItemToBuy(testItem);
        test.setBalance(30);
        test.payForItems();
        ArrayList<String> events = new ArrayList<>();
        for (Event event: EventLog.getInstance()) {
            events.add(event.getDescription());
        }
        assertEquals("Event log cleared.", events.get(0));
        assertEquals("The buyer, test joined Walter Gage", events.get(1));
        assertEquals("Item new added to test shopping bag.", events.get(2));
        assertEquals("test removed new from his/her wish list.", events.get(3));
        assertEquals("Buyer test paid for items!.", events.get(5));

        assertEquals(6, events.size());
    }

}
