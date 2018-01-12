/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Mukil
 */
public class fileUtils {

    public static int WriteToFile(String path, String filename, String content) {
        int res = 0;
        path = path + "/";
        try {
            if (!new File(path).exists()) {
                System.out.println("Making Dirs : " + path);
                new File(path).mkdirs();
            }
            if (!checkFileExist(path + filename)) {
                System.out.println("Creating New File :" + (path + filename));
                new File(path + filename).createNewFile();
            }
            FileWriter fw = new FileWriter(path + filename);
            fw.write(content);
            fw.close();
            System.out.println("File save Success :" + (path + filename));
        } catch (Exception e) {
            e.printStackTrace();
            res = -1;
        } finally {
            return res != -1 ? 1 : 0;
        }
    }

    public static boolean checkFileExist(String path) {
        return new File(path).exists();
    }

    public static boolean checkFileExist(File f) {
        return f.exists();
    }

    public static String ReadFromFile(String path, String filename) {
        String fileAsString = "";
        try {
            if (checkFileExist(path + filename)) {
                InputStream is = new FileInputStream(path + filename);
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                String line = buf.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line).append("\n");
                    line = buf.readLine();
                }
                fileAsString = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return fileAsString;
        }
    }

}
