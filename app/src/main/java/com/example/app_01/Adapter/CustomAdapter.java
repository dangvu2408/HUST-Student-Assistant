package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.SinhVien;
import com.example.app_01.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    final private Context context;
    final private List<SinhVien> itemList;

    public CustomAdapter(Context context, List<SinhVien> itemList) {
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

        SinhVien currentStudent = itemList.get(position);
        TextView listnumber = listItem.findViewById(R.id.listnumber);
        TextView hoten = listItem.findViewById(R.id.namestudent);
        TextView masosv = listItem.findViewById(R.id.idstudent);
        String hotenFull = currentStudent.getHo() + " " + currentStudent.getDem() + " " + currentStudent.getTen();
        hoten.setText(hotenFull);
        masosv.setText(currentStudent.getMSSV());
        listnumber.setText(String.valueOf(position + 1));
        return listItem;
    }
}
