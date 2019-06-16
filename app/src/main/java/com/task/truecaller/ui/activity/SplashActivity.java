package com.task.truecaller.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.task.truecaller.R;
import com.task.truecaller.base.BaseActivity;

import java.util.Objects;

public class SplashActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        observeViewModel();
    }

    private void observeViewModel() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
