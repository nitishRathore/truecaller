package com.task.mercari.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.task.mercari.ui.fragment.AllProductsFragment;
import com.task.mercari.ui.fragment.MenProductsFragment;
import com.task.mercari.ui.fragment.WomenProductsFragment;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public class FragmentAdapter extends FragmentPagerAdapter {


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MenProductsFragment();
            case 1:
                return new AllProductsFragment();
            case 2:
                return new WomenProductsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Men";
            case 1:
                return "All";
            case 2:
                return "Women";
            default:
                return null;
        }
    }
}
