package com.zs.demo.tablistdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zs
 * Date：2018年 05月 10日
 * Time：16:20
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */
public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onTime(View view){
        Intent intent = new Intent(this,TimeActivity.class);
        startActivity(intent);
    }

    public void onSetting(View view){
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }


}
