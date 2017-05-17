package com.zh.young.hellocode.util;


import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 这个类用于文件的相关操作
 * 读入文件
 * 保存文件
 * 新建文件
 */
public class FileUtil {

    /**
     * 从文件中读取数据
     * @param path
     *          读取文件的路径
     * @return
     *          返回读取的内容
     * @throws IOException
     *          可能会出现文件打不开，或者Io错误问题需要处理
     */
    public static String readFile(String path) throws IOException {


        //1. 读取文件
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        reader.close();
        return buffer.toString();
    }

    /**
     * 将内容保存到存储器中
     * @param fileName
     *      保存文件的名字
     * @param content
     *      保存的文件的内容
     * @return
     *      返回是否保存成功，如果成功返回true，否则返回false
     */
    public static boolean saveFile(String fileName, String content) {
        Log.i("save",content);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
