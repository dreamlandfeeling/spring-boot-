package com.xin.manager.utils;

import java.io.*;

public class FileUtils {

    public static void saveFile(byte[] bytes, String fileName, String savePath) throws IOException {
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream out = null;
        out = new FileOutputStream(savePath+fileName);
        out.write(bytes);
        out.flush();
        out.close();
    }

}