package com.nearby.belinked.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nearby.belinked.R;
import com.nearby.belinked.java.ActivityCollector;
import com.nearby.belinked.java.Tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register3Activity extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private EditText inputPassword,confirmPassword;
    private String phoneNumber,verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        verificationCode = intent.getStringExtra("verificationCode");
        Toast.makeText(this, "手机号"+phoneNumber+"\n验证码"+verificationCode, Toast.LENGTH_SHORT).show();
        initView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//设置toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标 。
    }
    public void initView(){
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        inputPassword = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
    }
    //菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCollector.removeActivity(this);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityCollector.removeActivity(this);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String password = inputPassword.getText().toString();
                String confirmPw=confirmPassword.getText().toString();
                if (Tool.isPassword(password)&&Tool.isPassword(confirmPw)){
                    if (password.equals(confirmPw)){
                        Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show();
                        register.setClickable(false);
                        register.setBackgroundResource(R.drawable.shape_button_unselected);
                    }
                    else
                    {
                        Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(this, "密码格式错误！\n必须为6-12位字母数字组合", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}