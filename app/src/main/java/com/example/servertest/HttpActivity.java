package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
[HTTP 프로토콜]
 -> 소켓으로 웹서버에 연결한 후에 요청을 전송하고 응답을 받은 다음 연결을 끊음
 -> 이런 특성을 '비연결성'이라고 하는데 이것 때문에 실시간으로 데이터를 처리하는 앱은 응답 속도를 높이기 위해 연결성이 있는 소켓 연결을 선호함
 -> 하지만 지금은 인터넷 속도가 빨라져서 HTTP 프로토콜을 사용하는 웹이 일반적이 되었음

 -비연결성(Stateless)인 HTTP 프로토콜은 페이지 정보를 요청할 때마다 소켓을 새로 연결하고 응답을 받은 다음에는 소켓의 연결을 끊는 것이 일반적임
  -> 그 소켓 연결 위에서 HTTP 프로토콜에 맞는 요청을 보내고 응답을 받아 처리함*/
public class HttpActivity extends AppCompatActivity {

    Handler handler = new Handler();

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        EditText edt = (EditText)findViewById(R.id.edt);
        tv = (TextView)findViewById(R.id.tv);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = edt.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });
    }

    public void request(String urlStr) {
        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL(urlStr); // URL 객체 생성
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // URL 객체로 HttpURLConnection 객체가 반환됨
            if(httpURLConnection != null) {
                httpURLConnection.setConnectTimeout(10000); // 10초 동안 기다려서 응답이 없으면 끝남
                httpURLConnection.setRequestMethod("GET"); // GET 방식으로 요청함
                httpURLConnection.setDoInput(true); // 이 객체가 입력 가능하도록 설정함
                
                // 입력 데이터를 받기 위한 Reader 객체 생성
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); // 입력을 받을 수 있는 통로 생성
                String line = null;

                while(true) {
                    line = bufferedReader.readLine(); // 한 줄씩 읽음
                    if(line == null) {
                        break;
                    }
                    output.append(line + "\n");
                }
                bufferedReader.close(); // 다 쓰고 닫음
                httpURLConnection.disconnect(); // 연결 끊기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("응답 : " + output.toString()); // 한 줄씩 출력
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.append(data + "\n");
            }
        });
    }
}