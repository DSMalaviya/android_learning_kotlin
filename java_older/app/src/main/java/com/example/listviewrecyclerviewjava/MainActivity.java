package com.example.listviewrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button lvShowBtn=null;
    private Button rvShowButton=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvShowBtn=findViewById(R.id.btn_lv);
        rvShowButton=findViewById(R.id.btn_rv);

        lvShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(MainActivity.this,LVActivity.class);
                startActivity(newIntent);
            }
        });

        rvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(MainActivity.this,RVActivity.class);
                startActivity(newIntent);
            }
        });
    }
}