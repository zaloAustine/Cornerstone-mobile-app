package com.zalocoders.cornerstonekangemi.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zalocoders.cornerstonekangemi.fragments.DiscoverFragment;
import com.zalocoders.cornerstonekangemi.fragments.FeedFragment;
import com.zalocoders.cornerstonekangemi.fragments.GivingFragment;
import com.zalocoders.cornerstonekangemi.fragments.HymnalFragment;
import com.zalocoders.cornerstonekangemi.fragments.SermonFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0 :
            return new SermonFragment();
            case 1:
                return  new GivingFragment();
            case 2:
                return  new FeedFragment();
            case 3:
                return new DiscoverFragment();
            case 4:
                return new HymnalFragment();
            default:
                return  new FeedFragment();

        }


    }

    @Override
    public int getCount() {
        return 5;
    }
}
