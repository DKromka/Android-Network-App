package com.example.messenger_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText IP_Address;
    EditText Port;
    TextView Connection;

    String numIP, numPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected  void onResume(){
        super.onResume();
        init();
    }
    private void init(){
        IP_Address.findViewById(R.id.edit_IP);
        Port.findViewById(R.id.edit_Port);
        Connection.findViewById(R.id.textView_Connection);
    }
}