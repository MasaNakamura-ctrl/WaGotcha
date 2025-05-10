package com.example.api.tsutsumeki;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TsutsumekiRepositoryTest {
    
    @Mock
    TsutsumekiRepository tsutsumekiRepository;

    @Test
    void testFindTsutsumeki_found(){
        Tsutsumeki tsutsumeki = new Tsutsumeki(1, "Here I Am!");
        when(tsutsumekiRepository.findById(1)).thenReturn(tsutsumeki);

        Tsutsumeki result = tsutsumekiRepository.findById(1);
        assertNotNull(result);
        assertEquals("Here I Am!", result.getTsutsumeki());
    }

    @Test
    void testFindTsutsumeki_notfound(){
        when(tsutsumekiRepository.findById(2)).thenReturn(null);

        Tsutsumeki result = tsutsumekiRepository.findById(2);
        assertNull(result);
    }

    @Test
    void testCreateTsutsumeki(){
        when(tsutsumekiRepository.createTsutsumeki("Created")).thenReturn(1);
        int id = tsutsumekiRepository.createTsutsumeki("Created");
        assertEquals(1, id);
        verify(tsutsumekiRepository, times(1)).createTsutsumeki("Created");
    }
}
