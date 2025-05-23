package com.example.api.tsutsumeki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tsutsumekis")
public class TsutsumekiController {
    @Autowired
    private TsutsumekiService tsutsumekiService;

    @GetMapping
    public ResponseEntity<?> getTsutsumekis(){
        try {
            List<Tsutsumeki> list = tsutsumekiService.getAllTsutsumekis();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<Tsutsumeki> createTsutsumeki(@RequestBody TsutsumekiRequest request) {
        try {
            Tsutsumeki created = tsutsumekiService.postTsutsumeki(request.getTsutsumeki());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTsutsumeki(@PathVariable int id, @RequestBody TsutsumekiRequest request) {
        Tsutsumeki updated = tsutsumekiService.putTsutsumeki(id, request.getTsutsumeki());
        if (updated==null){
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        } else{
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTsutsumeki(@PathVariable int id) {
        int deleted = tsutsumekiService.deleteTsutsumeki(id);
        if (deleted==0){
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        } else{
            return ResponseEntity.noContent().build();
        }
    }
}