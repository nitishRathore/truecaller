package com.task.mercari.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.task.mercari.R;
import com.task.mercari.adapter.ProductListAdapter;
import com.task.mercari.base.BaseFragment;
import com.task.mercari.util.ViewModelFactory;
import com.task.mercari.viewmodel.ProductListViewModel;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WomenProductsFragment extends BaseFragment {


    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.rv_women)
    RecyclerView rvWomensProduct;
    @BindView(R.id.txt_error)
    TextView txtErrorView;
    @BindView(R.id.progress_circular)
    ContentLoadingProgressBar loadingProgressBar;
    ProductListAdapter listAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProductListViewModel listViewModel;


    public WomenProductsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int layoutRes() {
        return R.layout.fragment_women_products;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvWomensProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
        listAdapter = new ProductListAdapter();
        rvWomensProduct.setAdapter(listAdapter);
        rvWomensProduct.setHasFixedSize(true);
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);
        observeViewModel();
    }


    private void observeViewModel() {

        listViewModel.getWomensProduct().observe(this, products -> {
            if (products != null) {
                rvWomensProduct.setVisibility(View.VISIBLE);
                listAdapter.setProductList(products);
            }
        });


        listViewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                txtErrorView.setVisibility(View.VISIBLE);
                rvWomensProduct.setVisibility(View.GONE);
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
                    rvWomensProduct.setVisibility(View.GONE);
                }
            }
        });
    }
}
