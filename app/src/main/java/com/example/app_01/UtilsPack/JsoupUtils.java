package com.example.app_01.UtilsPack;

import android.content.Context;

import com.example.app_01.ConstValue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.HashMap;

public class JsoupUtils {
    public static JsoupUtils instance;
    private Context context;
    private HashMap<String, String> cookies;
    public static JsoupUtils getInstance() {
        if (instance == null) {
            instance = new JsoupUtils();
        }
        return instance;
    }

    public boolean login(Context context, String str00, String str01, String str02, String str03, HashMap<String, String> hashMap, int i) {
        this.context = context;
        this.cookies = hashMap;
        String string00 = "0_2944,1_67,1_68,1_69,0_2949,0_2,0_2860,1_206,0_2865,1_207,1_204,1_203,https://fonts.googleapis.com/css?family=Arimo,../Content/bootstrap/css/bootstrap.min.css,../Content/bootstrap/css/bootstrap-combobox.css,../Content/docs.min.css,../Content/Site.css";
        String string01 = "1_11,1_12,1_23,1_63,1_14,1_15,1_48,1_17,1_24,1_33,1_180,1_185,1_186,1_181,1_200,1_179,1_32";
        String string02 = "ctl00$ctl00$TopMenuPane$ctl10$menuTop";
        boolean x = true;
        if (i == 0) {
            string00 = "0_2870,1_67,1_68,1_69,0_2875,0_2,0_2786,1_206,0_2791,1_207,1_204,1_203,https://fonts.googleapis.com/css?family=Arimo,../Content/bootstrap/css/bootstrap.min.css,../Content/bootstrap/css/bootstrap-combobox.css,../Content/docs.min.css,../Content/Site.css";
            string01 = "1_10,1_11,1_22,1_63,1_13,1_14,1_29,1_48,1_16,1_23,1_33,1_180,1_185,1_186,1_181,1_200,1_179,1_32";
        } else if (i == 1) {
            string02 = "ctl00$ctl00$TopMenuPane$ctl09$menuTop";
        }
        try {
            Connection data00 = Jsoup.connect(ConstValue.BASE_URL).cookies(hashMap)
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("__VIEWSTATE", Utils.getInstance().getValueFromSharedPreferences(context, "share_preferences_form_data_login", "share_preferences_form_data_login_3"))
                    .data("__VIEWSTATEGENERATOR", "CD85D8D2")
                    .data("__EVENTVALIDATION", Utils.getInstance().getValueFromSharedPreferences(context, "share_preferences_form_data_login", "share_preferences_form_data_login_5"))
                    .data("ctl00$ctl00$TopMenuPane$menuTop", "{&quot;selectedItemIndexPath&quot;:&quot;&quot;,&quot;checkedState&quot;:&quot;&quot;}")
                    .data(string02, "{&quot;selectedItemIndexPath&quot;:&quot;&quot;,&quot;checkedState&quot;:&quot;&quot;}")
                    .data("ctl00$ctl00$contentPane$MainPanel$MainContent$chbParents", "I");
            Connection data01 = data00.data("ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName$State", "{&quot;rawValue&quot;:&quot;" + str00 + "&quot;,&quot;validationState&quot;:&quot;&quot;}")
                    .data(ConstValue.USERNAME_INPUT_VALUE, str00);
            Connection.Response execute = data01.data("ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword$State", "{&quot;rawValue&quot;:&quot;" + str01 + "&quot;,&quot;validationState&quot;:&quot;&quot;}")
                    .data(ConstValue.PASSWORD_INPUT_VALUE, str01)
                    .data("ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB$State", "{&quot;validationState&quot;:&quot;&quot;}")
                    .data(ConstValue.CAPTCHA_INPUT_VALUE, str02)
                    .data("ctl00$ctl00$contentPane$MainPanel$MainContent$btLogin", "[\"0\":\"Đăng+nhập\",\"1\":\"\"]")
                    .data("ctl00$ctl00$contentPane$MainPanel$MainContent$hfInput", str03)
                    .data("DXScript", string01)
                    .data("DXCss", string00)
                    .followRedirects(x).method(Connection.Method.POST).userAgent(ConstValue.USER_AGENT).execute();
            if (execute == null || execute.statusCode() != 200 || !execute.url().toString().equals("https://ctt-sis.hust.edu.vn/")) {
                return false;
            }
            try {
                JsonUtils.getInstance().parseStudentInfo(context, execute.parse());
                getTimeTable();
                getClassInfo();
                getTOEICScore();
                getStudentScore();
                getGPACPA();
                getTuition();
                return true;
            } catch (Exception e) {

            }

        } catch (Exception e) {
            x = false;
            e.printStackTrace();
            return x;
        }
        return x;
    }

    public void getTimeTable() {
        try {
            JsonUtils.getInstance().parseTimeTable(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/Timetables.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getClassInfo() {
        try {
            JsonUtils.getInstance().parseClassInfo(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/StudentGroupInfo.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTOEICScore() {
        try {
            JsonUtils.getInstance().parseTOEICScore(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/ToeicMarks.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStudentScore() {
        try {
            JsonUtils.getInstance().parseStudentScore(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/StudentCourseMarks.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGPACPA() {
        try {
            JsonUtils.getInstance().parseGPACPA(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/StudentCourseMarks.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTuition() {
        try {
            JsonUtils.getInstance().parseTuition(this.context, Jsoup.connect("https://ctt-sis.hust.edu.vn/Students/CheckTuition.aspx").cookies(this.cookies).method(Connection.Method.GET).userAgent(ConstValue.USER_AGENT).execute().parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
