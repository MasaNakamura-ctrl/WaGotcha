package com.example.api.tsutsumeki;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tsutsumeki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tsutsumeki;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTsutsumeki(){
        return tsutsumeki;
    }
    public void setTsutsumeki(String tsutsumeki){
        this.tsutsumeki = tsutsumeki;
    }
}
