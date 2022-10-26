package com.example.servertest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
-인터페이스에 기술된 명세를 Http API로 전환해주기 때문에
 요청할 API에 대한 명세만을 인터페이스에 기술해두면됨

-반환되는 타입은 Call<객체타입>의 형태로 기술해야함
*/

public interface RetrofitInterface {
    @GET("posts/{UserID}")
    Call<Model> Test (
        @Path("UserID") String userid);
}
