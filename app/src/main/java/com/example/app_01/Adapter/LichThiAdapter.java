package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.LichThi;
import com.example.app_01.R;

import java.util.List;

public class LichThiAdapter extends BaseAdapter {
    private Context context;
    private List<LichThi> item;
    public LichThiAdapter(Context context, List<LichThi> item) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.lichthi_listitem, parent, false);
        }

        LichThi current = item.get(position);
        TextView gioThi = listItem.findViewById(R.id.gioThi);
        TextView tenvaMaHP = listItem.findViewById(R.id.tenvaMaHP);
        TextView maLopHoc = listItem.findViewById(R.id.maLopHoc);
        TextView diaDiemThi = listItem.findViewById(R.id.diaDiemThi);
        TextView ngayThi = listItem.findViewById(R.id.ngayThi);
        if (current.getKipThi().equals("Kíp 1")) {
            gioThi.setText("07:00");
        }
        if (current.getKipThi().equals("Kíp 2")) {
            gioThi.setText("09:30");
        }
        if (current.getKipThi().equals("Kíp 3")) {
            gioThi.setText("12:30");
        }
        if (current.getKipThi().equals("Kíp 4")) {
            gioThi.setText("15:00");
        }
        if (current.getKipThi().equals("Kíp 5")) {
            gioThi.setText("17:30");
        }
        tenvaMaHP.setText(current.getMaHP() + " - " + current.getTenHP());
        maLopHoc.setText("Mã lớp: " + current.getMaLop());
        diaDiemThi.setText("Địa điểm: " + current.getPhongThi());
        ngayThi.setText("Ngày thi: " + current.getNgayThi());
        return listItem;
    }
}
