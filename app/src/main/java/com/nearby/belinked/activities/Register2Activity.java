package com.nearby.belinked.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nearby.belinked.R;
import com.nearby.belinked.java.ActivityCollector;
import com.nearby.belinked.java.Tool;

public class Register2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button nextStep;
    private EditText inputVerificationCode;
    private String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        Toast.makeText(this, "手机号"+phoneNumber, Toast.LENGTH_SHORT).show();
        initView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//设置toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标 。
    }
    public void initView(){
        nextStep = findViewById(R.id.nextStep);
        nextStep.setOnClickListener(this);
        inputVerificationCode = findViewById(R.id.inputVerificationCode);
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
            case R.id.nextStep:
                String verificationCode = inputVerificationCode.getText().toString();
                if (Tool.isCheckCode(verificationCode)){
                    Intent intent = new Intent(Register2Activity.this,Register3Activity.class);
                    intent.putExtra("verificationCode",verificationCode);
                    intent.putExtra("phoneNumber",phoneNumber);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "验证码格式错误", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}