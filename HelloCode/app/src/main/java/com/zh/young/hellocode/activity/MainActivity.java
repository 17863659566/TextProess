package com.zh.young.hellocode.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.zh.young.hellocode.util.ConstantValue;
import com.zh.young.hellocode.R;
import com.zh.young.hellocode.UI.Editor;
import com.zh.young.hellocode.adapter.MenuAdapter;
import com.zh.young.hellocode.util.FileUtil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

import static android.support.v4.widget.DrawerLayout.STATE_DRAGGING;
import static android.support.v4.widget.DrawerLayout.STATE_IDLE;
import static android.support.v4.widget.DrawerLayout.STATE_SETTLING;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 最上面的ToolBar
     */
    private Toolbar mToolbar;
    /**
     * 编辑区加左侧菜单
     */
    private DrawerLayout mDrawerLayout;
    
    /**
     * 菜单按钮的状态
     */
    private int mMaterialState;
    /**
     * 用来描述drawer的状态
     */
    private boolean direction;
    /**
     * 主要的编辑器
     */
    private Editor mEditor;
    /**
     * drawer中的项的列表
     */
    private ListView mMenuList;
    /**
     * toobar上的文件的名称
     */
    private TextView mFileName;
    /**
     * toobar上面的保存文件的按钮
     */
    private  String mFileContent;
    private ImageButton mSaveFile;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ConstantValue.NULL_FILE_SELECT:
                    break;
                case ConstantValue.SUCCEED_OPEN_FILE:
                    mSaveFile.setVisibility(View.VISIBLE);
                    mEditor.setText(mFileContent);
                    String filename = (String) msg.obj;
                    mFileName.setText(filename);
                    break;
            }


        }
    };
    /**
     * alert中的确定按钮
     */
    private Button mPositiveButton;
    /**
     * alert中的取消按钮
     */
    private Button mNegativeButton;
    /**
     * 存储文件的弹窗
     */
    private AlertDialog mDialog;
    /**
     * 存储文件时候的文件名称
     */
    private EditText mEtFilename;
    /**
     * 要显示在弹窗中的布局
     */
    private View mAlertContent;
    /**
     * 保存文件的路径
     */
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    /**
     * 初始化所有的view
     */
    private void initViews() {
        mAlertContent = View.inflate(this, R.layout.save_file, null);
        findViews();

        setViews();


    }

    /**
     * 设置所有view的事件
     */
    private void setViews() {
        setSupportActionBar(mToolbar);
        MaterialMenuDrawable materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        setToolbarInfo(materialMenu);

        mPositiveButton.setOnClickListener(this);
        mNegativeButton.setOnClickListener(this);
        setMenuListInfo();

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if(newState != STATE_IDLE){
                    changeMenuIcon();
                }
            }
        });
    }

    /**
     * 设置左侧drawer中的项的相关信息
     */
    private void setMenuListInfo() {
        MenuAdapter adapter = new MenuAdapter(this);
        mMenuList.setAdapter(adapter);

        mMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    //新建文件
                    case 0:
                        createNewFile();
                        break;
                    //打开文件
                    case 1 :
                        openFile();

                        break;
                    //关于软件
                    case 2 :
                        startActivity(new Intent(MainActivity.this,AboutSoftActivity.class));
                        break;
                    //设置中心
                    case 3 :
                        startActivity(new Intent(MainActivity.this,SettingCenterActivity.class));
                        break;

                }
            }
        });
    }

    /**
     * 创建一个文件
     */
    private void createNewFile() {
        if(!mEditor.getText().toString().equals("")){
            mEditor.setText("");
        }
        mSaveFile.setVisibility(View.VISIBLE);
        mFileName.setText("undefined");
        mFileName.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
        mFileName.setTextColor(Color.WHITE);
        switchEditorAndDrawerState();
    }


    /**
     * 切换编辑器的状态
     */
    private void switchEditorAndDrawerState(){
        if(direction){
            closeDrawer();
        }else{
            openDrawer();
        }

    }

    private void changeMenuIcon() {
        direction = !direction;
        mMaterialState = generateState(mMaterialState);
        getMaterialMenu(mToolbar).animateIconState(intToState(mMaterialState));
    }

    /**
     * 关闭抽屉
     */
    private void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.START);
        mEditor.setFocusable(false);
        mEditor.setFocusableInTouchMode(false);
    }

    /**
     * 打开抽屉
     */
    private void closeDrawer() {
        mDrawerLayout.closeDrawer(Gravity.START);
        mEditor.setFocusable(true);
        mEditor.setFocusableInTouchMode(true);
        mEditor.requestFocus();
    }

    /**
     * 打开文件的操作
     */
    private void openFile() {

        startActivityForResult(new Intent(MainActivity.this,FileManagerActivity.class),0);
        switchEditorAndDrawerState();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            return;
        }
        switch (requestCode){
            //这个是请求文件路径的结果码
            case 0:

                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Message msg = Message.obtain();
                                if(data == null){
                                    msg.what = ConstantValue.NULL_FILE_SELECT;
                                }else{
                                    String filePath = data.getStringExtra("filePath");
                                    msg.what = ConstantValue.SUCCEED_OPEN_FILE;
                                    int filenameIndex = filePath.lastIndexOf("/");
                                    msg.obj = filePath.substring(filenameIndex+1,filePath.length());
                                    mFileContent = FileUtil.readFile(filePath);
                                }





                            mHandler.sendMessage(msg);
                        } catch (Exception e) {
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(MainActivity.this, "文件打开错误，请稍后再试", Toast.LENGTH_SHORT).show();
                               }
                           });
                        }
                        }



                    }.start();


                break;
            //保存文件的回调
            case 1 :
                mFilePath = data.getStringExtra("filePath");
                mDialog.show();
                break;
        }
    }

    /**
     * 设置Toolbar的一些信息
     * @param materialMenu
     *      menu菜单触发按钮
     */
    private void setToolbarInfo(MaterialMenuDrawable materialMenu) {
        mToolbar.setNavigationIcon(materialMenu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //direction 代表了目前抽屉的状态，如果为true，那么关闭，否则打开
                switchEditorAndDrawerState();

            }
        });

        //将布局加载到相应的控件树上
        View toobarContent = getLayoutInflater().inflate(R.layout.layout_toobar, mToolbar);
        mFileName = (TextView) toobarContent.findViewById(R.id.tv_file_name);
        mSaveFile = (ImageButton) toobarContent.findViewById(R.id.ib_save_file);
        mSaveFile.setVisibility(View.INVISIBLE);

        mSaveFile.setOnClickListener(this);


    }

    /**
     * 将状态码转换成真实的状态
     * @param mMaterialState
     *              状态代码
     * @return
     *      真实的状态
     */
    private MaterialMenuDrawable.IconState intToState(int mMaterialState) {
        switch (mMaterialState) {
            case 0:
                return MaterialMenuDrawable.IconState.BURGER;
            case 1:
                return MaterialMenuDrawable.IconState.ARROW;
        }
        throw new IllegalArgumentException("Must be a number [0,2)");
    }

    /**
     * 获取菜单按钮
     * @param mTopMenu
     *          整个ToolBar
     * @return
     *          返回导航菜单按钮
     */
    private MaterialMenu getMaterialMenu(Toolbar mTopMenu) {
        return (MaterialMenu)mTopMenu.getNavigationIcon();
    }

    /**
     * 生成随机状态
     * @param previous
     *          以前的状态代码
     * @return
     *          返回与以前代码不同的状态
     */
    private int generateState(int previous) {
        int generatedSates = new Random().nextInt(2);
        return generatedSates != previous ? generatedSates : generateState(generatedSates);
    }

    /**
     * 查找到所有的需要的控件
     */
    private void findViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        mEditor = (Editor) findViewById(R.id.et_editor);
        mMenuList = (ListView) findViewById(R.id.lv_menu_list);
        mPositiveButton = (Button) mAlertContent.findViewById(R.id.bt_positive);
        mNegativeButton = (Button) mAlertContent.findViewById(R.id.bt_negative);
        mEtFilename = (EditText) mAlertContent.findViewById(R.id.et_save_filename);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_save_file :

                //如果是第一次保存则需要输入文件名，否则可以直接保存
                if(mFileName.getText().toString().equals("undefined")){
                    saveFile();
                }else{
                    String filename = mFileName.getText().toString();
                    String content = mEditor.getText().toString();
                    if(FileUtil.saveFile(filename, content)){
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            //设置alert中的确定按钮
            case R.id.bt_positive :
                String filename = mEtFilename.getText().toString();
                String content = mEditor.getText().toString();
                if(FileUtil.saveFile(new String(mFilePath + "/" +filename),content)){
                    Toast.makeText(this, "保存成功" + content, Toast.LENGTH_SHORT).show();
                }
                mFileName.setText(filename);
                mDialog.dismiss();
                break;
            //alert的取消按钮
            case R.id.bt_negative :
                mDialog.dismiss();
                break;
            default:
                switchEditorAndDrawerState();
                break;
        }
    }

    /**
     * 保存文件
     */
    private void saveFile() {
        startActivityForResult(new Intent(this,SelectSavePathActivity.class),1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("保存文件");
        builder.setIcon(R.drawable.ic_save_black_24dp);

        builder.setView(mAlertContent);
        mDialog = builder.create();

    }


}
