package com.sample.ssheleg.data.rest.factory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.utils.FilesUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public class RetrofitApiFactory {

    public static final String BASE_URL = "https://api.github.com";

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new ErrorInterceptor())
            .addInterceptor(new CapitalsInterceptor());

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClientBuilder.build());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }

    private static class ErrorInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (response.code() != HTTP_OK) {
                //TODO process error
            }
            return response;
        }
    }

    private static class CapitalsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.url().toString().contains("loadlocalcapitals")) {
                String data = FilesUtils.loadAssetsFile("capitals.json");
                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray = (JsonArray) jsonParser.parse(data);
                List<Capital> capitals = Capital.fromJson(jsonArray);
                Collections.sort(capitals, (l, r) -> l.code.compareTo(r.code));
                return buildResponse(request, gson.toJson(capitals));
            }
            Response response = chain.proceed(request);
            if (response.code() != HTTP_OK) {
                //TODO process error
            }
            return response;
        }
    }

    private static Response buildResponse(Request request, String json) {
        return new Response.Builder()
                .code(200)
                .message(json)
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), json.getBytes()))
                .addHeader("description-type", "application/json")
                .build();
    }


}