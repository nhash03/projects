package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSeller {
    Seller seller1;
    Residence walterGage;
    Residence exchange;
    Item milk;
    Item yoghurt;
    Buyer mainBuyer;

    @BeforeEach
    public void setup() {
        walterGage = new Residence("Walter Gage");
        exchange = new Residence("Exchange");
        seller1 = new Seller("Negin", walterGage, "nhash03", "12abc");
        mainBuyer = new Buyer("Parshan", walterGage, "pjavan", "123a1");
        milk = new Item("Milk", 2.50, walterGage, seller1);
        yoghurt = new Item("Yoghurt", 3.778, walterGage, seller1);
    }

    @Test
    public void testConstructors(){
        assertEquals("Negin", seller1.getName());
        assertEquals(0, seller1.getBalance());
        assertEquals(walterGage, seller1.getLocation());
        assertEquals("nhash03", seller1.getUsername());
        assertEquals("12abc", seller1.getPassword());
        assertEquals(0, seller1.getItemsToSell().size());
    }

    @Test
    public void testSetters() {
        seller1.setBalance(12.5);
        assertEquals(12.5, seller1.getBalance());

        seller1.setLocation(exchange);
        assertEquals(exchange, seller1.getLocation());

        seller1.setPassword("123-abc");
        assertEquals("123-abc", seller1.getPassword());
    }

    @Test
    public void testAddItemtoSell() {
        seller1.addItemToSell(milk);
        assertEquals(1, seller1.getItemsToSell().size());
        assertTrue(seller1.getItemsToSell().contains(milk));

        seller1.addItemToSell(yoghurt);
        assertEquals(2, seller1.getItemsToSell().size());
        assertTrue(seller1.getItemsToSell().contains(milk));
        assertTrue(seller1.getItemsToSell().contains(yoghurt));
    }

    @Test
    public void testDeleteItem() {
        seller1.addItemToSell(milk);
        seller1.deleteItem(yoghurt);
        assertTrue(seller1.getItemsToSell().contains(milk));
        assertEquals(1, seller1.getItemsToSell().size());

        seller1.deleteItem(milk);
        assertEquals(0, seller1.getItemsToSell().size());
    }
}
