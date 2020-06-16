/*** 分类选择/管理页
 * by gw 1711324
 */
package com.example.accountbook.ui;
import com.example.accountbook.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class classification extends AppCompatActivity {
    Button but_return;
    RecyclerView recyclerView;
    recycleview_adapter adapter;
    List<label_item_c> expend_list;
    List<label_item_c> income_list;
    Boolean expend_or_spend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifcation);
        Intent intent=getIntent();
        expend_or_spend=intent.getBooleanExtra("e_or_i",true);
        but_return=(Button)findViewById(R.id.btn_classfication_return);
        recyclerView=(RecyclerView)findViewById(R.id.id_recylerView);
        expend_list=new ArrayList<label_item_c>();
        income_list=new ArrayList<label_item_c>();
        label_item_c terr1=new label_item_c("餐饮",true,R.drawable.canyin);
        label_item_c terr2=new label_item_c("交通",true,R.drawable.jiaotong);
        label_item_c terr3=new label_item_c("娱乐",true,R.drawable.huaban);
        label_item_c terr4=new label_item_c("服饰",true,R.drawable.fushi);
        label_item_c terr5=new label_item_c("其他",true,R.drawable.qita);
        expend_list.add(terr1);
        expend_list.add(terr2);
        expend_list.add(terr3);
        expend_list.add(terr4);
        expend_list.add(terr5);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(expend_or_spend)
            adapter=new recycleview_adapter(expend_list);
        else
            adapter=new recycleview_adapter(income_list);
        recyclerView.setAdapter(adapter);

        but_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(classification.this,Calculate.class);
                startActivity(intent);
            }
        });
    }
}
