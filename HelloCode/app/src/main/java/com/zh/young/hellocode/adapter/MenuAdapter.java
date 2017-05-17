package com.zh.young.hellocode.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zh.young.hellocode.R;



/**
 * 这个类作为菜单的ListView的适配器
 */
public class MenuAdapter extends BaseAdapter {
    private final Context mContext;
    private String[] mMenuDescs;
    private int[] drawRes;

    public MenuAdapter(Context context){
        mContext = context;
        mMenuDescs = new String[]{
                "新建文件",
                "打开文件",
                "关于软件",
                "设置"};
        drawRes = new int[]{
          R.drawable.ic_create_new_folder_black_24dp,
          R.drawable.ic_open_file_black_24dp,
          R.drawable.ic_about_software_24dp,
          R.drawable.ic_settings_black_24dp
        };


        }
    @Override
    public int getCount() {
        return mMenuDescs.length;
    }

    @Override
    public String getItem(int position) {
        return mMenuDescs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_menu,null);
            holder = new ViewHolder();
            holder.menuDesc = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        }else{
           holder = (ViewHolder) convertView.getTag();
        }

        setItemInfo(position, holder);

        return convertView;
    }

    private void setItemInfo(int position, ViewHolder holder) {
        holder.menuDesc.setText(getItem(position));
        holder.menuDesc.setPadding(50,50,50,50);
        holder.menuDesc.setTextColor(Color.WHITE);
        holder.menuDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
        Drawable drawable = mContext.getResources().getDrawable(drawRes[position]);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        holder.menuDesc.setCompoundDrawables(drawable,null,null,null);
        holder.menuDesc.setCompoundDrawablePadding(30);
    }

    class ViewHolder{
        TextView menuDesc;
    }
}
