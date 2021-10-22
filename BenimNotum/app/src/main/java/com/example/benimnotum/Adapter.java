package com.example.benimnotum;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Not> nots;

    Adapter(Context context,List<Not> nots){
        this.inflater = LayoutInflater.from(context);
        this.nots = nots;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ozel_liste_gorunum,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = nots.get(position).getTitle();
        String date = nots.get(position).getDate();
        String time = nots.get(position).getTime();
        long id = nots.get(position).getID();
        Log.d("date on","Date on: "+date);
        Log.d("Title","onBindViewHolder: Title -> "+ title);
        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return nots.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nTitle, nDate, nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(),ayrintilar.class);
                    i.putExtra("ID",nots.get(getAdapterPosition()).getID());
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
