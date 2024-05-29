package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.HocPhi;
import com.example.app_01.R;

import java.util.List;

public class HocPhiAdapter extends BaseAdapter {
    private Context context;
    private List<HocPhi> item;
    public HocPhiAdapter(Context context, List<HocPhi> item) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.tienhocphan_listitem, parent, false);
        }

        HocPhi current = item.get(position);
        TextView tenMaHp = listItem.findViewById(R.id.tenvaMaHP);
        TextView tienTrenTC = listItem.findViewById(R.id.hocphitrentinchi);
        TextView tcHocPhi = listItem.findViewById(R.id.tinchihocphi);
        TextView tongHP = listItem.findViewById(R.id.tongTienHP);
        TextView hesoHP = listItem.findViewById(R.id.hesoHP);
        TextView trangthaiDK = listItem.findViewById(R.id.trangthaiDK);
        TextView ghichu = listItem.findViewById(R.id.ghichuHP);
        tenMaHp.setText(current.getMaHocPhan() + " - " + current.getTenHocphan());
        tienTrenTC.setText("Số tiền / 1TC: " + current.getTienTrenMotTC());
        tcHocPhi.setText("Số TC học phí: " + current.getTinChihp());
        tongHP.setText(current.getTongTienHP());
        hesoHP.setText("Hệ số học phí lớp: " + current.getHeSoHP());
        trangthaiDK.setText("Trạng thái đăng kí: " + current.getTrangThaiDK() + " - " + current.getLoaiDK());
        ghichu.setText("Ghi chú: " + current.getGhichU());
        return listItem;
    }
}
