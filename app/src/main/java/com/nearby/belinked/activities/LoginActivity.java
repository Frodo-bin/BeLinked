package com.nearby.belinked.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.io.IOException;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    final String urlSendVerificationCode = "http://118.178.185.111:8090/user/sendVerifyCode";

    private long firstTime = 0;
    private TimeCount time;
    private Button getVCode, register, forgetPassword, login, loginByPassword;
    private EditText getPhoneNumber, getVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        time = new TimeCount(60000, 1000);
        initView();
    }

    public void initView() {
        getVCode = findViewById(R.id.getVerificationCode);
        getVCode.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        loginByPassword = findViewById(R.id.passwordLogin);
        loginByPassword.setOnClickListener(this);
        getPhoneNumber = findViewById(R.id.inputPhoneNumber);
        getVerificationCode = findViewById(R.id.inputVerificationCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getVerificationCode:
                String phoneNumber = getPhoneNumber.getText().toString();
                if (Tool.isChinaPhoneLegal(phoneNumber)) {
                    time.start();
                    String ret = "";
                    try {
                        UtilHttp.post(urlSendVerificationCode, ret);
                        UtilHttp.parseJSONWithJSONObject(ret);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "输入手机号码格式不对", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forgetPassword:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, Register1Activity.class));
                break;
            case R.id.passwordLogin:
                startActivity(new Intent(LoginActivity.this, LoginByPwActivity.class));
                break;
            case R.id.login:
                ;
                break;
            default:
                break;
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getVCode.setBackgroundResource(R.drawable.shape_button_unselected);
            getVCode.setClickable(false);
            getVCode.setText("重新获取(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            getVCode.setText("获取验证码");
            getVCode.setClickable(true);
            getVCode.setBackgroundResource(R.drawable.shape_button_selected);

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