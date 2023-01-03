package com.example.jaryia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jaryia.fragmentPages.All;
import com.example.jaryia.fragmentPages.Animals;
import com.example.jaryia.fragmentPages.BuySellHome;
import com.example.jaryia.fragmentPages.Cars;
import com.example.jaryia.fragmentPages.Jobs;
import com.example.jaryia.fragmentPages.Mulk;
import com.example.jaryia.fragmentPages.Taxi;
import com.example.jaryia.fragmentPages.Technology;


public class TabPagerAdapter extends FragmentPagerAdapter {
    String[] page = new String[]{"Жалпы", "К.Мулк", "Авто", "Мал-чарба", "Алуу/Сатуу", "Жумуш", "Электроника", "Каттам"};

    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return page[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new Mulk();
            case 2:
                return new Cars();
            case 3:
                return new Animals();
            case 4:
                return new BuySellHome();
            case 5:
                return new Jobs();
            case 6:
                return new Technology();
            case 7:
                return new Taxi();
            default:
                return new All();
        }
    }

    @Override
    public int getCount() {
        return 8;
    }

}
