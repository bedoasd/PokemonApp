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
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint

public class FavActivity extends AppCompatActivity {
    private PockemonViewModel myviewModel;
    private RecyclerView recyclerViewOfFav;
    private Adapter adapter;
    private Button tohomebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        recyclerViewOfFav=findViewById(R.id.recycler_fav);
        adapter=new Adapter(this);
        recyclerViewOfFav.setAdapter(adapter);

        setupSwip();
        tohomebtn=findViewById(R.id.to_home_btn);
        tohomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavActivity.this,MainActivity.class));
            }
        });

        myviewModel=new ViewModelProvider(this).get(PockemonViewModel.class);

        myviewModel.getfavPockemons();


        myviewModel.getFavList().observe(this, new Observer<List<Pockemon>>() {
            @Override
            public void onChanged(List<Pockemon> pockemons) {
                adapter.setList(pockemons);
            }
        });



    }




    private void setupSwip(){

        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPokemonPosition =viewHolder.getAdapterPosition();
                Pockemon swipedPokemon=adapter.getPockemonAt(swipedPokemonPosition);
                myviewModel.deletePockemon(swipedPokemon.getName());
                Toast.makeText(FavActivity.this, "Pokemon deleted from Database", Toast.LENGTH_SHORT).show();


            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewOfFav);

    }
}