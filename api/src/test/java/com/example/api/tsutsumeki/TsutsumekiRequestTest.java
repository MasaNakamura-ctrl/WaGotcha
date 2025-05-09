package com.example.api.tsutsumeki;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TsutsumekiRequestTest {
    @Test
    void testTsutsumekifields(){
        TsutsumekiRequest req = new TsutsumekiRequest();
        req.setTsutsumeki("Success!");

        assertEquals("Success!",req.getTsutsumeki());
    }
}