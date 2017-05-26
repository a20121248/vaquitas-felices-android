package com.github.alvarosct.ascthelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.IBaseModel;
import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.utils.BaseFormManager;
import com.github.alvarosct.ascthelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class BaseFormTabbedFragment<T extends IBaseModel, F extends BaseFormManager>
        extends BaseFormFragment<T, F> {

    public BaseFormTabbedFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle avedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment_tabbed, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        setupViewPager(viewPager);
//        viewPager.forceLayout();
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public abstract void setupViewPager(ViewPager viewPager);

    protected class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_ACTION, getArguments().getString(Constants.BUNDLE_ACTION));
            fragment.setArguments(bundle);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}