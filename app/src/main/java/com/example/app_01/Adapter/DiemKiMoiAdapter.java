package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.InputGrade;
import com.example.app_01.R;

import java.util.List;

public class DiemKiMoiAdapter extends BaseAdapter {
    private Context context;
    private List<InputGrade> item;
    public DiemKiMoiAdapter(Context context, List<InputGrade> item) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.nhapdiemkimoi_listitem, parent, false);
        }

        InputGrade current = item.get(position);
        TextView malopVatenlop = listItem.findViewById(R.id.malopVatenlop);
        TextView trongsoQT = listItem.findViewById(R.id.trongsoQT);
        TextView diemvaTTQT = listItem.findViewById(R.id.diemvaTTQT);
        TextView diemThivaTTDT = listItem.findViewById(R.id.diemThivaTTDT);
        malopVatenlop.setText("(" + current.getMasinhvien() + ") - " + current.getMalop() + " - " + current.getTenlop());
        trongsoQT.setText("Trọng số quá trình: " + current.getTrongsoqt());
        diemvaTTQT.setText("Điểm QT: " + current.getDiemqt() + " - Trạng thái: " + current.getTtdiemqt());
        diemThivaTTDT.setText("Điểm thi: " + current.getDiemthi() + " - Trạng thái: " + current.getTtdiemthi());
        return listItem;
    }
}
