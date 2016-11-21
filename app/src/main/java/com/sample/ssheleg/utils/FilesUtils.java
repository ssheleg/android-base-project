package com.sample.ssheleg.utils;

import com.sample.ssheleg.SampleApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public class FilesUtils {

    public static String loadAssetsFile(String fileName) {
        String result;
        try {
            InputStream is = SampleApplication.getInstance().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
}
