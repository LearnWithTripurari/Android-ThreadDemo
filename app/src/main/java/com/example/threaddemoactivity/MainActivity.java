package com.example.threaddemoactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle msgData = msg.getData();
            String message = msgData.getString("Msg");

            TextView textView = findViewById(R.id.textView);
            textView.setText(message);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDownloadBtnClicked(View view) {

        Thread threadDownload = new Thread(new Runnable() {

            Message message = new Message();
            Bundle bundle = new Bundle();

            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bundle.putString("Msg", "File Downloaded successfully!");
                message.setData(bundle);

                handler.sendMessage(message);
            }
        });

        threadDownload.start();

        TextView textvi = findViewById(R.id.textView);
        textvi.setText("Button CLicked");
    }
}