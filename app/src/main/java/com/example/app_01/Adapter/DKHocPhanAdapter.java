package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.app_01.Constructor.HocPhanDK;
import com.example.app_01.R;

import java.util.List;

public class DKHocPhanAdapter extends BaseAdapter {
    private Context context;
    private List<HocPhanDK> item;
    public DKHocPhanAdapter(Context context, List<HocPhanDK> item) {
        this.context = context;
        this.item = item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.hocphandk_listitem, parent, false);
        }
        HocPhanDK current = item.get(position);
        TextView tenvaMaHP = listItem.findViewById(R.id.tenvaMaHP);
        TextView ngaydangki = listItem.findViewById(R.id.ngaydangki);
        TextView trangthaidk = listItem.findViewById(R.id.trangthaidk);
        TextView soTC = listItem.findViewById(R.id.soTC);
        CheckBox check = listItem.findViewById(R.id.check);
        tenvaMaHP.setText(current.getMaHPDK() + " - " + current.getTenHPDK());
        ngaydangki.setText("Ngày đăng ký: " + current.getNgayDK());
        trangthaidk.setText("TT đăng ký: " + current.getTTDK());
        soTC.setText("Số tín chỉ: " + current.getSoTCDK());
        check.setOnCheckedChangeListener(null);
        check.setChecked(current.isChecked());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                current.setChecked(isChecked);
            }
        });
        return listItem;
    }

    public void checkAll(boolean isChecked) {
        for (HocPhanDK item0 : item) {
            item0.setChecked(isChecked);
        }
        notifyDataSetChanged();
    }
}
