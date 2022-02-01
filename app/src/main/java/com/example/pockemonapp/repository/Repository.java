package com.example.pockemonapp.repository;

import androidx.lifecycle.LiveData;

import com.example.pockemonapp.db.PockemonDao;
import com.example.pockemonapp.model.Pockemon;
import com.example.pockemonapp.model.PockemonResponce;
import com.example.pockemonapp.network.PockemonApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private PockemonApiService pockemonApiService;
    private PockemonDao pockemonDao;

    @Inject
    public Repository(PockemonApiService pockemonApiService , PockemonDao pockemonDao ) {
        this.pockemonApiService = pockemonApiService;
        this.pockemonDao=pockemonDao;
    }


    public Observable<PockemonResponce> getPOckemons(){
        return pockemonApiService.getpokemons();
    }


    public void inserPockemon(Pockemon pockemon){
        pockemonDao.insertPockemon(pockemon);
    }
    public void deletePockemon(String pockemonname){
        pockemonDao.deletePockemon(pockemonname);
    }
    public LiveData<List<Pockemon>>getfavPockemons(){
        return pockemonDao.getPokemons();
    }


}
