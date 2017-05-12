package com.zh.young.textprocess.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zh.young.textprocess.R;


/**
 * 做为菜单的Fragment存在
 */
public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View menu = inflater.inflate(R.layout.frament_menu, container, false);
        return menu;
    }
}
