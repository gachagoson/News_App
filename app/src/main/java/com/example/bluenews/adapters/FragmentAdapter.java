package com.example.bluenews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bluenews.fragments.EducationFragment;
import com.example.bluenews.fragments.HealthFragment;
import com.example.bluenews.fragments.HomeFragment;
import com.example.bluenews.fragments.NationalFragment;
import com.example.bluenews.fragments.ScienceFragment;
import com.example.bluenews.fragments.SportsFragment;
import com.example.bluenews.fragments.TechFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 7;

    public FragmentAdapter(@NonNull FragmentManager fm, int i) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HealthFragment();
            case 2:
                return new SportsFragment();
            case 3:
                return new EducationFragment();
            case 4:
                return new ScienceFragment();
            case 5:
                return new TechFragment();
            case 6:
                return new NationalFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Health";
            case 2:
                return "Sports";
            case 3:
                return "Education";
            case 4:
                return "Science";
            case 5:
                return "Tech";
            case 6:
                return "National";
            default:
                return null;
        }
    }
}
