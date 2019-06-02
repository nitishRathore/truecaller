package com.task.mercari.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.mercari.R;
import com.task.mercari.adapter.ProductListAdapter;
import com.task.mercari.base.BaseFragment;
import com.task.mercari.di.module.ViewModelModule;
import com.task.mercari.util.ViewModelFactory;
import com.task.mercari.viewmodel.ProductListViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenProductsFragment extends BaseFragment {


    public MenProductsFragment() {
        // Required empty public constructor
    }

    @Inject
    ViewModelFactory viewModelFactory;

    RecyclerView rvMensProduct;
    ProductListAdapter listAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProductListViewModel listViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_men_products;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMensProduct = view.findViewById(R.id.rv_mens);
        rvMensProduct.setLayoutManager(new GridLayoutManager(getContext(),2));
        listAdapter = new ProductListAdapter();
        rvMensProduct.setAdapter(listAdapter);
        rvMensProduct.setHasFixedSize(true);
        listViewModel = ViewModelProviders.of(this,viewModelFactory).get(ProductListViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {

        listViewModel.getMensProduct().observe(this, products -> {
            if(products != null){
                rvMensProduct.setVisibility(View.VISIBLE);
                listAdapter.setProductList(products);
            }
        });
    }
}
