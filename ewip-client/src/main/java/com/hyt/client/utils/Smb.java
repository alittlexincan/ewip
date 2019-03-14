package com.hyt.client.utils;


import java.io.File;

public class Smb {

    public static void main(String[] args) {

        File file = new File("//192.168.1.123/ftp");		//获取其file对象
        func(file);
    }

    private static void func(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                func(f);
            if(f.isFile())		//若是文件，直接打印
                System.out.println(f.getName());
        }
    }
}

