package com.example.delifood;

import static androidx.core.content.ContextCompat.startActivity;

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

import java.security.AccessControlContext;
import java.util.List;

public class RecItemItemAdapter extends RecyclerView.Adapter<RecItemItemAdapter.ItemViewHolder>{

    private static Context context;
    private List<RecItemItemAct> itemList;

    public RecItemItemAdapter(Context context, List<RecItemItemAct> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        RecItemItemAct item = itemList.get(position);

        // Set data to views
        holder.foodImg.setImageResource(item.getImageResource());
        holder.titleVal.setText(item.getItemTitle());
        holder.priceVal.setText(item.getItemprice());
        holder.caloriesVal.setText(item.getItemcalories());

        holder.contentLayout.setOnClickListener(v -> {
            context.startActivity(new Intent(context, IndvidualItemActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView titleVal;
        TextView priceVal;
        TextView caloriesVal;
        LinearLayout contentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            contentLayout=itemView.findViewById(R.id.itemll);

            foodImg = itemView.findViewById(R.id.item_img);
            titleVal = itemView.findViewById(R.id.item_title);
            priceVal = itemView.findViewById(R.id.item_price);
            caloriesVal=itemView.findViewById(R.id.item_calories);

        }
    }

}
