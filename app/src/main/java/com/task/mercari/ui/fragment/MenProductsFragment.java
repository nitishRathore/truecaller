package com.task.mercari.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.mercari.R;
import com.task.mercari.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenProductsFragment extends BaseFragment {


    public MenProductsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int layoutRes() {
        return R.layout.fragment_men_products;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_men_products, container, false);
    }

}
