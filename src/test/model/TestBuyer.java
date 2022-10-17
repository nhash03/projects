package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBuyer {

    Buyer buyer1;
    Buyer testBuyer;
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
        testBuyer = new Buyer("Maggie", exchange, "magmag", "123e");
        mainSeller = new Seller("Parshan", walterGage, "pjavan", "123a1");
        milk = new Item("Milk", 2.50, walterGage, mainSeller);
        yoghurt = new Item("Yoghurt", 3.778, walterGage, mainSeller);
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
        testBuyer.setBalance(12.5);
        assertEquals(12.5, testBuyer.getBalance());
    }

    @Test
    public void testSetLocation() {
        testBuyer.setLocation(exchange);
        assertEquals(exchange, testBuyer.getLocation());
    }

    @Test
    public void testSetPassword() {
        testBuyer.setPassword("123-abc");
        assertEquals("123-abc", testBuyer.getPassword());
    }

    @Test
    public void testGetName() {
        assertEquals("Maggie", testBuyer.getName());
    }

    @Test
    public void testGetLocation() {
        testBuyer.setLocation(exchange);
        assertEquals(exchange, testBuyer.getLocation());
    }

    @Test
    public void testGetBalance() {
        testBuyer.setBalance(2000);
        assertEquals(2000, testBuyer.getBalance());
    }

    @Test
    public void testGetPassword() {
        testBuyer.setPassword("a1-2b;");
        assertEquals("a1-2b;", testBuyer.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("magmag", testBuyer.getUsername());
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
}
