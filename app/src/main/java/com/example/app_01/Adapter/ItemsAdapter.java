package com.example.app_01.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.app_01.AppCompat.TKBActivity;
import com.example.app_01.Constructor.ReItems;
import com.example.app_01.R;

import java.util.List;

public class ItemsAdapter extends ArrayAdapter<ReItems> {
    private Context context;
    private List<ReItems> listItems;

    public ItemsAdapter(Context context, List<ReItems> listItems) {
        super(context, 0, listItems);
        this.context = context;
        this.listItems = listItems;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        }
        ReItems current = listItems.get(position);
        ImageView img = listItem.findViewById(R.id.re_icon);
        TextView txt = listItem.findViewById(R.id.re_text);
        TextView txtdes = listItem.findViewById(R.id.re_des_text);
        img.setImageResource(current.getResouceIcon());
        txt.setText(current.getReText());
        txtdes.setText(current.getTextdes());
        CardView cardView = listItem.findViewById(R.id.cardviewClick);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    switch (position) {
                        case 0:
                            context.startActivity(new Intent(context, TKBActivity.class));
                            break;
                        case 1:
                            context.startActivity(new Intent(context, TKBActivity.class));
                            break;
                        case 2:
                            context.startActivity(new Intent(context, TKBActivity.class));
                            break;
                        case 3:
                            context.startActivity(new Intent(context, TKBActivity.class));
                            break;
                    }
                }
            }
        });
        return listItem;
    }
}
