package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginSystem {
    LoginSystem logSys;
    Residence res;
    Buyer buyer;

    @BeforeEach
    void setup() {
        res = new Residence("new Residence");
        logSys = new LoginSystem(res, "nhash03", "123abc");
    }

    @Test
    void testConstructors(){
        assertEquals(res, logSys.getUserResidence());
        assertEquals("nhash03", logSys.getUsername());
        assertEquals("123abc", logSys.getPassword());
    }

    @Test
    void testBuyerLogin() {
        assertEquals(-2, logSys.buyerLogin());
        Buyer b1 = new Buyer("Shadan", res, "shad", "abcd");
        Buyer b2 = new Buyer("Negin", res, "nhash03", "123abc");

        assertEquals(1, logSys.buyerLogin());

        LoginSystem newLogSys = new LoginSystem(res, "nhash03", "123abd");
        assertEquals(-1, newLogSys.buyerLogin());
    }

    @Test
    void testSellerLogin() {
        assertEquals(-2, logSys.sellerLogin());
        Seller s1 = new Seller("Shadan", res, "shad", "abcd");
        Seller s2 = new Seller("Negin", res, "nhash03", "123abc");

        assertEquals(1, logSys.sellerLogin());

        LoginSystem newLogSys = new LoginSystem(res, "nhash03", "123abd");
        assertEquals(-1, newLogSys.sellerLogin());
    }
}