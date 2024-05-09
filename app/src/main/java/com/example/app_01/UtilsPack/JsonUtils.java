package com.example.app_01.UtilsPack;

import android.content.Context;

import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsonUtils {
    public static final String KEY_MA_SV = "maSV";
    public static JsonUtils instance;
    static JsonUtils getInstance() {
        if (instance == null) {
            instance = new JsonUtils();
        }
        return instance;
    }

    public void parseStudentInfo(Context context, Document document) {
        try {
            Elements select = document.select("div[class=row]");
            Element element = select.get(1);
            // get the student information by ID on website: https://ctt-sis.hust.edu.vn/
            String substring01 = element.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_lbMSSV").text().substring(5);
            String[] split01 = element.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_lbTextInfo1").text().split(":");
            String substring02 = split01[1].substring(0, split01[1].length() - 16);
            String substring03 = split01[2].substring(0, split01[2].length() - 13);
            String substring04 = split01[3].substring(0, split01[3].length() - 14);
            String substring05 = split01[4].substring(0, split01[4].length() - 19);
            String substring06 = split01[5].substring(0, split01[5].length() - 20);
            String substring07 = split01[6].substring(1);
            String[] split02 = element.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_lbTextInfo2").text().split(":");
            String substring08 = split02[1].substring(0, split02[1].length() - 5);
            String substring09 = split02[2].substring(0, split02[2].length() - 10);
            String substring10 = split02[3].substring(0, split02[3].length() - 7);
            String str = split02[4];
            Element element1 = select.get(5);
            String[] split03 = element1.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_lbTextInfo5").text().split(":");
            String substring11 = split03[1].substring(0, split03[1].length() - 14);
            String substring12 = split03[2].substring(0, split03[2].length() - 9);
            String substring13 = split03[3].substring(0, split03[3].length() - 10);
            String substring14 = split03[4].substring(0, split03[4].length() - 9);
            String substring15 = split03[5].substring(0, split03[5].length() - 11);
            String substring16 = split03[6].substring(0, split03[6].length() - 10);
            String substring17 = split03[7].substring(0, split03[7].length() - 13);
            String str3 = substring17;
            String substring18 = split03[8].substring(0, split03[8].length() - 12);
            String substring19 = split03[9].substring(0, split03[9].length() - 7);
            String str4 = split03[10];
            String[] split04 = element1.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_lbTextInfo6").text().split(":");
            String substring20 = split04[1].substring(0, split04[1].length() - 13);
            String substring21 = split04[2].substring(0, split04[2].length() - 9);
            String substring22 = split04[3].substring(0, split04[3].length() - 15);
            String substring23 = split04[4].substring(0, split04[4].length() - 11);
            String substring24 = split04[5].substring(0, split04[5].length() - 10);
            String substring25 = split04[6].substring(0, split04[6].length() - 13);
            String substring26 = split04[7].substring(0, split04[7].length() - 12);
            String substring27 = split04[8].substring(0, split04[8].length() - 7);
            String str5 = split04[9];
            // "push" the substring into a JSON Object

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MSSV", substring01);
            jsonObject.put("Ho_ten", substring02);
            jsonObject.put("Nam_vao_truong", substring03);
            jsonObject.put("Bac_dao_tao", substring04);
            jsonObject.put("Chuong_tring", substring05);
            jsonObject.put("Khoa_vien", substring06);
            jsonObject.put("Tinh_trang", substring07);
            jsonObject.put("Gioi_tinh", substring08);
            jsonObject.put("Lop", substring09);
            jsonObject.put("Khoa", substring10);
            jsonObject.put("Email", str);
            jsonObject.put("Dan_toc", substring11);
            jsonObject.put("Nam_tot_nghiep_c3", substring12);
            jsonObject.put("Dia_chi", substring13);
            jsonObject.put("CCCD", substring14);
            jsonObject.put("Noi_cap", substring15);
            jsonObject.put("Ho_ten_bo", substring16);
            jsonObject.put("Nam_sinh_bo", str3);
            jsonObject.put("Nghe_nghiep_bo", substring18);
            jsonObject.put("SDT_bo", substring19);
            jsonObject.put("Email_bo", str4);
            jsonObject.put("Ton_giao", substring20);
            jsonObject.put("Truong_THPT", substring21);
            jsonObject.put("Ho_khau", substring22);
            jsonObject.put("SDT", substring23);
            jsonObject.put("Ho_ten_me", substring24);
            jsonObject.put("Nam_sinh_me", substring25);
            jsonObject.put("Nghe_nghiep_me", substring26);
            jsonObject.put("SDT_me", substring27);
            jsonObject.put("Email_me", str5);

            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_thong_tin_sinh_vien", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
