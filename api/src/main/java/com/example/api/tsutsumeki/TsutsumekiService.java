package com.example.api.tsutsumeki;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TsutsumekiService {
    private final TsutsumekiRepository tsutsumekiRepository;

    public TsutsumekiService(TsutsumekiRepository tsutsumekiRepository){
        this.tsutsumekiRepository = tsutsumekiRepository;
    }

    public List<Tsutsumeki> getAllTsutsumekis(){
        return tsutsumekiRepository.findAll();
    }

    public Tsutsumeki postTsutsumeki(String tsutsumeki){
        int id =tsutsumekiRepository.createTsutsumeki(tsutsumeki);
        Tsutsumeki newTsutsumeki = new Tsutsumeki();
        newTsutsumeki.setId(id);
        newTsutsumeki.setTsutsumeki(tsutsumeki);
        return newTsutsumeki;
    }

    public Tsutsumeki putTsutsumeki(int id, String tsutsumeki){
        int updatedRows = tsutsumekiRepository.updateTsutsumeki(id, tsutsumeki);
        if (updatedRows==0){
            return null;
        }
        return new Tsutsumeki(id, tsutsumeki);
    }

    public Tsutsumeki deleteTsutsumeki(int id){
        int deleteRows = tsutsumekiRepository.deleteTsutsumeki(id);
        if (deleteRows==0){
            return null;
        }
        return new Tsutsumeki();
    }
}
