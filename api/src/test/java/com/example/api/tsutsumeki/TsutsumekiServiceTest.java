package com.example.api.tsutsumeki;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
