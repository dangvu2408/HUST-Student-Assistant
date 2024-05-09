package com.example.app_01.LoginPropeties;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.AppCompat.MainActivity;
import com.example.app_01.ConstValue;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.JsonUtils;
import com.example.app_01.UtilsPack.JsoupUtils;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextCaptcha;
    public HashMap<String, String> cookiesLogin;
    public Bitmap bitmap;
    public String loginCode;
    public boolean isUpdate;
    public HashMap<String, String> cookiesEncrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        editTextUsername = findViewById(R.id.userName);
        editTextPassword = findViewById(R.id.pass);
        editTextCaptcha = findViewById(R.id.inputCaptcha);
        Intent intent1 = getIntent();
        this.isUpdate = intent1.getStringExtra("type") != null && intent1.getStringExtra("type").equals("update");
        initLayout();
        Button loginBtn = findViewById(R.id.loginBtn);
        ImageButton updateCaptcha = findViewById(R.id.updateCaptcha);
        TextView guideText = findViewById(R.id.forgotPass);
        loginBtn.setOnClickListener(this);
        updateCaptcha.setOnClickListener(this);
        guideText.setOnClickListener(this);
    }

    public void onClick(View view) {
        int idLayout = view.getId();
        if (idLayout == R.id.loginBtn) {
            if (editTextUsername.getText().toString().equals("") || editTextPassword.getText().toString().equals("") || editTextCaptcha.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Vui lòng điền vào trường còn thiếu!", Toast.LENGTH_SHORT).show();
            } else if (!Utils.getInstance().isOnline()) {
                Toast.makeText(LoginActivity.this, "Không có kết nối mạng, vui lòng kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            } else {
                showDialogLogin(this);
            }
        } else if (idLayout == R.id.updateCaptcha) {
            initLayout();
        } else if (idLayout == R.id.forgotPass) {
            showDialogGuide(this);
        }
    }

    private void showDialogLogin(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.login_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);
        Button cancel = dialogView.findViewById(R.id.buttonCancel);
        Button next = dialogView.findViewById(R.id.buttonNext);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                if(!dialog.isShowing()) {
                    if (isUpdate) {
                        checkAccount();
                    } else {
                        String value = Utils.getInstance().getValueFromSharedPreferences(getApplicationContext(), "share_preferences_data", "key_share_preferences_is_first_time");
                        if (value == null || value.equals("")) {
                            Utils.getInstance().saveToSharedPreferences(getApplicationContext(), "share_preferences_data", "key_share_preferences_is_first_time", "1");
                        }
                    }
                    String md5 = LoginActivity.maHoaMD5(editTextUsername.getText() + "." + editTextPassword.getText());
                    HashMap hashMap = new HashMap<>();
                    hashMap.put("ma_hoa", md5);
                    hashMap.put("username", editTextUsername.getText().toString());
                    hashMap.put("password", editTextPassword.getText().toString());
                    hashMap.put("captcha", editTextCaptcha.getText().toString());
                    new Login().execute(new HashMap[]{hashMap});
                }
            }
        });
        dialog.show();
    }

    private void showDialogGuide(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.login_guide_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);
        ImageButton imageButton = dialogView.findViewById(R.id.closeBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void initLayout() {
        if (Utils.getInstance().isOnline()) {
            new ParseURL().execute(new Void[0]);
        } else {
        Toast.makeText(LoginActivity.this, "Không có kết nối mạng, vui lòng kết nối và thử lại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkAccount() {
        try {
            if(!new JSONObject(Utils.getInstance().getValueFromSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_user")).getString(JsonUtils.KEY_MA_SV).equals(editTextUsername.getText().toString())) {
                Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_hoc_phan_cai_thien", "");
                Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_hoc_phan_moi", "");
                Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_hoc_phan_khong_tinh_diem", "");
            }
        } catch (Exception e) {
            Log.e("RAKAN", "checkAccount: " + e.toString());
            e.printStackTrace();
        }
    }

    public static String maHoaMD5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void saveUser() {
        String obj01 = editTextUsername.getText().toString();
        String obj02 = editTextPassword.getText().toString();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", obj01);
            jsonObject.put("password", obj02);
            Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_user", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ParseURL extends AsyncTask<Void, Void, Boolean> {
        private ParseURL() {}
        protected Boolean doInBackground(Void... voids) {
            try {
                Connection.Response execute = Jsoup.connect(ConstValue.BASE_URL).method(Connection.Method.GET).execute();
                if(execute.statusCode() == 200) {
                    HashMap unused = LoginActivity.this.cookiesLogin = new HashMap<>();
                    LoginActivity.this.cookiesLogin.putAll(execute.cookies());
                    Document parse = execute.parse();
                    Bitmap unused2 = LoginActivity.this.bitmap = BitmapFactory.decodeStream(new URL(parse.getElementById(ConstValue.CAPTCHA_ID).absUrl("src")).openStream());
                    String str00 = parse.select("input[id=__VIEWSTATE]").first().attr("value");
                    String str01 = parse.select("input[id=__EVENTVALIDATION]").first().attr("value");
                    Utils.getInstance().saveToSharedPreferences(LoginActivity.this.getApplicationContext(), "share_preferences_form_data_login", "key_share_preferences_form_data_login_00", str00);
                    Utils.getInstance().saveToSharedPreferences(LoginActivity.this.getApplicationContext(), "share_preferences_form_data_login", "key_share_preferences_form_data_login_01", str01);
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                ImageView captchaImageView = findViewById(R.id.captchaImg);
                captchaImageView.setImageBitmap(LoginActivity.this.bitmap);
            } else {
                Toast.makeText(LoginActivity.this, "Kết nối lại mạng và thử lại...", Toast.LENGTH_SHORT).show();
            }
            if (LoginActivity.this.bitmap == null) {
                Toast.makeText(LoginActivity.this, "Trang CTT-SIS đang cập nhật, hiện tại app chưa đăng nhập được. Vui lòng thử lại sau...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Login extends AsyncTask<HashMap, Void, Boolean> {
        private Login() {}
        public Boolean doInBackground(HashMap... hashMaps) {
            boolean x = false;
            try {
                JSONObject jsonObject = new JSONObject(hashMaps[0]);
                String str00 = jsonObject.getString("username");
                String str01 = jsonObject.getString("password");
                String str02 = jsonObject.getString("captcha");
                String str03 = jsonObject.getString("ma_hoa");
                Connection.Response execute = Jsoup.connect("https://encode-decode.com/des-ecb-encrypt-online/").method(Connection.Method.GET).execute();
                HashMap unused = LoginActivity.this.cookiesEncrypt = new HashMap<>();
                LoginActivity.this.cookiesEncrypt.putAll(execute.cookies());
                Element elementId = Jsoup.connect("https://encode-decode.com/des-ecb-encrypt-online/")
                        .cookies(LoginActivity.this.cookiesEncrypt)
                        .data("encryption[algorithm]", "des-ecb")
                        .data("encryption[sourceText]", str01)
                        .data("encryption[destinationText]", "")
                        .data("encryption[secret]", str03)
                        .data("encryption[encrypt]", "")
                        .data("encryption[_token]", execute.parse().getElementById("encryption__token").attr("value"))
                        .followRedirects(true).method(Connection.Method.POST)
                        .userAgent(ConstValue.USER_AGENT).execute().parse().getElementById("encryption_destinationText");
                LoginActivity loginActivity = LoginActivity.this;
                String unused0 = loginActivity.loginCode = "{&quot;data&quot;:&quot;12|#|user|4|9|1" + str00 + "pass|4|25|1" + elementId.text() + "#&quot;}";
                x = JsoupUtils.getInstance().login(LoginActivity.this.getApplicationContext(), str00, str01, str02, LoginActivity.this.loginCode, LoginActivity.this.cookiesLogin, 0);
                if(!x) {
                    String unused1 = LoginActivity.this.loginCode = "{&quot;data&quot;:&quot;12|#|user|4|9|1" + str00 + "pass|4|45|1" + elementId.text() + "#&quot;}";
                    x = JsoupUtils.getInstance().login(LoginActivity.this.getApplicationContext(), str00, str01, str02, LoginActivity.this.loginCode, LoginActivity.this.cookiesLogin, 1);
                    if(!x) {
                        String unused2 = LoginActivity.this.loginCode = "{&quot;data&quot;:&quot;12|#|user|4|9|1" + str00 + "pass|4|33|1" + elementId.text() + "#&quot;}";
                        x = JsoupUtils.getInstance().login(LoginActivity.this.getApplicationContext(), str00, str01, str02, LoginActivity.this.loginCode, LoginActivity.this.cookiesLogin, 2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Boolean.valueOf(x);
        }

        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if(bool.booleanValue()) {
                LoginActivity.this.saveUser();
                Utils.getInstance().saveToSharedPreferences(getApplicationContext(), "share_preferences_data", "key_share_preferences_data_already_user_login", "1");
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            initLayout();
            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}

// cre source code: Le Xuan Nhat - HUST Student v.20200929
