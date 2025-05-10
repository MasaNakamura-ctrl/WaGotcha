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
    public Tsutsumeki(){
    }
    public Tsutsumeki(int id, String tsutsumeki){
        this.id = id;
        this.tsutsumeki = tsutsumeki;
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((tsutsumeki == null) ? 0 : tsutsumeki.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tsutsumeki other = (Tsutsumeki) obj;
        if (id != other.id)
            return false;
        if (tsutsumeki == null) {
            if (other.tsutsumeki != null)
                return false;
        } else if (!tsutsumeki.equals(other.tsutsumeki))
            return false;
        return true;
    }
}
