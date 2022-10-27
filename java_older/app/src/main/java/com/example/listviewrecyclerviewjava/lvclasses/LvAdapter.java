package com.example.listviewrecyclerviewjava.lvclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewrecyclerviewjava.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class LvAdapter extends ArrayAdapter<LVDataClass> {

    List<LVDataClass> arr;

    public LvAdapter(@NonNull Context context, @NonNull List<LVDataClass> objects) {
        super(context, 0,objects);
        this.arr=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.listview_item,parent,false);
        }

        TextView t1=convertView.findViewById(R.id.textView1);
        t1.setText(getItem(position).getNumbersInText());

        TextView t2=convertView.findViewById(R.id.textView2);
        t2.setText(getItem(position).getNumberInDigit());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getContext().getApplicationContext(), "Clicked on Item number"+(position+1)).show();
            }
        });

        return convertView;
    }

    @Nullable
    @Override
    public LVDataClass getItem(int position) {
        return arr.get(position);
    }
}
