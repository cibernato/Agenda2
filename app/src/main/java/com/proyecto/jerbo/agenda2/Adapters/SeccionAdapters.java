package com.proyecto.jerbo.agenda2.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeccionAdapters extends FragmentStatePagerAdapter {
    private final List<Fragment> listaFrag = new ArrayList<>();
    private final List<String> titulos = new ArrayList<>();


    public void addFragment(Fragment a , String t){
        listaFrag.add(a);
        titulos.add(t);
    }
    public SeccionAdapters(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listaFrag.get(position);
    }

    @Override
    public int getCount() {
        return listaFrag.size();
    }
}
