package com.example.api.tsutsumeki;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
