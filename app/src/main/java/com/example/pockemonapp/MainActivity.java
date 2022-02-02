package com.example.pockemonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private Button tofavbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recycler);
        adapter=new Adapter(this);
        recyclerView.setAdapter(adapter);

        setupSwip();
        tofavbtn=findViewById(R.id.to_fav_btn);
        tofavbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FavActivity.class));
            }
        });


        myviewModel=new ViewModelProvider(this).get(PockemonViewModel.class);

        myviewModel.getPockemon();


        myviewModel.PockemonList.observe(this, new Observer<ArrayList<Pockemon>>() {
            @Override
            public void onChanged(ArrayList<Pockemon> pockemons) {
                adapter.setList(pockemons);
            }
        });

    }


    private void setupSwip(){

        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPokemonPosition =viewHolder.getAdapterPosition();
                Pockemon swipedPokemon=adapter.getPockemonAt(swipedPokemonPosition);
                myviewModel.insertPockemon(swipedPokemon);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Pokemon Added To Database", Toast.LENGTH_SHORT).show();


            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

}