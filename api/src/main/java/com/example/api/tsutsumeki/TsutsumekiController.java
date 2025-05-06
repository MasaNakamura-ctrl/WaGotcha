package com.example.api.tsutsumeki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tsutsumekis")
public class TsutsumekiController {
    @Autowired
    private TsutsumekiService tsutsumekiService;

    @DeleteMapping("/{id}")
    public void deleteTsutsumeki(@PathVariable int id) {
        tsutsumekiService.deleteTsutsumeki(id);
    }
}