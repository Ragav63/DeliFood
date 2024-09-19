package com.example.delifood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecItemIndividualItemAdapter extends RecyclerView.Adapter<RecItemIndividualItemAdapter.ItemViewHolder>{

    private static Context context;
    private List<RecItemIndividualItemAct> itemList;

    public RecItemIndividualItemAdapter(Context context, List<RecItemIndividualItemAct> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individualitem_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        RecItemIndividualItemAct item = itemList.get(position);

        // Set data to views
        holder.titleVal.setText(item.getItemTitle());
        holder.priceVal.setText(item.getItemprice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleVal;
        TextView priceVal;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            titleVal = itemView.findViewById(R.id.individualItemTitle);
            priceVal = itemView.findViewById(R.id.individualItemPrice);

        }
    }

}
