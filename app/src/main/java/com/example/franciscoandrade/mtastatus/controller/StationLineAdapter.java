package com.example.franciscoandrade.mtastatus.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.mtastatus.R;
import com.example.franciscoandrade.mtastatus.StationsActivity;

import java.util.List;

/**
 * Created by Wayne Kellman on 2/7/18.
 */

public class StationLineAdapter extends RecyclerView.Adapter<StationLineAdapter.ViewHolder> {

    public List<String> stationLines;
    public Context context;

    public StationLineAdapter(List<String> stationLines, Context context) {
        this.stationLines = stationLines;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_line_itemview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(stationLines.get(position));
        colorSorting(holder, stationLines.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StationsActivity.class);
                intent.putExtra("station_line", stationLines.get(position));
                context.startActivity(intent);
            }
        });
    }

    private void colorSorting(ViewHolder holder, String line) {
        switch (line) {
            case "A":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;
            case "E":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;
            case "C":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.ACE));
                break;

            case "B":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;

            case "D":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;
            case "F":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;
            case "M":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.BDFM));
                break;

            case "G":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.G));
                break;
            case "J":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                break;
            case "Z":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.JZ));
                break;
            case "L":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.L));
                break;

            case "N":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case "Q":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case "R":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.NQR));
                break;

            case "S":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.S));
                break;
            case "1":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case "2":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case "3":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.onetwothree));
                break;
            case "4":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case "5":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case "6":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.fourfiveSix));
                break;
            case "7":
                holder.textView.setBackgroundTintList(context.getResources().getColorStateList(R.color.seven));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return stationLines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.station_line_name);
        }
    }
}
