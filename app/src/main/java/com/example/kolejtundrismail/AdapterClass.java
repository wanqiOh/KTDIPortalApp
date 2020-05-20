package com.example.kolejtundrismail;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{

    ArrayList<activity> list;
    public AdapterClass(ArrayList<activity> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        //Log.d("CREATION",list.get(position).getName());
        holder.date.setText("Start Date : "+list.get(position).getDate());
        holder.place.setText("Place : "+list.get(position).getPlace());
        holder.pic.setText("PIC : "+list.get(position).getPIC());
        holder.phone.setText("Phone No. PIC : "+list.get(position).getPhone_no());
        holder.merit.setText("Merit : "+list.get(position).getMerit());
        //Log.d("CREATION",list.get(position).getId());
        holder.creator.setText("Created by : "+list.get(position).getIssued_by());
        holder.btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CREATION", "click button");
                Intent intent = new Intent(holder.btnR.getContext(),Main4Activity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("place",list.get(position).getPlace());
                intent.putExtra("date",list.get(position).getDate());
                intent.putExtra("merit",list.get(position).getMerit());
                holder.btnR.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return list.size();}

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,place,pic,phone,merit,creator;
        Button btnR;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.activity);
            date = itemView.findViewById(R.id.date);
            place = itemView.findViewById(R.id.Place);
            pic = itemView.findViewById(R.id.pic);
            phone = itemView.findViewById(R.id.phone_pic);
            merit = itemView.findViewById(R.id.merit);
            creator = itemView.findViewById(R.id.issued_by);
            btnR = itemView.findViewById(R.id.btnResgister);
        }
    }

}
