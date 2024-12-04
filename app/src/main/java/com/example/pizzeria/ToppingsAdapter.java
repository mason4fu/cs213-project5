package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This adapter is responsible for populating the RecyclerView with a list of available pizza toppings.
 *
 * @author YU
 * @author JOHNATHAN CHAN
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingViewHolder> {

    private final Context context;
    private final Topping[] toppings;
    private final OnToppingClickListener listener;

    public interface OnToppingClickListener {
        void onToppingClick(Topping topping);
    }

    public ToppingsAdapter(Context context, Topping[] toppings, OnToppingClickListener listener) {
        this.context = context;
        this.toppings = toppings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topping_item, parent, false);
        return new ToppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingViewHolder holder, int position) {
        Topping topping = toppings[position];
        holder.toppingName.setText(topping.name());

        holder.itemView.setOnClickListener(v -> listener.onToppingClick(topping));
    }

    @Override
    public int getItemCount() {
        return toppings.length;
    }

    public static class ToppingViewHolder extends RecyclerView.ViewHolder {
        TextView toppingName;

        public ToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            toppingName = itemView.findViewById(R.id.toppingName);
        }
    }
}