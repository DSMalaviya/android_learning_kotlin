package com.example.listviewrecyclerviewjava.rvclass;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listviewrecyclerviewjava.R;
import com.example.listviewrecyclerviewjava.lvclasses.LVDataClass;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    List<LVDataClass> arr;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

     public RvAdapter(List<LVDataClass> data){
        this.arr=data;
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        TextView t1=holder.itemView.findViewById(R.id.textView1);
        TextView t2=holder.itemView.findViewById(R.id.textView2);

        t1.setText(arr.get(position).getNumbersInText());
        t2.setText(arr.get(position).getNumberInDigit());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(v.getContext().getApplicationContext(), "Clicked on Item number"+(position+1)).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

}
