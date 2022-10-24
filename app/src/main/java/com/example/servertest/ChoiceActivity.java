package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
[네트워킹]
-2티어 C/S 방식 : 원격지의 서버를 연결하는 가장 단순한 방식으로써 클라이언트와 서버를 일대일로 연결함
 -> 가장 많이 사용하는 방식이며 클라이언트가 서버에 연결되어 데이터를 요청하고 응답 받는 단순한 개념임

-3티어 연결 방식
 -> 응용 서버와 데이터 서버로 서버를 따로 구성하면 DB를 분리할 수 있어 중간에 비즈니스 로직을 처리하는 응용 서버가
  좀 더 다양한 역할을 할 수 있다는 장점이 생김
*/
public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button socketBtn = (Button)findViewById(R.id.socketBtn);
        socketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocketActivity.class);
                startActivity(intent);
            }
        });

        Button httpBtn = (Button)findViewById(R.id.httpBtn);
        httpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HttpActivity.class);
                startActivity(intent);
            }
        });

        Button volleyBtn = (Button)findViewById(R.id.volleyBtn);
        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VolleyActivity.class);
                startActivity(intent);
            }
        });

        Button gsonBtn = (Button)findViewById(R.id.gsonBtn);
        gsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GsonActivity.class);
                startActivity(intent);
            }
        });
    }
}