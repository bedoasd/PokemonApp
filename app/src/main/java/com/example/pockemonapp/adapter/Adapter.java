package com.example.pockemonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pockemonapp.R;
import com.example.pockemonapp.model.Pockemon;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Pockemon> pockemonList = new ArrayList<>();
    private Context mcontext;


    public Adapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pockemon_item, parent, false));

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pockemontitle.setText(pockemonList.get(position).getName());
        Glide.with(mcontext)
                .load(pockemonList.get(position).getUrl())
                .into(holder.pockemonImage);
    }

    @Override
    public int getItemCount() {
        return pockemonList.size();
    }

    public void setList(List<Pockemon>modelList){
        this.pockemonList=modelList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pockemontitle;
        ImageView pockemonImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pockemontitle = itemView.findViewById(R.id.pockemon_name);
            pockemonImage = itemView.findViewById(R.id.pockemon_image);




        }
    }
}