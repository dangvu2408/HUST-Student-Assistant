package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.app_01.Constructor.TimeTable;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import java.util.ArrayList;

public class CustomAdapterTimetable extends ArrayAdapter<TimeTable> {
     private final Context context;
     private final ArrayList<TimeTable> itemList;

    public CustomAdapterTimetable(Context context, ArrayList<TimeTable> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.timetable_listitem, parent, false);
        }


        TimeTable currentTKB = itemList.get(position);
        ImageView imgDot = listItem.findViewById(R.id.dotRed);
        TextView startTime = listItem.findViewById(R.id.startTime);
        TextView endTime = listItem.findViewById(R.id.endTime);
        TextView classInfor  = listItem.findViewById(R.id.classinfo);
        TextView classAddress = listItem.findViewById(R.id.phonghoc);
        String classaddress = "";
        String classinformation = currentTKB.getMahocphan() + " - " + currentTKB.getTenlop() + " - " + currentTKB.getMalop();
        classInfor.setText(classinformation);
        String time = currentTKB.getThoigian();



        if (time.equals("") || time.length() == 1 || !time.contains(",") || !time.contains("-")) {
            startTime.setText("");
            endTime.setText("");
        } else {
            int i = time.indexOf(",");
            int j = time.indexOf("-");
            String thu = time.substring(4, 5);
            startTime.setText(time.substring(i + 1, j - 1));
            endTime.setText(time.substring(j + 2));
            if (time.substring(i + 1, j - 1).equals("6h45") ||
                    time.substring(i + 1, j - 1).equals("7h0") ||
                    time.substring(i + 1, j - 1).equals("7h30") ||
                    time.substring(i + 1, j - 1).equals("8h25") ||
                    time.substring(i + 1, j - 1).equals("8h0") ||
                    time.substring(i + 1, j - 1).equals("9h20") ||
                    time.substring(i + 1, j - 1).equals("9h15") ||
            time.substring(i + 1, j - 1).equals("10h15") ||
                    time.substring(i + 1, j - 1).equals("11h0")) {
                classaddress = "Sáng thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            } else if (time.substring(i + 1, j - 1).equals("12h30") ||
                    (time.substring(i + 1, j - 1).equals("12h45") ||
                    time.substring(i + 1, j - 1).equals("13h0") ||
                    time.substring(i + 1, j - 1).equals("13h15") ||
                    time.substring(i + 1, j - 1).equals("14h0") ||
                    time.substring(i + 1, j - 1).equals("14h10") ||
                    time.substring(i + 1, j - 1).equals("15h5") ||
                    time.substring(i + 1, j - 1).equals("15h10") ||
                    time.substring(i + 1, j - 1).equals("15h15") ||
                    time.substring(i + 1, j - 1).equals("15h30") ||
                    time.substring(i + 1, j - 1).equals("16h0") ||
                    time.substring(i + 1, j - 1).equals("16h45"))) {
                classaddress = "Chiều thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            }
            else if (time.substring(i + 1, j - 1).equals("17h45") ||
                    time.substring(i + 1, j - 1).equals("18h0") ||
                    time.substring(i + 1, j - 1).equals("18h30")) {
                classaddress = "Tối thứ " + thu + ", " + currentTKB.getGhichu() + ", " + currentTKB.getPhonghoc();
            }
            classAddress.setText(classaddress);
        }


        String weekNow = Utils.getInstance().getWeek(context);
        String week = currentTKB.getTuanhoc();
        ArrayList arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        boolean x = false;
        String str = null;
        int i = 0, k = 1;
        while (i < week.length()) {
            if ((String.valueOf(week.charAt(i)).equals(",") || String.valueOf(week.charAt(i)).equals(".")) && !x) {
                arrayList.add(String.valueOf(stringBuilder).trim());
                stringBuilder = new StringBuilder();
            } else if (String.valueOf(week.charAt(i)).equals("-")) {
                str = stringBuilder.toString().trim();
                stringBuilder = new StringBuilder();
                x = true;
            } else if ((String.valueOf(week.charAt(i)).equals(",") || String.valueOf(week.charAt(i)).equals(".")) && x) {
                String trim = stringBuilder.toString().trim();
                for (int j = Integer.parseInt(str); j <= Integer.parseInt(trim); j++) {
                    arrayList.add(String.valueOf(j));
                }
                stringBuilder = new StringBuilder();
                x = false;
                str = null;
            } else if (i == week.length() - k) {
                stringBuilder.append(week.charAt(i));
                if (!x) {
                    arrayList.add(String.valueOf(stringBuilder));
                } else {
                    String trim1 = stringBuilder.toString().trim();
                    for (int m = Integer.parseInt(str); m <= Integer.parseInt(trim1); m++) {
                        arrayList.add(String.valueOf(m));
                    }
                }
            } else {
                stringBuilder.append(week.charAt(i));
            }
            i++;
        }

//        if (currentTKB.getLoailop().equals("TN")) {
//            for (int i = 0; i < week.length(); i++) {
//                if ((String.valueOf(week.charAt(i)).equals(",") || String.valueOf(week.charAt(i)).equals(".")) && !x) {
//                    arrayList.add(String.valueOf(stringBuilder).trim());
//                    stringBuilder = new StringBuilder();
//                } else if (String.valueOf(week.charAt(i)).equals("-")) {
//                    str = stringBuilder.toString().trim();
//                    stringBuilder = new StringBuilder();
//                    x = true;
//                } else if ((String.valueOf(week.charAt(i)).equals(",") || String.valueOf(week.charAt(i)).equals(".")) && x) {
//                    String trim = stringBuilder.toString().trim();
//                    for (int j = Integer.parseInt(str); j <= Integer.parseInt(trim); j++) {
//                        arrayList.add(String.valueOf(j));
//                    }
//                    stringBuilder = new StringBuilder();
//                    x = false;
//                    str = null;
//                } else {
//                    if (i == week.length() - 1) {
//                        stringBuilder.append(week.charAt(i));
//                        if (!x) {
//                            arrayList.add(String.valueOf(stringBuilder));
//                        } else {
//                            String trim0 = stringBuilder.toString().trim();
//                            for (int k = Integer.parseInt(str); k <= Integer.parseInt(trim0); k++) {
//                                arrayList.add(String.valueOf(k));
//                            }
//                        }
//                    } else {
//                        stringBuilder.append(week.charAt(i));
//                    }
//                }
//            }

        if (arrayList.size() > 0) {
            for (int a = 0; a < arrayList.size(); a++) {
                if (weekNow.equals(arrayList.get(a))) {
                    if (currentTKB.getLoailop().equals("TN")) {
                        listItem.setBackgroundResource(R.drawable.item_tkb_tn);
                    }
                }
            }
        }
        return listItem;
    }
}
