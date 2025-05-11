package com.example.api.tsutsumeki;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class TsutsumekiServiceTest {
    @Mock
    TsutsumekiRepository tsutsumekiRepository;
    @InjectMocks
    TsutsumekiService tsutsumekiService;

    @Test
    void testPostTsutsumeki(){
        String input = "Success";
        int fakeId = 1;
        when(tsutsumekiRepository.createTsutsumeki(input)).thenReturn(fakeId);
        Tsutsumeki result = tsutsumekiService.postTsutsumeki("Success");
        assertEquals(fakeId, result.getId());
        assertEquals(input, result.getTsutsumeki());
    }

    @Test
    void testPutTsutsumeki(){
        String input = "New";
        int fakeId = 1;
        Tsutsumeki fakeTsutsumeki = new Tsutsumeki();
        fakeTsutsumeki.setId(fakeId);
        fakeTsutsumeki.setTsutsumeki(input);
        when(tsutsumekiRepository.updateTsutsumeki(fakeId, input)).thenReturn(fakeId);
        when(tsutsumekiRepository.findById(fakeId)).thenReturn(fakeTsutsumeki);
        Tsutsumeki result = tsutsumekiService.putTsutsumeki(1, "New");
        assertEquals(fakeId, result.getId());
        assertEquals(input, result.getTsutsumeki());
    }

    @Test
    void testPutTsutsumeki_notfound(){
        String input = "New";
        int fakeId = 999;
        when(tsutsumekiRepository.updateTsutsumeki(fakeId, input)).thenReturn(0);
        Tsutsumeki result = tsutsumekiService.putTsutsumeki(999, "New");
        assertNull(result);
    }

    @Test
    void testDeleteTsutsumeki(){
        int fakeId = 1;
        when(tsutsumekiRepository.deleteTsutsumeki(fakeId)).thenReturn(fakeId);
        int result = tsutsumekiService.deleteTsutsumeki(1);
        assertEquals(fakeId, result);
    }

    @Test
    void testDeleteTsutsumeki_notfound(){
        int fakeId = 999;
        when(tsutsumekiRepository.deleteTsutsumeki(fakeId)).thenReturn(0);
        int result = tsutsumekiService.deleteTsutsumeki(999);
        assertEquals(0, result);
    }
}
