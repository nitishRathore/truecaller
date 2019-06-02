package com.task.mercari.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.task.mercari.R;
import com.task.mercari.base.BaseActivity;
import com.task.mercari.model.Product;
import com.task.mercari.model.ProductData;
import com.task.mercari.util.ViewModelFactory;
import com.task.mercari.viewmodel.ProductListViewModel;

import java.util.Objects;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }


    @Inject
    ViewModelFactory viewModelFactory;

    ProductListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        listViewModel = ViewModelProviders.of(this,viewModelFactory).get(ProductListViewModel.class);

        obeserveViewModel();
    }

    private void obeserveViewModel() {
        listViewModel.getProductsList().observe(this, products -> {
            if(products != null){

               Intent intent = new Intent(this,MainActivity.class);
               startActivity(intent);
               this.finish();
            }
        });
    }
}
