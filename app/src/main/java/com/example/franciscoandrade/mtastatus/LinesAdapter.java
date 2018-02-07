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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by franciscoandrade on 2/7/18.
 */

public class LinesAdapter extends RecyclerView.Adapter <LinesAdapter.LinesViewHolder>{
    List<StationsEntity> listTrains ;
    Context context;


    public LinesAdapter( Context context) {
        listTrains= new ArrayList<>();
        this.context = context;
    }

    @Override
    public LinesAdapter.LinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lines_item_view, parent, false);
        return new LinesAdapter.LinesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(LinesAdapter.LinesViewHolder holder, int position) {



        char line= listTrains.get(position).getStationID().charAt(0);

        colorSorting(holder, line);

        String lineInfo= listTrains.get(position).getStationName();

        holder.lineTV.setText(String.valueOf(line));
        holder.lineInfoTV.setText(lineInfo);
        //Log.d("ITERATOR", "onBindViewHolder: "+newSet.iterator().);
    }

    private void colorSorting(LinesViewHolder holder, char line) {
        switch (line){
            case 'A':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;
            case 'E':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;
            case 'C':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;

            case 'B':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;

            case 'D':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;
            case 'F':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;
            case 'M':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;

            case 'G':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.G));
                break;
            case 'J':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                break;
            case 'Z':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                break;
            case 'L':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.L));
                break;

            case 'N':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case 'Q':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case 'R':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case 'S':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.S));
                break;
            case '1':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case '2':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case '3':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case '4':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case '5':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case '6':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case '7':
                holder.lineTV.setBackgroundTintList(context.getResources().getColorStateList(R.color.seven));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listTrains.size();
    }

    public void addLines(List<StationsEntity> newSetList ) {
        listTrains.addAll(newSetList);
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
