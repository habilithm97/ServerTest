package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
[네트워킹]
-2티어 C/S 방식 : 클라이언트와 서버가 일대일로 연결하는 방식으로, 원격지의 서버를 연결하는 가장 단순한 방식임
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}