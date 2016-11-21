package com.sample.ssheleg.data.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
@Singleton
public class HelperFactory {

    private static OrmDatabaseHelper databaseHelper;

    @Inject
    public HelperFactory(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, OrmDatabaseHelper.class);
    }

    public OrmDatabaseHelper getHelper() {
        return databaseHelper;
    }


    public void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
