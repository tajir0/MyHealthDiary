package com.example.mypaindiary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypaindiary.databinding.RvLayoutBinding;
import com.example.mypaindiary.entity.PainRecord;
import com.example.mypaindiary.model.PainRecordUnit;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter
        <RecyclerViewAdapter.ViewHolder> {
    private List<PainRecordUnit> painRecordUnits;
    public RecyclerViewAdapter(List<PainRecordUnit> painRecordUnits) {
        this.painRecordUnits = painRecordUnits;
    }
    //This method creates a new view holder that is constructed with a new View, inflated from a layout
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                                     parent, int viewType) {
        RvLayoutBinding binding=
                RvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder
                                         viewHolder, int position) {
        final PainRecordUnit unit = painRecordUnits.get(position);
        viewHolder.binding.RvrId.setText("ID: " + unit.getRid());
        viewHolder.binding.RvdateEntry.setText("Date: " + unit.getDataEntry());
        viewHolder.binding.Rvintensity.setText(unit.getPainIntensity());
        viewHolder.binding.Rvlocation.setText("Pain Location: " + unit.getPainLocation());
        viewHolder.binding.Rvmood.setText(unit.getMoodLevel());
        viewHolder.binding.Rvsteps.setText("Steps Taken: " + unit.getStepsTaken());
        viewHolder.binding.RvTemp.setText("Temperature: " );
        viewHolder.binding.RvHumid.setText("Humid: " );
        viewHolder.binding.RvPressure.setText("Pressure: " );
        viewHolder.binding.deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                painRecordUnits.remove(unit);
                notifyDataSetChanged();
            }
        }
        );
    }
    @Override
    public int getItemCount() {
        return painRecordUnits.size();
    }
    public void addUnits(List<PainRecordUnit> results) {
        painRecordUnits = results;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private RvLayoutBinding binding;
        public ViewHolder(RvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
