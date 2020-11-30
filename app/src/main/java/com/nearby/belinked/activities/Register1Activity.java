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

public class Register1Activity extends AppCompatActivity implements View.OnClickListener {
    private Button getVCode;
    private EditText inputPhoneNnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        initView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//设置toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标 。
    }

    public void initView() {
        getVCode = findViewById(R.id.getVerificationCode);
        getVCode.setOnClickListener(this);
        inputPhoneNnumber = findViewById(R.id.inputPhoneNumber);
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
        switch (v.getId()) {
            case R.id.getVerificationCode:
                String phoneNumber = inputPhoneNnumber.getText().toString();
                if (Tool.isChinaPhoneLegal(phoneNumber)) {
                    Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
                    intent.putExtra("phoneNumber",phoneNumber);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "输入手机号码格式不对", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}