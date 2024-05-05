package com.example.app_01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.AppCompat.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextCaptcha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button buttonLogin;
        // Xác định các thành phần trong layout
        editTextUsername = findViewById(R.id.userName);
        editTextPassword = findViewById(R.id.pass);
        editTextCaptcha = findViewById(R.id.inputCaptcha);
        buttonLogin = findViewById(R.id.loginBtn);

        // Thêm sự kiện cho nút đăng nhập
        buttonLogin.setOnClickListener(v -> {
            // Xử lý đăng nhập
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String captcha = editTextCaptcha.getText().toString();
            /*
            // Kiểm tra xem các trường đã được điền đầy đủ không
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

            // Gọi phương thức đăng nhập trên API Service
            Call<ResponseBody> call = apiService.login(username, password, captcha);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.isSuccessful()) {

                    } else {
                        // Xử lý lỗi khi gửi yêu cầu
                        Toast.makeText(LoginActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    // Xử lý lỗi kết nối
                    Toast.makeText(LoginActivity.this, "Không thể kết nối tới máy chủ", Toast.LENGTH_SHORT).show();
                }

                // Gửi yêu cầu đăng nhập đến máy chủ và xử lý phản hồi
                // Thực hiện các bước xác thực dựa trên username, password và captcha
                // Sau đó xử lý phản hồi từ máy chủ (đăng nhập thành công hoặc thất bại)

            });
            */
            if (loginSuccess()) {
                // Hiển thị toast thông báo đăng nhập thành công
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                // Chuyển sang MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Đóng hoạt động hiện tại
            } else {
                // Hiển thị toast thông báo đăng nhập thất bại
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private boolean loginSuccess() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        return true;
        /* Kiểm tra xem username và password có hợp lệ không
        if (!username.isEmpty() && !password.isEmpty()) {
            // Nếu hợp lệ, trả về true
            return true;
        } else {
            // Nếu không hợp lệ, trả về false
            return false;
        } */
    }
}
