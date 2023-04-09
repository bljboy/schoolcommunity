package com.bljboy.schoolcommunity;

import static com.bljboy.schoolcommunity.R.string.email_format_error;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bljboy.schoolcommunity.model.Code;
import com.bljboy.schoolcommunity.utils.OkhttpHelper;
import com.bljboy.schoolcommunity.utils.isValidEmail;
import com.bljboy.schoolcommunity.variable.GlobalVars;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    final String LOGIN_URL = GlobalVars.URL + "email_login/";
    private MaterialButton bt_login, bt_login_code;
    private TextInputEditText et_email_login, et_captcha_login;
    private CircularProgressIndicator indicator;
    private OkhttpHelper okhttpHelper;
    private int COUNT_NUMBER = 60;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getLoginStatus();
        bt_login = findViewById(R.id.bt_login);
        et_email_login = findViewById(R.id.et_email_login);
        et_captcha_login = findViewById(R.id.et_captcha_login);
        bt_login_code = findViewById(R.id.bt_login_code);
        indicator = findViewById(R.id.linearProgressIndicator);
//        获取验证码
        bt_login_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputEmail();
            }
        });
//        登录检验验证码
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_captcha_login.getText().toString().equals("") || et_email_login.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "邮箱或验证码错误", Toast.LENGTH_SHORT).show();
                } else {
                    getCaptcha();
                }
            }
        });
    }

    //点击登录按钮检验验证码
    public void getCaptcha() {
        String email = et_email_login.getText().toString();
        String captcha = et_captcha_login.getText().toString();
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("captcha", captcha)
                .build();
        OkhttpHelper.getRequest(LOGIN_URL + "login", requestBody, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String jsonData = response.body().string();
                    Log.e("jsonData", "onResponse: " + jsonData);
                    Gson gson = new Gson();
                    Code code = gson.fromJson(jsonData, Code.class);
//                    Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
//                    Map<String, List<String>> resultMap = gson.fromJson(jsonData, type);
//                    List<String> captchaList = resultMap.get("captcha");
                    if (code.getCode() == 200) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        sharedPreferences = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLogin", true);
                        editor.putString("email", email);
                        editor.apply();
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                StringBuilder captcha_error = new StringBuilder();
//                                for (String str : captchaList) {
//                                    captcha_error.append(str);
//                                }
                                Toast.makeText(Login.this, "验证码错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                response.close();
            }
        });
    }

    //获取输入框信息
    public void getInputEmail() {
        String email = et_email_login.getText().toString();
        if (isValidEmail.isValidEmail(email)) {
            Log.e("email", "------>" + email);
//            RequestBody requestBody = new FormBody.Builder()
//                    .add("email", email)
////                    .add("captcha",captcha)
//                    .build();
            OkhttpHelper.getRequest(LOGIN_URL + "captcha?email=" + email, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String message = getString(R.string.not_server);
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.e("onResponse", response.body().string());
                    if (response.isSuccessful()) {
                        final String jsonData = response.body().string();
                        Gson gson = new Gson();
                        Code code = gson.fromJson(jsonData, Code.class);
                        if (code.getCode() == 200) {
                            CounTimer();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Toast.makeText(Login.this, code.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                    response.close();
                }
            });
        } else {
            Toast.makeText(Login.this, email_format_error, Toast.LENGTH_SHORT).show();
        }

    }

    public void CounTimer() {
        bt_login_code.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (COUNT_NUMBER >= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bt_login_code.setText(String.format(String.valueOf(COUNT_NUMBER)));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    COUNT_NUMBER--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bt_login_code.setEnabled(true);
                        bt_login_code.setText(R.string.bt_login_code);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        indicator.setVisibility(View.INVISIBLE);
    }

    public void getLoginStatus() {
        sharedPreferences = getSharedPreferences(GlobalVars.LOGIN_STATUS, Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}