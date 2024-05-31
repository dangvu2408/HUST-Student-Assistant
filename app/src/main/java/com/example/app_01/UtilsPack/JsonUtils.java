package com.example.app_01.UtilsPack;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

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
            String substring02 = split01[1].substring(1, split01[1].length() - 16);
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

    public void parseTimeTable(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvStudentRegister_DXMainTable").getElementsByClass("dxgvDataRow_Mulberry").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dxgv").first().text();
                String str02 = next.select("td.dxgv").get(1).text();
                String str03 = next.select("td.dxgv").get(2).text();
                String str04 = next.select("td.dxgv").get(3).text();
                String str05 = next.select("td.dxgv").get(4).text();
                String str06 = next.select("td.dxgv").get(5).text();
                String str07 = next.select("td.dxgv").get(6).text();
                String str08 = next.select("td.dxgv").get(7).text();
                String str09 = next.select("td.dxgv").get(8).text();
                String str10 = next.select("td.dxgv").get(9).text();
                String str11 = next.select("td.dxgv").get(10).text();
                String str12 = next.select("td.dxgv").get(11).text();
                String str13 = next.select("td.dxgv").get(12).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Thoi_gian", str01);
                jsonObject.put("Tuan_hoc", str02);
                jsonObject.put("Phong_hoc", str03);
                jsonObject.put("Ma_lop", str04);
                jsonObject.put("Loai_lop", str05);
                jsonObject.put("Nhom", str06);
                jsonObject.put("Ma_HP", str07);
                jsonObject.put("Ten_lop", str08);
                jsonObject.put("Ghi_chu", str09);
                jsonObject.put("Hinh_thuc_day", str10);
                jsonObject.put("Giang_vien", str11);
                jsonObject.put("Link_online", str12);
                jsonObject.put("Code_teams", str13);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_tkb", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseClassInfo(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvStudents_DXMainTable").getElementsByClass("dxgvDataRow").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dx-nowrap").first().text();
                String str02 = next.select("td.dx-nowrap").get(1).text();
                String str03 = next.select("td.dx-nowrap").get(2).text();
                String str04 = next.select("td.dx-nowrap").get(3).text();
                String str05 = next.select("td.dx-nowrap").get(4).text();
                String str06 = next.select("td.dx-nowrap").get(5).text();
                String str07 = next.select("td.dx-nowrap").get(6).text();
                String str08 = next.select("td.dx-nowrap").get(7).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("maSV", str01);
                jsonObject.put("hoSV", str02);
                jsonObject.put("demSV", str03);
                jsonObject.put("tenSV", str04);
                jsonObject.put("ngaysinh", str05);
                jsonObject.put("tenlop", str06);
                jsonObject.put("ctdt", str07);
                jsonObject.put("trangthai", str08);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_danh_sach_lop_sv", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseTOEICScore(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvStudents").getElementsByClass("dxgvDataRow").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dx-nowrap").first().text();
                String str02 = next.select("td.dx-nowrap").get(1).text();
                String str03 = next.select("td.dx-nowrap").get(2).text();
                String str04 = next.select("td.dx-nowrap").get(3).text();
                String str05 = next.select("td.dx-nowrap").get(4).text();
                String str06 = next.select("td.dx-nowrap").get(5).text();
                String str07 = next.select("td.dx-nowrap").get(6).text();
                String str08 = next.select("td.dx-nowrap").get(7).text();
                String str09 = next.select("td.dx-nowrap").get(8).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("masoSV", str01);
                jsonObject.put("hotenSV", str02);
                jsonObject.put("ngaySinh", str03);
                jsonObject.put("hocKi", str04);
                jsonObject.put("ghiChu", str05);
                jsonObject.put("ngayThi", str06);
                jsonObject.put("diemNghe", str07);
                jsonObject.put("diemDoc", str08);
                jsonObject.put("tongDiem", str09);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_diem_thi_toeic", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseStudentScore(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvCourseMarks").getElementsByClass("dxgvDataRow").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dx-nowrap").first().text();
                String str02 = next.select("td.dx-nowrap").get(1).text();
                String str03 = next.select("td.dx-nowrap").get(2).text();
                String str04 = next.select("td.dx-nowrap").get(3).text();
                String str05 = next.select("td.dx-nowrap").get(4).text();
                String str06 = next.select("td.dx-nowrap").get(5).text();
                String str07 = next.select("td.dx-nowrap").get(6).text();
                String str08 = next.select("td.dx-nowrap").get(7).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("HocKi", str01);
                jsonObject.put("MaHocPhan", str02);
                jsonObject.put("TenHocPhan", str03);
                jsonObject.put("TinChi", str04);
                jsonObject.put("LopHoc", str05);
                jsonObject.put("diemQT", str06);
                jsonObject.put("diemThi", str07);
                jsonObject.put("diemChu", str08);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_diem_thi_ca_nhan", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseGPACPA(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvResults").getElementsByClass("dxgvDataRow").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dx-nowrap").first().text();
                String str02 = next.select("td.dx-nowrap").get(1).text();
                String str03 = next.select("td.dx-nowrap").get(2).text();
                String str04 = next.select("td.dx-nowrap").get(3).text();
                String str05 = next.select("td.dx-nowrap").get(4).text();
                String str06 = next.select("td.dx-nowrap").get(5).text();
                String str07 = next.select("td.dx-nowrap").get(6).text();
                String str08 = next.select("td.dx-nowrap").get(7).text();
                String str09 = next.select("td.dx-nowrap").get(8).text();
                String str10 = next.select("td.dx-nowrap").get(9).text();
                String str11 = next.select("td.dx-nowrap").get(10).text();
                String str12 = next.select("td.dx-nowrap").get(11).text();
                String str13 = next.select("td.dx-nowrap").get(12).text();
                String str14 = next.select("td.dx-nowrap").get(13).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("hockihoc", str01);
                jsonObject.put("gpa", str02);
                jsonObject.put("cpa", str03);
                jsonObject.put("tinchiqua", str04);
                jsonObject.put("tinchitichluy", str05);
                jsonObject.put("tinchino", str06);
                jsonObject.put("tinchidk", str07);
                jsonObject.put("trinhdo", str08);
                jsonObject.put("canhbao", str09);
                jsonObject.put("thieudiem", str10);
                jsonObject.put("khongtinh", str11);
                jsonObject.put("ctdtsv", str12);
                jsonObject.put("dukienxlht", str13);
                jsonObject.put("xulichinhthuc", str14);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_diem_gpa_cpa", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseTuition(Context context, Document document) {
        try {
            JSONObject jsonObject = new JSONObject();
            Element element1 = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_rpEditTables_ASPxCallbackPanel1_gvCourseRegister");
//            Element element2 = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_rpEditTables_ASPxCallbackPanel1_gvTuitionTerm");
            Element element3 = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_rpEditTables_ASPxCallbackPanel1_TuitionInfo");
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> iterator1 = element1.getElementsByClass("dxgvDataRow_Mulberry").iterator();
//            Iterator<Element> iterator2 = element2.getElementsByClass("dxgvDataRow_Mulberry").iterator();
            while (iterator1.hasNext()) {
                Element next = iterator1.next();
                String str01 = next.select("td.dxgv").first().text();
                String str02 = next.select("td.dxgv").get(1).text();
                String str03 = next.select("td.dxgv").get(2).text();
                String str04 = next.select("td.dxgv").get(3).text();
                String str05 = next.select("td.dxgv").get(4).text();
                String str06 = next.select("td.dxgv").get(5).text();
                String str07 = next.select("td.dxgv").get(6).text();
                String str08 = next.select("td.dxgv").get(7).text();
                String str09 = next.select("td.dxgv").get(8).text();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("maHocPhanHP", str01);
                jsonObject1.put("tenHocPhanHP", str02);
                jsonObject1.put("soTien1TC", str03);
                jsonObject1.put("tinChiHocPhi", str04);
                jsonObject1.put("heSoHocPhi", str05);
                jsonObject1.put("tongTienHP", str06);
                jsonObject1.put("trangThaiDK", str07);
                jsonObject1.put("loaiDangKi", str08);
                jsonObject1.put("ghiChuHP", str09);
                jsonArray.put(jsonObject1);
            }

//            while (iterator2.hasNext()) {
//                Element next1 = iterator2.next();
//                String str11 = next1.select("td.dxgv").first().text();
//                String str12 = next1.select("td.dxgv").get(1).text();
//                String str13 = next1.select("td.dxgv").get(2).text();
//                String str14 = next1.select("td.dxgv").get(3).text();
//                String str15 = next1.select("td.dxgv").get(4).text();
//                String str16 = next1.select("td.dxgv").get(5).text();
//                JSONObject jsonObject2 = new JSONObject();
//                jsonObject2.put("loaiTienHP", str11);
//                jsonObject2.put("soTienHP", str12);
//                jsonObject2.put("heSodongHP", str13);
//                jsonObject2.put("thucDongHP", str14);
//                jsonObject2.put("ngayCapNhat", str15);
//                jsonObject2.put("ghiChuBangCN", str16);
//                jsonArray.put(jsonObject2);
//            }
            jsonObject.put("toanBoCongNoHP", jsonArray);
            String replace = element3.select("li").first().select("span").text().substring(23).replace("đ.", "");
            String replace2 = element3.select("li").select("span").get(1).text().replace("tại đây", "tại https://ctt.hust.edu.vn/DisplayWeb/DisplayBaiViet?baiviet=43430");
            jsonObject.put("soTienCanDongHP", replace);
            jsonObject.put("ghiChuSoTienHP", replace2);
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_thong_tin_hoc_phi", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseProgram(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_ProgramCoursePanel_gvStudentProgram").getElementsByClass("dxgvDataRow").iterator();
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dxgv").get(2).text();
                String str02 = next.select("td.dxgv").get(3).text();
                String str03 = next.select("td.dxgv").get(4).text();
                String str05 = next.select("td.dxgv").get(6).text();
                String str06 = next.select("td.dxgv").get(8).text();
                String str07 = next.select("td.dxgv").get(9).text();
                String str08 = next.select("td.dxgv").get(10).text();
                String str09 = next.select("td.dxgv").get(11).text();
                String str10 = next.select("td.dxgv").get(12).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("maHPCTDT", str01);
                jsonObject.put("tenHPCTDT", str02);
                jsonObject.put("kyhocCTDT", str03);
                jsonObject.put("tinchiDT", str05);
                jsonObject.put("maHPhoc", str06);
                jsonObject.put("ghichuHPH", str07);
                jsonObject.put("dienchuCTDT", str08);
                jsonObject.put("diemsoCTDT", str09);
                jsonObject.put("vienkhoaDT", str10);
                jsonArray.put(jsonObject);
            }
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_chuong_trinh_dao_tao_sv", jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseCoursesRegister(Context context, Document document) {
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject all = new JSONObject();
            Iterator<Element> element = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvRegisteredList").getElementsByClass("dxgvDataRow_Mulberry").iterator();
            Element semester = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvRegisteredList_DXTitle");
            String hk = semester.select("td.dxgvTitlePanel_Mulberry").first().text().substring(25, 30);
            Element sumTC = document.getElementById("ctl00_ctl00_contentPane_MainPanel_MainContent_gvRegisteredList_DXFooterRow");
            String str00 = sumTC.select("td.dxgv").get(4).text().substring(19);
            while (element.hasNext()) {
                Element next = element.next();
                String str01 = next.select("td.dxgv").first().text();
                String str02 = next.select("td.dxgv").get(1).text();
                String str03 = next.select("td.dxgv").get(2).text();
                String str04 = next.select("td.dxgv").get(3).text();
                String str05 = next.select("td.dxgv").get(4).text();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("maHPDK", str01);
                jsonObject.put("tenHPDK", str02);
                jsonObject.put("ngayDK", str03);
                jsonObject.put("TTDK", str04);
                jsonObject.put("soTCDK", str05);
                jsonArray.put(jsonObject);
            }

            all.put("thongtinDK", jsonArray);
            all.put("thongtinHK", hk);
            all.put("tongtinchi", str00);
            Utils.getInstance().saveToSharedPreferences(context, "share_preferences_data", "key_share_preferences_data_dang_ky_hoc_phan", all.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
