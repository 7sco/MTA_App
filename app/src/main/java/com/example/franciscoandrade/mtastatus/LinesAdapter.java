package com.example.franciscoandrade.mtastatus;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.mtastatus.database.StationsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by franciscoandrade on 2/7/18.
 */

public class LinesAdapter extends RecyclerView.Adapter <LinesAdapter.LinesViewHolder>{
    List<StationsEntity> listTrains ;
    List <Character>  set;
    Context context;


    public LinesAdapter( Context context) {
        listTrains= new ArrayList<>();
        set= new ArrayList<>();
        this.context = context;
    }

    @Override
    public LinesAdapter.LinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lines_item_view, parent, false);
        return new LinesAdapter.LinesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(LinesAdapter.LinesViewHolder holder, int position) {
        char line= set.get(position);
        colorSorting(holder, line);
        holder.lineTV.setText(String.valueOf(line));
    }

    private void colorSorting(LinesViewHolder holder, char line) {
        switch (line){
            case 'A':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                holder.lineInfoTV.setText("8TH AVE Express");
                break;
            case 'E':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                holder.lineInfoTV.setText("Eight Avenue Local");
                break;
            case 'C':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                holder.lineInfoTV.setText("Eight Avenue Local");
                break;
            case 'B':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                holder.lineInfoTV.setText("Central Park West Local/\n6 Avenue Express");
                break;
            case 'D':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                holder.lineInfoTV.setText("6 Avenue Express");
                break;
            case 'F':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                holder.lineInfoTV.setText("Queens Blvd Local");
                break;
            case 'M':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                holder.lineInfoTV.setText("8TH AVE Express");
                break;
            case 'G':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.G));
                holder.lineInfoTV.setText("Brooklyn-Queens\nCrosstown Local");
                break;
            case 'J':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                holder.lineInfoTV.setText("Nassau Street Express");
                break;
            case 'Z':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                holder.lineInfoTV.setText("Nassau Street Express");
                break;
            case 'L':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.L));
                holder.lineInfoTV.setText("14 Street-Canarsie Local");
                break;
            case 'N':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                holder.lineInfoTV.setText("Broadway Express");
                break;
            case 'Q':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                holder.lineInfoTV.setText("Second Av-broadway Express");
                break;
            case 'R':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                holder.lineInfoTV.setText("Queens Blvd-4 Ave Local");
                break;
            case 'S':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.S));
                holder.lineInfoTV.setText("42 St Shuttle ");
                break;
            case '1':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                holder.lineInfoTV.setText("Broadway-7 Av Local");
                break;
            case '2':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                holder.lineInfoTV.setText("Seventh Av Express");
                break;
            case '3':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                holder.lineInfoTV.setText("Seventh Av Express");
                break;
            case '4':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                holder.lineInfoTV.setText("Lexington Av Express");
                break;
            case '5':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                holder.lineInfoTV.setText("Lexington Av Express");
                break;
            case '6':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                holder.lineInfoTV.setText("Lexington Av Local");
                break;
            case '7':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.seven));
                holder.lineInfoTV.setText("Flushing Local");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return set.size();
    }

    public void addLines(List<StationsEntity> newSetList, List <Character> listOfLines ) {
        listTrains.addAll(newSetList);
        set.addAll(listOfLines);
        notifyDataSetChanged();
    }


    public class LinesViewHolder extends RecyclerView.ViewHolder {
        private TextView lineTV, lineInfoTV;

        public LinesViewHolder(View itemView) {
            super(itemView);
            lineTV=(TextView)itemView.findViewById(R.id.lineTV);
            lineInfoTV=(TextView)itemView.findViewById(R.id.lineInfoTV);
        }
    }
}
