package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
/*
[Glide]
-이미지를 빠르고 효율적으로 불러올 수 있게 해주는 라이브러리임
-사용법도 간단하고 확장성도 넓어서 많이 사용하고 있음
-HttpUrlConnection 기반이지만, Volley나 OkHttp 라이브러리를 사용할 수 있는 플러그인도 지원함
-어떤 이미지라도 빠르고 부드럽게 스크롤하는 것이 목적임

[Glide와 Picasso의 차이]
-속성 수 : >
-메모리 사용량 : 리사이징 이미지 < 원본 이미지
-캐싱 : 리사이징 이미지 저장 / 원본 이미지 저장
-속도 : 네트워크에서의 이미지 로딩 느림(리사이징), 캐시 이미지 로딩 빠름 / 네트워크에서의 이미지 로딩 빠름(무변환), 캐시 이미지 로딩 느림
-품질 : 낮은 비트 사용 < 높은 비트 사용
-GIF : 지원 / 미지원
*/

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        ImageView iv = (ImageView)findViewById(R.id.iv);
        ImageView iv1 = (ImageView)findViewById(R.id.iv1);

        Picasso.get().load("https://square.github.io/picasso/static/sample.png").placeholder(R.drawable.noimage)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(iv);

        Picasso.get().load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").placeholder(R.drawable.noimage)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(iv1);
        // placeholder() : 이미지 로드 전 또는 로드에 실패하면 대체 이미지를 표시함
        // resize() : 이미지 크기 조절
        // 캐싱과 저장 방지
        // memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
        // networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)

        ImageView iv2 = (ImageView)findViewById(R.id.iv2);
        ImageView iv3 = (ImageView)findViewById(R.id.iv3);

        Glide.with(this).load("https://square.github.io/picasso/static/sample.png").placeholder(R.drawable.noimage).into(iv2);
        Glide.with(this).load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").placeholder(R.drawable.noimage).into(iv3);
        // placeholder() : 이미지 로딩을 시작하기 전에 보여줄 이미지를 설정함
        // error() : 리소스를 불러오다가 오류가 발생했을 때 보여줄 이미지를 설정함
        // fallback() : 로드할 url이 null인 경우 등 비어있을 때 보여줄 이미지를 설정함
        // skipMemoryCache() : 메모리에 캐싱하지 않으면 true
        // diskCacheStrategy() : 디스크에 캐싱하지 않으려면 DiskCacheStrategy.NONE
    }
}