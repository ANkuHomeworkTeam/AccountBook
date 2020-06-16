package com.example.accountbook.ui;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accountbook.R;

import java.util.List;


public class recycleview_adapter extends RecyclerView.Adapter<recycleview_adapter.MyViewHolder> {
    List<label_item_c> list;
    View inflater;
    //构造方法
    public recycleview_adapter( List<label_item_c> list)
    {
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.label_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        label_item_c item=list.get(position);
        holder.textView.setText(item.getLabel_name());
        holder.imageView.setImageResource(item.image_id);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View labelview;
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            labelview=itemView;
            textView = (TextView) itemView.findViewById(R.id.text_view);
            imageView=(ImageView)itemView.findViewById(R.id.label_image);
        }
    }
}
