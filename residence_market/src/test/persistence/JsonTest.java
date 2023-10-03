package persistence;

import model.Buyer;
import model.Item;
import model.Residence;
import model.Seller;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkResidence(String name, HashMap<String, String> ba, HashMap<String, String> sa,
                                  List<Buyer> bs, List<Item> is, List<Seller> ss, Residence res) {
        assertEquals(name, res.getName());
        assertEquals(ba, res.getBuyersAccounts());
        assertEquals(sa, res.getSellersAccounts());
        assertEquals(bs, res.getBuyers());
        assertEquals(is, res.getItems());
        assertEquals(ss, res.getSellers());
    }
}
