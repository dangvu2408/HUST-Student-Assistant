package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_01.Constructor.TimeTable;
import com.example.app_01.R;

import java.util.ArrayList;

public class CustomAdapterTimetable extends BaseAdapter {
    final private Context context;
    final private ArrayList<TimeTable> itemList;

    public CustomAdapterTimetable(Context context, ArrayList<TimeTable> itemList) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.timetable_listitem, parent, false);
        }
        TimeTable currentTKB = itemList.get(position);
        TextView startTime = listItem.findViewById(R.id.startTime);
        TextView endTime = listItem.findViewById(R.id.endTime);
        TextView classInfor  = listItem.findViewById(R.id.classinfo);
        TextView classAddress = listItem.findViewById(R.id.phonghoc);
        String classaddress = "";
        String classinformation = currentTKB.getMahocphan() + " - " + currentTKB.getTenlop() + " - " + currentTKB.getMalop();
        classInfor.setText(classinformation);
        String time = currentTKB.getThoigian();
        String thu = time.substring(4, 5);

        if (time.equals("") || time.length() == 1 || !time.contains(",") || !time.contains("-")) {
            startTime.setText("");
            endTime.setText("");
        } else {
            int i = time.indexOf(",");
            int j = time.indexOf("-");
            startTime.setText(time.substring(i + 1, j - 1));
            endTime.setText(time.substring(j + 2));


            if (time.substring(i + 1, j - 1).equals("6h45") ||
                    time.substring(i + 1, j - 1).equals("7h30") ||
                    time.substring(i + 1, j - 1).equals("8h25") ||
                    time.substring(i + 1, j - 1).equals("8h0") ||
                    time.substring(i + 1, j - 1).equals("9h20") ||
                    time.substring(i + 1, j - 1).equals("9h15") ||
            time.substring(i + 1, j - 1).equals("10h15") ||
                    time.substring(i + 1, j - 1).equals("11h0")) {
                classaddress = "Sáng thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            } else if (time.substring(i + 1, j - 1).equals("12h30") ||
                    time.substring(i + 1, j - 1).equals("13h0") ||
                    time.substring(i + 1, j - 1).equals("13h15") ||
                    time.substring(i + 1, j - 1).equals("14h0") ||
                    time.substring(i + 1, j - 1).equals("14h10") ||
                    time.substring(i + 1, j - 1).equals("15h5") ||
                    time.substring(i + 1, j - 1).equals("15h30") ||
                    time.substring(i + 1, j - 1).equals("16h0") ||
                    time.substring(i + 1, j - 1).equals("16h45")) {
                classaddress = "Chiều thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            }
            else if (time.substring(i + 1, j - 1).equals("17h45") ||
                    time.substring(i + 1, j - 1).equals("18h0") ||
                    time.substring(i + 1, j - 1).equals("18h30")) {
                classaddress = "Tối thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            }
            classAddress.setText(classaddress);
        }

        return listItem;
    }
}
