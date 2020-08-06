package com.ljh.lib_common.utils;

import android.content.Context;

import java.io.File;
import java.io.IOException;

/**
 */
public class FileUtil {

    public static String FOLDER_NAME = "ljh";

    private FileUtil(Context context) {
    }

    //File.separator 文件分隔符
    /**
     * 目录
     */
    public static File getStorageDirectory(String foldName) {
        return StorageUtil.getUnderCacheDirectory(foldName, true);
    }

    public static File getStorageDirectory(String foldName, boolean preferExternal) {
        return StorageUtil.getUnderCacheDirectory(foldName, preferExternal);
    }


    public static File getFile(String foldName, String fileName) {
        return new File(getStorageDirectory(foldName), fileName);
    }

    public static File getFile(String foldName, String fileName, boolean prefer) {
        return new File(getStorageDirectory(foldName, prefer), fileName);
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isFileExists(String foldName, String fileName) {
        return getFile(foldName, fileName).exists();
    }

    public static boolean deleteDir(File dir) {
        if (dir == null) return false;
        if (!dir.exists()) return true;
        if (!dir.isDirectory()) {

            if (dir.isFile()) {
                deleteFile(dir);
                return true;
            }
            return false;
        }

        File[] files = dir.listFiles();

        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!deleteFile(file)) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    public static boolean deleteFile(String srcFilePath) {
        return deleteFile(getFileByPath(srcFilePath));
    }

    public static File getFileByPath(String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile(final File file) {
        if (file == null) return false;
        // 文件存在并且删除失败返回false
        if (file.exists() && !file.delete()) return false;
        // 创建目录失败返回false
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(final File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }
    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
