package com.zh.young.fileprovider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView backToLastLevel;
    private TextView path;
    private ListView fileList;
    private TitleView titleView;
    private List<File> mFileList = new ArrayList<>();
    private FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        initViews();
        addClickListener();
        getFiles();
    }


    /**
     * 用于获取所有的控件
     */
    private void initViews() {
        titleView = (TitleView) findViewById(R.id.title_view);


        backToLastLevel = (TextView) findViewById(R.id.back_to_last_level);
        path = (TextView) findViewById(R.id.tv_path);
        fileList = (ListView) findViewById(R.id.file_list);



    }


    /**
     * 为需要添加监听事件的view进行监听事件的添加
     */
    private void addClickListener() {
        titleView.setOnArrowClickListener(new TitleView.OnArrowClickListener() {
            @Override
            public void onclick() {
                Intent intent = new Intent(FileManagerActivity.this, OpenFileActivity.class);
                startActivity(intent);
                finish();
            }
        });


        adapter = new FileListAdapter();
        fileList.setAdapter(adapter);
        backToLastLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastPath = path.getText().toString();
//                Toast.makeText(FileManagerActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
                int lastIndex = lastPath.lastIndexOf("/");
                String newPath = lastPath.substring(0,lastIndex);
//                Toast.makeText(FileManagerActivity.this, "newPath = " + newPath, Toast.LENGTH_SHORT).show();

                File file = new File(newPath);
                if(file.exists()){
                    mFileList.clear();
                    getFileList(file);
                    adapter.notifyDataSetChanged();
                    setPath(newPath);
                }

            }
        });

    }

    /**
     * 获取所有的文件列表，这里可能有bug，因为不同的厂商提供的接口不一样，这里要留意
     */
    public void getFiles() {
        File directory = Environment.getExternalStorageDirectory();
        getFileList(directory);
        setPath(directory.getPath());
    }

    /**
     * 设置Path的数据
     */
    private void setPath(String pathData) {
        path.setText(pathData);
        path.setTextColor(Color.WHITE);
    }

    /**
     * 对所有的文件进行遍历
     *
     * @param root
     */
    public void getFileList(File root) {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                mFileList.add(file);
            }
        } else {
            Log.i("Environment", "文件为空");
        }
    }

    @Override
    public void onClick(View v) {
        TextView filePath = (TextView) v.findViewById(R.id.tv_file_path);
        String path = filePath.getText().toString();
        //1. 通过遍历进行文件的匹配，如果匹配到是目录，那么可以执行重置数据，否则，将选中文件，打开文件编辑器
        for(File file : mFileList){
            if(path.equals(file.getPath())){
                if(file.isDirectory()){
                    mFileList.clear();
                    getFileList(file);
                    adapter.notifyDataSetChanged();
                    setPath(file.getPath()); //如果是文件夹，那么就要更新Path
//                    Toast.makeText(this, "选中的是文件夹", Toast.LENGTH_SHORT).show();
                    break;
                }else{
//                    Toast.makeText(this, "选中的是文件", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FileManagerActivity.this, OpenFileActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
        Log.i("filePath",filePath.getText().toString());


    }


    private class FileListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mFileList.size();
        }

        @Override
        public File getItem(int position) {
            return mFileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            //TODO ： 这里需要做一些优化
            View view = View.inflate(getApplicationContext(), R.layout.item_file_list, null);
            TextView fileName = (TextView) view.findViewById(R.id.tv_file_name);
            TextView filePath = (TextView) view.findViewById(R.id.tv_file_path);
            ImageView imageIcon = (ImageView) view.findViewById(R.id.image_icon);
            String name = getItem(position).getName();
            String path = getItem(position).getPath();
            fileName.setText(name);
            filePath.setText(path);
            fileName.setTextColor(Color.WHITE);
            filePath.setTextColor(Color.WHITE);

            if (getItem(position).isDirectory()) {
                imageIcon.setImageResource(R.drawable.ic_folder_black_24dp);
            } else {
                imageIcon.setImageResource(R.drawable.file_document);
            }
            view.setOnClickListener(FileManagerActivity.this);
            return view;
        }
    }

}
