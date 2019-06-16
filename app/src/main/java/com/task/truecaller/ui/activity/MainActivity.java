package com.task.truecaller.ui.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProviders;

import com.task.truecaller.R;
import com.task.truecaller.base.BaseActivity;
import com.task.truecaller.util.ViewModelFactory;
import com.task.truecaller.viewmodel.TrueCallerViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.btn)
    Button btnClick;

    @BindView(R.id.txt_tenth)
    TextView txtTenthChar;
    @BindView(R.id.txt__every_tenth)
    TextView txtEveryTenthChar;
    @BindView(R.id.txt_count)
    TextView txtWordCount;

    @BindView(R.id.progress_circular)
    ContentLoadingProgressBar loadingProgressBar;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    TrueCallerViewModel trueCallerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtEveryTenthChar.setMovementMethod(new ScrollingMovementMethod());
        txtWordCount.setMovementMethod(new ScrollingMovementMethod());
        trueCallerViewModel = ViewModelProviders.of(this, viewModelFactory).get(TrueCallerViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {

        loadingProgressBar.hide();
        trueCallerViewModel.getProgress().observe(this, response -> {
            if (response != null) {
                if (response) {
                    loadingProgressBar.show();
                    btnClick.setEnabled(false);
                } else {
                    loadingProgressBar.hide();
                    btnClick.setEnabled(true);
                }
            }
        });
        trueCallerViewModel.getTenthCharacterData().observe(this, response -> {
            if (response != null) {
                txtTenthChar.setText(response);
            } else {
                txtTenthChar.setText("");
            }
        });

        trueCallerViewModel.getEveryTenthCharacterData().observe(this, response -> {
            if (response != null) {


                txtEveryTenthChar.setText(response);
            } else {
                txtEveryTenthChar.setText("");
            }
        });

        trueCallerViewModel.getWordsCountData().observe(this, response -> {
            if (response != null) {

                txtWordCount.setText(response);

            } else {
                txtWordCount.setText("");
            }
        });

    }


    @OnClick(R.id.btn)
    public void callParallerApis() {
        trueCallerViewModel.fetchData();
    }
}
