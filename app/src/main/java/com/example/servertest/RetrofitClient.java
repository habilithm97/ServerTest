package com.example.servertest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/"; // 요청할 서버의 기본 URL
    // Open RestAPI Test 서버 : https://reqres.in/

    // getInstance()로 생성한 RetrofitClient를 이용하여
    // Http API 명세가 담긴 인터페이스의 구현체를 생성한 다음 반환함
    public static RetrofitInterface getApiService() {
        return getInstance().create(RetrofitInterface.class);
    }

    private static Retrofit getInstance() { // RetrofitClient 생성
        Gson gson = new GsonBuilder().setLenient().create(); // JSON 응답을 객체로 변환하기 위해 필요함
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) // 응답을 객체로 변환하기 위한 GsonConverter 설정
                .build();
    }
}
