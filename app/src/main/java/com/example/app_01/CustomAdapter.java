package com.example.app_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    final private Context context;
    final private List<ListViewItem> itemList;

    public CustomAdapter(Context context, List<ListViewItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
        }

        ListViewItem currentStudent = itemList.get(position);

        TextView numberlistTextView = listItem.findViewById(R.id.listnumber);
        numberlistTextView.setText(String.valueOf(currentStudent.getNumberlist()));

        TextView hoTenTextView = listItem.findViewById(R.id.namestudent);
        hoTenTextView.setText(currentStudent.getHoTen());

        TextView mssvTextView = listItem.findViewById(R.id.idstudent);
        mssvTextView.setText(currentStudent.getMSSV());

        return listItem;
    }
}
