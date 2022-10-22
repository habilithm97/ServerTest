
package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
[소켓]
 -> TCP와 UDP 방식이 있음
 -> 대부분의 프로그래밍에서는 TCP 연결을 사용함
 -> 반드시 스레드를 사용해야 됨

 -> 소켓 통신에서는 클라이언트와 서버 사이의 연결이 지속되고 실시간으로 양방향으로 데이터를 주고 받음
*/

public class SocketActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Handler handler = new Handler();

    TextView tv, tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        EditText edt = (EditText)findViewById(R.id.edt);
        tv = (TextView)findViewById(R.id.tv);
        tv1 = (TextView)findViewById(R.id.tv1);

        Button sendBtn = (Button)findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = edt.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button serverStartBtn = (Button)findViewById(R.id.serverStartBtn);
        serverStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    public void send(String data) { // 클라이언트 기능 소켓
        try {
            int portNum = 5001;
            Socket socket = new Socket("localhost", portNum); // 서버 접속, 소켓 객체 생성
            printClientLog("소켓 연결. ");

            // 소켓 데이터로 데이터 전송
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush(); // 출력
            printClientLog("데이터 전송. ");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로부터 받은 데이터 : " + inputStream.readObject());
            socket.close(); // 소켓 해제
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() { // 서버 기능 소켓
        try {
            int portNum = 5001;
            ServerSocket serverSocket = new ServerSocket(portNum); // 소켓 서버 객체 생성
            printServerLog("서버가 시작됨. : " + portNum);

            while (true) {
                // Socket : 클라이언트에서 요청이 들어 왔을 때 대응해주는 객체(클라이언트의 요청 정보)
                // 클라이언트가 접속했을 때 만들어지는 소켓 객체 참조
                Socket socket = serverSocket.accept(); // 대기 상태
                InetAddress clientHost = socket.getLocalAddress(); // IP 주소 받음
                int clientPort = socket.getPort(); // Port 번호 받음
                printServerLog("클라이언트가 연결됨. " + clientHost + " : " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream()); // 들어오는 데이터 처리
                Object obj = inputStream.readObject(); // 클라이언트에서 보낸 객체 읽기
                printServerLog("데이터 받음. " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream()); // 클라이언트 쪽으로 데이터 전송
                outputStream.writeObject("서버의 " + obj);
                outputStream.flush(); // 출력
                printServerLog("데이터 전송. ");

                socket.close(); // 소켓 해제(ㄴ한정적인 리소스 유지 -> 낭비)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printClientLog(final String data) { // 전달되는 파라미터가 그대로 전달되어야 됨
        Log.d(TAG, data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.append(data + "\n");
            }
        });
    }

    public void printServerLog(final String data) {
        Log.d(TAG, data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                tv1.append(data + "\n");
            }
        });
    }
}