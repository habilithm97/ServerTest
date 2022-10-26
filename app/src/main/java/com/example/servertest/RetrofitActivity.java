package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
[Retrofit]
-TypeSafe한 HttpClient 라이브러리(TypeSafe하다 : 네트워크로부터 전달된 데이터를 프로그램에서 필요한 형태의 객체로 받을 수 있다)
-서버와 클라이언트 간 HTTP 통신을 쉽게 할 수 있음
-통신 라이브러리 중 대표적으로 가장 많이 사용됨
-REST API 통신을 위해 구현된 동일 Squareup사의 OkHttp 라이브러리의 상위 구현체
 -> OkHttp를 네트워크 계층으로 활용하고 그 위에 구축됨

-AsyncTask 없이 백그라운드 스레드 실행 -> 콜백을 통해 메인 스레드에서 UI 업데이트

-장점
 1. 빠른 성능 : AsyncTask를 사용하는 OkHttp보다 3~10배는 빠름
 2. 간단한 구현 : HttpUrlConnection의 Connection, Input&Output Stream, URL Encording, OkHttp의 request, response 등의
  반복된 작업을 라이브러리로 넘겨서 작업함
 3. 가독성 : 어노테이션 사용으로 코드의 가독성이 뛰어나서 직관적인 설계가 가능함
 4. 동기/비동기 쉬운 구현 :
  -동기 : 요청/응답이 하나의 작업에서 발생 -> 요청 후 응답까지 기다림
  -비동기 : 요청 후 다른 작업을 하다 작업이 완료되어 응답이 도착하면 응답을 처리하기 위해 콜백 처리함

 -3가지 구성요소
  1. DTO(POJO) : Data Transfer Object(Plain Old Java Object). 받아온 데이터를 JSON으로 변환할 때 사용되는 클래스
  2. Interface : 사용할 CRUD 동작들을 정의해 놓은 인터페이스
  3. Retrofit.Builder 클래스 : 위에서 정의한 인터페이스를 사용함

*/

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        TextView tv = (TextView)findViewById(R.id.tv);

        Call<Model> call = RetrofitClient.getApiService().Test("5");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model result = response.body();
                String str;
                str = result.getUserId() + "\n" +
                        result.getId() + "\n" +
                        result.getTitle() + "\n" +
                        result.getBody();
                tv.setText(str);
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }
}