package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_01.Constructor.CTDT;
import com.example.app_01.R;

import java.util.List;

public class CTDTAdapter extends BaseAdapter {
    private Context context;
    private List<CTDT> item;
    public CTDTAdapter(Context context, List<CTDT> item) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.chuongtrinh_listitem, parent, false);
        }

        CTDT current = item.get(position);
        TextView tenMaHp = listItem.findViewById(R.id.tenvaMaHP);
        TextView kihoc = listItem.findViewById(R.id.kihocDT);
        TextView tinchi = listItem.findViewById(R.id.tinchidt);
        TextView vienkhoa = listItem.findViewById(R.id.vienkhoa);
        TextView ghichu = listItem.findViewById(R.id.ghichuloaiHP);
        TextView dchudso = listItem.findViewById(R.id.diemchudiemso);
        ImageView check = listItem.findViewById(R.id.checkboxImg);
        ImageView uncheck = listItem.findViewById(R.id.uncheckboxImg);
        tenMaHp.setText(current.getMaHPctdt() + " - " + current.getTenHPCTDT());
        if (current.getKyhocCTDT() == null) {
            kihoc.setText("Kì học: Tùy chọn");
        } else {
            kihoc.setText("Kì học (gợi ý): " + current.getKyhocCTDT());
        }
        tinchi.setText("Số TC đào tạo: " + current.getTinchiDT());
        vienkhoa.setText("Viện/Khoa: " + current.getVienkhoaDT());
        ghichu.setText("Ghi chú loại HP: " + current.getGhichuHPH());
        if (current.getDiemsoCTDT() == null && current.getDienchuCTDT() == null) {
            dchudso.setText("Điểm chữ: N/A - Điểm số: N/A");
        } else {
            dchudso.setText("Điểm chữ: " + current.getDienchuCTDT() + " - " + "Điểm số: " + current.getDiemsoCTDT());
        }
        if (current.getMaHPhoc().equals("")) {
            check.setVisibility(View.GONE);
            uncheck.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.VISIBLE);
            uncheck.setVisibility(View.GONE);
        }
        return listItem;
    }
}
