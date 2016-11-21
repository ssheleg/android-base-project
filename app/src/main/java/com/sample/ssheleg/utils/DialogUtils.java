package com.sample.ssheleg.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.sample.ssheleg.R;


/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public class DialogUtils {

    public enum ID {
        ERROR_LOST_CONNECTION,
        ERROR_NOT_FOUND,
        ERROR_SOME_HAPPENED
    }

    public static Dialog create(ID id, Context context) {
        switch (id) {
            case ERROR_LOST_CONNECTION:
                return new AlertDialog.Builder(context)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.error_lost_connection)
                        .create();
            case ERROR_NOT_FOUND:
                return new AlertDialog.Builder(context)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.error_not_found)
                        .create();
            case ERROR_SOME_HAPPENED:
                return new AlertDialog.Builder(context)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.error_some_happened)
                        .create();
            default:
                new AlertDialog.Builder(context)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.error_some_happened)
                        .create();
        }
        return null;
    }
}
