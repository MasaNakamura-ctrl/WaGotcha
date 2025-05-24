package com.example.api.tsutsumeki;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class TsutsumekiTest {
    @Test
    void tsutsumekiFields(){
        Tsutsumeki req = new Tsutsumeki();
        req.setId(1);
        req.setTsutsumeki("Success!");

        assertEquals(1,req.getId());
        assertEquals("Success!",req.getTsutsumeki());
    }

    @Test
    void testEqualAndHashSuccess(){
        Tsutsumeki test1 = new Tsutsumeki(1, "Test");
        Tsutsumeki test2 = new Tsutsumeki(1, "Test");

        // equals
        assertEquals(test1,test2);

        // hashCode
        assertEquals(test1.hashCode(),test2.hashCode());
    }

    @Test
    void testEqualAndHashFailure(){
        Tsutsumeki test1 = new Tsutsumeki(1, "Test");
        Tsutsumeki test2 = new Tsutsumeki(2, "Test");

        // equals
        assertNotEquals(test1,test2);

        // hashCode
        assertNotEquals(test1.hashCode(),test2.hashCode());
    }
}
