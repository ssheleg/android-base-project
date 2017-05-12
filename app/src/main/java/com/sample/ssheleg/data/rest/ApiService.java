package com.sample.ssheleg.data.rest;

import com.sample.ssheleg.data.model.map.Capital;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public interface ApiService {

    @GET("/loadlocalcapitals")
    Observable<List<Capital>> getCapitals();
}
