package com.task.mercari.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.task.mercari.R;
import com.task.mercari.base.BaseActivity;
import com.task.mercari.model.Product;
import com.task.mercari.model.ProductData;
import com.task.mercari.util.ViewModelFactory;
import com.task.mercari.viewmodel.ProductListViewModel;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }


    @Inject
    ViewModelFactory viewModelFactory;

    ProductListViewModel listViewModel;
    @BindView(R.id.txt_error)
    TextView txtErrorView;

    @BindView(R.id.progress_circular)
    ContentLoadingProgressBar loadingProgressBar;

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


        listViewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                txtErrorView.setVisibility(View.VISIBLE);
                txtErrorView.setText(getText(R.string.error));
            }else {
                txtErrorView.setVisibility(View.GONE);
                txtErrorView.setText(null);
            }
        });

        listViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    txtErrorView.setVisibility(View.GONE);

                }
            }
        });
    }
}
