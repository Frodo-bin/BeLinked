package com.nearby.belinked.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nearby.belinked.R;
import com.nearby.belinked.java.ActivityCollector;
import com.nearby.belinked.java.Tool;
import com.nearby.belinked.util.UtilHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginByPwActivity extends BaseActivity implements View.OnClickListener {
    final String urlLogin = "http://118.178.185.111:8090/user/login";
    private Button forgetPw, login, register, verificationCodeLogin;
    private long firstTime = 0;
    private EditText inputPhoneNumber, inputPassword;
    private static final String TAG = "LoginByPwActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_pw);
        initView();
    }

    //初始化组件
    public void initView() {
        forgetPw = findViewById(R.id.forgetPassword);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        verificationCodeLogin = findViewById(R.id.verificationCodeLogin);
        forgetPw.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        verificationCodeLogin.setOnClickListener(this);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        inputPassword = findViewById(R.id.inputPassword);
    }

    String jsonResult = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetPassword:
                Intent intent = new Intent(LoginByPwActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.verificationCodeLogin:
                startActivity(new Intent(LoginByPwActivity.this, LoginActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(LoginByPwActivity.this, Register1Activity.class));
                break;
            case R.id.login:
                String phoneNumber = inputPhoneNumber.getText().toString();
                String password = inputPassword.getText().toString();
                if (Tool.isChinaPhoneLegal(phoneNumber)) {
                    if (Tool.isPassword(password)) {
                        Map<String, String> userInfo = new HashMap<>(1);
                        userInfo.put("userTel", phoneNumber);
                        userInfo.put("password", password);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    jsonResult = UtilHttp.post(urlLogin, JSON.toJSONString(userInfo));
                                } catch (IOException e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        }).start();
                        Toast.makeText(this, jsonResult, Toast.LENGTH_SHORT).show();

                        //Toast.makeText(this, "格式都对了", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                ActivityCollector.finishAll();
                System.exit(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}