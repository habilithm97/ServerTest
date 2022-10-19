package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
[네트워킹]
-2티어 C/S 방식 : 원격지의 서버를 연결하는 가장 단순한 방식으로써 클라이언트와 서버를 일대일로 연결함
 -> 가장 많이 사용하는 방식이며 클라이언트가 서버에 연결되어 데이터를 요청하고 응답 받는 단순한 개념임

-3티어 연결 방식
 -> 응용 서버와 데이터 서버로 서버를 따로 구성하면 DB를 분리할 수 있어 중간에 비즈니스 로직을 처리하는 응용 서버가
  좀 더 다양한 역할을 할 수 있다는 장점이 생김

-소켓
 -> TCP와 UDP 방식이 있음
 -> 대부분의 프로그래밍에서는 TCP 연결을 사용함
 -> 반드시 스레드를 사용해야 됨

-HTTP 프로토콜
 -> 소켓으로 웹서버에 연결한 후에 요청을 전송하고 응답을 받은 다음 연결을 끊음
 -> 이런 특성을 '비연결성'이라고 하는데 이것 때문에 실시간으로 데이터를 처리하는 앱은 응답 속도를 높이기 위해 연결성이 있는 소켓 연결을 선호함
 -> 하지만 지금은 인터넷 속도가 빨라져서 HTTP 프로토콜을 사용하는 웹이 일반적이 되었음
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}