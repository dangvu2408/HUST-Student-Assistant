package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.ToeicScore;
import com.example.app_01.R;

import java.util.List;

public class ToeicAdapter extends BaseAdapter {
    final private Context context;
    final private List<ToeicScore> itemList;
    public ToeicAdapter(Context context, List<ToeicScore> itemList) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.toeic_score_item, parent, false);
        }
        ToeicScore current = itemList.get(position);
        TextView hocki = listItem.findViewById(R.id.hocki);
        TextView ghichu = listItem.findViewById(R.id.ghichu);
        TextView hoten = listItem.findViewById(R.id.namestudent);
        TextView masovangaysinh = listItem.findViewById(R.id.mssvandns);
        TextView nghe = listItem.findViewById(R.id.listenscore);
        TextView doc = listItem.findViewById(R.id.redingscore);
        TextView tong = listItem.findViewById(R.id.totalscore);
        hocki.setText(current.getHocki() + " (" + current.getNgaythi() + ")");
        hoten.setText(current.getHotensv());
        hoten.setVisibility(View.GONE);
        masovangaysinh.setText(current.getMaso() + " " + current.getDob());
        nghe.setText("Điểm nghe: " + current.getNghe());
        doc.setText("Điểm đọc: " + current.getDoc());
        tong.setText(current.getTong());
        ghichu.setText(current.getNote());
        return listItem;
    }
}
