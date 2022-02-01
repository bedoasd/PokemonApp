package com.example.pockemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pockemonapp.adapter.Adapter;
import com.example.pockemonapp.model.Pockemon;
import com.example.pockemonapp.viewmodel.PockemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {

    private PockemonViewModel myviewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recycler);
        adapter=new Adapter(this);
        recyclerView.setAdapter(adapter);

        myviewModel=new ViewModelProvider(this).get(PockemonViewModel.class);

        myviewModel.getPockemon();


        myviewModel.PockemonList.observe(this, new Observer<ArrayList<Pockemon>>() {
            @Override
            public void onChanged(ArrayList<Pockemon> pockemons) {
                adapter.setList(pockemons);
            }
        });

    }

}