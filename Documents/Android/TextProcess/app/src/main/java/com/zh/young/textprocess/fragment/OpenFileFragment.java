package com.zh.young.textprocess.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zh.young.textprocess.R;

/**
 * Created by young on 2017/4/27.
 */

public class OpenFileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_file, container, false);
        readTextFromFileSystem();
        return view;
    }

    /**
     * 从本地的文件系统中读取文件
     * 读取的方式为：
     *  1. 通过ContentProvider打开文件管理器
     *  2. 读取打开的文件信息
     *  3. 将文件写入到控件中
     */
    private void readTextFromFileSystem() {
        
    }
}
