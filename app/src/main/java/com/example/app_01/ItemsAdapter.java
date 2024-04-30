package com.example.app_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private List<ReItems> listItems;
    public ItemsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ReItems> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        ReItems items = listItems.get(position);
        if (items == null) {
            return;
        }
        holder.icon.setImageResource(items.getResouceIcon());
        holder.text.setText(items.getReText());
    }

    @Override
    public int getItemCount() {
        if(listItems != null) {
            return listItems.size();
        }
        return 0;
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView text;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.re_icon);
            text = itemView.findViewById(R.id.re_text);
        }
    }
}
