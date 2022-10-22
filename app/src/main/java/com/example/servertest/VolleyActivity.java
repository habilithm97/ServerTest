package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/*
[Volley]
-웹 요청과 응답을 단순화하기 위해 만들어진 라이브러리
-요청 객체 생성 -> 요청 객체를 요청 큐에 넣으면 요청 큐가 웹서버에 요청하고 응답까지 받아줌
-메인 스레드에서 UI에 접근할 수 있도록 만들어 주는 과정을 다 알아서 함(응답 받으면 바로 UI 업데이트)

-코드 양이 적고 스레드를 신경 쓰지 않아도 된다는 장점이 있음
 */

public class VolleyActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    EditText edt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        edt = (EditText)findViewById(R.id.edt);
        tv = (TextView)findViewById(R.id.tv);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        // 요청 큐 생성 -> 한번만 만들어서 계속 사용함
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void makeRequest() {
        String url = edt.getText().toString();

        // 문자열을 주고 받기 위해 사용하는 요청 객체
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 : " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("오류 : " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        request.setShouldCache(false); // 이전 결과가 있어도 새로 요청하여 응답을 보여줌
        requestQueue.add(request); // 요청 큐에 넣음
        println("요청을 보냄. ");
    }

    public void println(String data) {
        tv.append(data + "\n");
    }
}