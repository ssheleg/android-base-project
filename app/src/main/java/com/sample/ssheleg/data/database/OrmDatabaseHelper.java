package com.sample.ssheleg.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sample.ssheleg.data.database.dao.CapitalDAO;
import com.sample.ssheleg.data.model.map.Capital;

import java.sql.SQLException;


/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public class OrmDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "project.db";
    private static final int DATABASE_VERSION = DbVersions.CURRENT_VERSION;

    private CapitalDAO capitalDAO = null;

    public OrmDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Capital.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer) {
      /*   try {
           if (oldVer < DbVersions.VERSION_2) {
                TableUtils.createTable(connectionSource, DBItem.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }*/
    }

    public CapitalDAO getCapitalDAO() {
        if (capitalDAO == null) {
            try {
                capitalDAO = new CapitalDAO(getConnectionSource(), Capital.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return capitalDAO;
    }

    @Override
    public void close() {
        super.close();
        capitalDAO = null;
    }
}
