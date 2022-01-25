package com.example.pockemonapp.repository;

import com.example.pockemonapp.model.PockemonResponce;
import com.example.pockemonapp.network.PockemonApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    public PockemonApiService pockemonApiService;

    @Inject
    public Repository(PockemonApiService pockemonApiService) {
        this.pockemonApiService = pockemonApiService;
    }


    public Observable<PockemonResponce> getPOckemons(){
        return pockemonApiService.getpokemons();
    }
}
