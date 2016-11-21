package com.sample.ssheleg.data.model.map;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sample.ssheleg.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
@DatabaseTable(tableName = "capitals")
public class Capital implements BaseMapModel {
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_CAPITAL = "capital";
    public static final String COLUMN_CODE = "code";

    @DatabaseField(dataType = DataType.STRING, columnName = COLUMN_CAPITAL, index = true)
    public String capital;

    @DatabaseField(dataType = DataType.STRING, columnName = COLUMN_COUNTRY, index = true)
    public String country;

    @DatabaseField(dataType = DataType.DOUBLE, defaultValue = "0")
    public double latitude;

    @DatabaseField(dataType = DataType.DOUBLE, defaultValue = "0")
    public double longitude;

    @DatabaseField(id = true, dataType = DataType.STRING, columnName = COLUMN_CODE, defaultValue = "")
    public String code;

    @DatabaseField(dataType = DataType.STRING, defaultValue = "")
    public String placement;

    public Capital() {

    }

    public static List<Capital> fromJson(JsonArray array) {
        List<Capital> result = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            try {
                JsonArray capitalArray = array.get(i).getAsJsonArray();
                Capital capital = new Capital();
                capital.capital = capitalArray.get(1).getAsString();
                capital.country = capitalArray.get(0).getAsString();
                capital.latitude = capitalArray.get(2).getAsDouble();
                capital.longitude = capitalArray.get(3).getAsDouble();
                capital.code = capitalArray.get(4).getAsString();
                capital.placement = capitalArray.get(5).getAsString();
                result.add(capital);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Capital{" +
                "capital='" + capital + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", code='" + code + '\'' +
                ", placement='" + placement + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capital)) return false;

        Capital capital1 = (Capital) o;

        if (Double.compare(capital1.latitude, latitude) != 0) return false;
        if (Double.compare(capital1.longitude, longitude) != 0) return false;
        if (capital != null ? !capital.equals(capital1.capital) : capital1.capital != null)
            return false;
        if (country != null ? !country.equals(capital1.country) : capital1.country != null)
            return false;
        if (code != null ? !code.equals(capital1.code) : capital1.code != null) return false;
        return placement != null ? placement.equals(capital1.placement) : capital1.placement == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = capital != null ? capital.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (placement != null ? placement.hashCode() : 0);
        return result;
    }

    @Override
    public MarkerOptions buildMarker() {
        return new MarkerOptions()
                .title(country)
                .position(new LatLng(latitude, longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.castle_pin));
    }
}