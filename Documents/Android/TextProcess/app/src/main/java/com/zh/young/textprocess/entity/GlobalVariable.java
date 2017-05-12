package com.zh.young.textprocess.entity;


/**
 * 记录全局的变量
 */
public class GlobalVariable {
    /**
     * 记录文件是否有文件被打开，如果有，那么为HAVE_FILE_OPEN，否则为NOT_HAVE_FILE_OPEN
     */
    public static int has_open_file = ConstantValue.NOT_HAVE_FILE_OPEN;

    public static boolean MENU_DISPLAY = true;
}
