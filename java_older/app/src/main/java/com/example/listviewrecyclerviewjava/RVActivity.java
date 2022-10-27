package com.example.listviewrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.listviewrecyclerviewjava.lvclasses.LVDataClass;
import com.example.listviewrecyclerviewjava.rvclass.RvAdapter;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<LVDataClass> arrayList=new ArrayList<LVDataClass>();

        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "1", "One"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "2", "Two"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "3", "Three"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "4", "Four"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "5", "Five"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "6", "Six"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "7", "Seven"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "8", "Eight"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "9", "Nine"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "10", "Ten"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "11", "Eleven"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "12", "Twelve"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "13", "Thirteen"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "14", "Fourteen"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "15", "Fifteen"));

        RvAdapter rvAdapter=new RvAdapter(arrayList);
        recyclerView.setAdapter(rvAdapter);
    }
}