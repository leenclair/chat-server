package com.example.chatserver.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    /**
     * 바이트 배열을 파일로 저장
     */
    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.flush();
        }
    }

    /**
     * 파일을 바이트 배열로 읽기
     */
    public static byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    /**
     * 파일 존재 여부 확인
     */
    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 디렉토리 생성
     */
    public static void createDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * 파일 삭제
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 파일 크기 확인
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.length() : 0;
    }

    public static long getFileSize(File file) {
        return file.exists() ? file.length() : 0;
    }
}
