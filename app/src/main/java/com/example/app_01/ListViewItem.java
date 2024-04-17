package com.example.app_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewItem extends BaseAdapter {
    Context context;
    String namestudent[], numberlist[], idstudent[];
    LayoutInflater inflater;
    public ListViewItem(Context context, String[] namestudent, String[] numberlist, String[] idstudent) {
        super();
        this.context = context;
        this.namestudent = namestudent;
        this.numberlist = numberlist;
        this.idstudent = idstudent;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return namestudent.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView	=	inflater.inflate(R.layout.listview_item, null);
        TextView name = (TextView) convertView.findViewById(R.id.namestudent);
        TextView nums = (TextView) convertView.findViewById(R.id.listnumber);
        TextView id = (TextView) convertView.findViewById(R.id.idstudent);
        name.setText(namestudent[position]);
        nums.setText(numberlist[position]);
        id.setText(idstudent[position]);
        return convertView;
    }
}
