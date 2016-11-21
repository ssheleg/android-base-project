package com.sample.ssheleg.data.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sample.ssheleg.data.model.map.Capital;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public class CapitalDAO extends BaseDaoImpl<Capital, String> {

    public CapitalDAO(ConnectionSource connectionSource, Class<Capital> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Capital> getAll() {
        try {
            List<Capital> result = queryForAll();
            Collections.sort(result, (l, r) -> l.code.compareTo(r.code));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void replace(List<Capital> newList) {
        try {
            TableUtils.clearTable(connectionSource, Capital.class);
            create(newList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createOrUpdateData(Capital data) {
        try {
            Dao.CreateOrUpdateStatus status = createOrUpdate(data);
            if (status != null && status.getNumLinesChanged() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
