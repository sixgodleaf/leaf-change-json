package com.leaf;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @created by ycc
 * @since 2021-09-20
 */
public class BaseTest {
    public static String getFileStr(String relativePath) {
        String filePath = getFilePath(relativePath);
        try {
            return parseValue(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFilePath(String relativePath) {
        URL resource = BaseTest.class.getResource(relativePath);
        return resource.getFile();
    }
    private static String parseValue(String appConfigFile) throws IOException {
        File file = new File(appConfigFile);
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return new String(bytes);
    }


}
