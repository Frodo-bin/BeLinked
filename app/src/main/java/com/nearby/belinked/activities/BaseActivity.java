package com.nearby.belinked.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.nearby.belinked.java.ActivityCollector;

import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}