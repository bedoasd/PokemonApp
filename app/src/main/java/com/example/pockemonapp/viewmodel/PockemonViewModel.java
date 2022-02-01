package com.example.pockemonapp.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pockemonapp.model.Pockemon;
import com.example.pockemonapp.model.PockemonResponce;
import com.example.pockemonapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PockemonViewModel extends ViewModel {
    private Repository repository;


    @ViewModelInject
    public PockemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pockemon>> PockemonList = new MutableLiveData<>();
    public LiveData<List<Pockemon>> favList = null;


    public void getPockemon() {

        repository.getPOckemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PockemonResponce, ArrayList<Pockemon>>() {
                    @Override
                    public ArrayList<Pockemon> apply(PockemonResponce pockemonResponce) throws Throwable {
                        ArrayList<Pockemon> list = pockemonResponce.getResults();
                        for (Pockemon pockemon : list) {

                            String url = pockemon.getUrl();
                            String[] pockemonIndex = url.split("/");
                            pockemon.setUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                                    + pockemonIndex[pockemonIndex.length - 1] + ".png");

                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> PockemonList.setValue(result),
                        error -> Log.e("ViewMOdel", "" + error.getMessage()));


    }


    public void insertPockemon(Pockemon pockemon) {
        repository.inserPockemon(pockemon);
    }

    public void deletePockemon(String PockemonName) {
        repository.deletePockemon(PockemonName);
    }

    public void getfavPockemons() {
        favList = repository.getfavPockemons();
    }
}


