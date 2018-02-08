package com.example.franciscoandrade.mtastatus.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.mtastatus.R;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscoandrade on 2/7/18.
 */

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.StationsViewHolder> {

    Context context;
    List<StationsEntity> stationsList;

    public StationsAdapter(Context context) {
        stationsList= new ArrayList<>();
        this.context = context;
    }

    @Override
    public StationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stations_item_view, parent, false);
        return new StationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationsViewHolder holder, int position) {
        holder.stationNameTV.setText(stationsList.get(position).getStationName());
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public void addLines(List<StationsEntity>  newList ) {
        stationsList.addAll(newList);
        notifyDataSetChanged();
    }

    public class StationsViewHolder extends RecyclerView.ViewHolder {
        TextView stationNameTV;
        public StationsViewHolder(View itemView) {
            super(itemView);
            stationNameTV=(TextView)itemView.findViewById(R.id.stationNameTV);
        }
    }
}
