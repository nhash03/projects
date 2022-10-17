package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem {
    Residence residence;
    Buyer buyer1;
    Buyer buyer2;
    Seller seller1;
    Seller s2;
    Item i1;
    Item i2;

    @BeforeEach
    public void setup() {
        residence = new Residence("Walter Gage");
        buyer1 = new Buyer("Negin", residence, "nhash03", "123abc");
        buyer2 = new Buyer("Elina", residence, "eligh", "a1b2");
        seller1 = new Seller("Shadan", residence, "shad", "123");
        s2 = new Seller("Ava", residence, "avahmadi", "abc-1");
        i1 = new Item("Milk", 2.50, residence, seller1);
        i2 = new Item("Yoghurt", 3.778, residence, s2);
    }

    @Test
    public void testConstructor(){
        assertEquals("Milk", i1.getName());
        assertNull(i1.getDescription());
        assertEquals(2.50, i1.getPrice());
        assertEquals(residence, i1.getLocation());
        assertNotNull(i1.getId());
        assertTrue(i1.getId() <= 99999);
        assertEquals(seller1, i1.getSeller());
        assertNull(i1.getBuyer());
        assertTrue(i1.isAvailable());
    }

    @Test
    public void testGetDescription(){
        i1.setDescription("I hate testing getters!");
        assertEquals("I hate testing getters!", i1.getDescription());
    }

    @Test
    public void testGetLocation() {
        assertEquals(residence, i2.getLocation());
    }

    @Test
    public void testGetBuyer(){
        i2.setBuyer(buyer2);
        assertEquals(buyer2, i2.getBuyer());
    }

    @Test
    public void testIsAvailable(){
        assertTrue(i2.isAvailable());
    }

    @Test
    public void testGetSeller(){
        Item newItem = new Item("sofa", 1000, residence, s2);
        assertEquals(s2, newItem.getSeller());
    }
    @Test
    public void testSetDescription() {
        i1.setDescription("This is the best milk ever!");
        assertEquals("This is the best milk ever!", i1.getDescription());
    }

    @Test
    public void testSetName() {
        i1.setName("new name");
        assertEquals("new name", i1.getName());
    }

    @Test
    public void testSetPrice() {
        i1.setPrice(3.33);
        assertEquals(3.33, i1.getPrice());
    }

    @Test
    public void testSetBuyer() {
        i1.setBuyer(buyer1);
        assertEquals(buyer1, i1.getBuyer());
    }

    @Test
    public void testSwitchAvailability() {
        i1.switchAvailability();
        assertFalse(i1.isAvailable());

        i1.switchAvailability();
        assertTrue(i1.isAvailable());
    }

    @Test
    public void testSetID() {
        Integer id = i2.setID();
        assertNotNull(id);
        assertTrue(0 <= id);
        assertTrue(id <= 99999);
    }

    @Test
    public void testBuy() {
        i1.buy(buyer1);
        assertEquals(buyer1, i1.getBuyer());
        assertFalse(i1.isAvailable());

        i2.switchAvailability();
        i2.buy(buyer1);
        assertNull(i2.getBuyer());
        assertFalse(i2.isAvailable());
    }
}
