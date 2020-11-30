package com.nearby.belinked.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


import com.hanks.htextview.base.HTextView;
import com.nearby.belinked.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity  implements View.OnClickListener{
    //跳过按钮
    final String motto = "海内存知己 天涯若比邻";
    private int recLen = 5;//跳过倒计时提示5秒
    private TextView tv;
    private HTextView htv;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private Runnable runnableToLogin = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions); // 使用activity的window是隐藏虚拟按键。
        getWindow().setNavigationBarColor(Color.parseColor("#1bb5d7")); //设置虚拟按键的背景颜色

        initView();

        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(SplashActivity.this, LoginByPwActivity.class);
                startActivity(intent);
                //finish();
            }
        }, 5000);//延迟5S后发送handler信息

    }

    //初始化组件
    public void initView() {
        tv = findViewById(R.id.tv);//跳过
        tv.setOnClickListener(this);//跳过监听
        htv = findViewById(R.id.htv);
        htv.animateText(motto); // animate
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(SplashActivity.this, LoginByPwActivity.class);
                startActivity(intent);
                //finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
        }


    /**
     * 跳转到登录界面
     */
    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginByPwActivity.class);
        startActivity(intent);
        //finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        handler.removeCallbacks(runnableToLogin);
    }

}