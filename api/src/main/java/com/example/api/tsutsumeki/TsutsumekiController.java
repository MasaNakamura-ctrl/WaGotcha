package com.example.api.tsutsumeki;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tsutsumekis")
public class TsutsumekiController {
    @Autowired
    private TsutsumekiService tsutsumekiService;

    @GetMapping
    public ResponseEntity<List<Tsutsumeki>> getTsutsumekis(){
        try {
            List<Tsutsumeki> list = tsutsumekiService.getAllTsutsumekis();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping
    public void createTsutsumeki(@RequestBody TsutsumekiRequest request) {
        tsutsumekiService.postTsutsumeki(request.getTsutsumeki());
    }

    @PutMapping("/{id}")
    public void updateTsutsumeki(@PathVariable int id, @RequestBody TsutsumekiRequest request) {
        tsutsumekiService.putTsutsumeki(id, request.getTsutsumeki());
    }

    @DeleteMapping("/{id}")
    public void deleteTsutsumeki(@PathVariable int id) {
        tsutsumekiService.deleteTsutsumeki(id);
    }
}