/*** 计算页面
 *  by gw 1711324
 */
package com.example.accountbook.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.accountbook.MainActivity;
import com.example.accountbook.R;

public class Calculate extends AppCompatActivity {
    Button btn_return;
    Button btn_expend;
    Button btn_income;
    Button btn_more;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        btn_return=(Button)findViewById(R.id.btn_return);
        btn_expend=(Button)findViewById(R.id.btn_account_expend);
        btn_income=(Button)findViewById(R.id.btn_account_income);
        btn_more=(Button)findViewById(R.id.btn_more);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Calculate.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_expend.setTextColor(Color.parseColor("#008577"));
                btn_income.setTextColor(Color.parseColor("#000000"));
            }
        });
        btn_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_expend.setTextColor(Color.parseColor("#000000"));
                btn_income.setTextColor(Color.parseColor("#008577"));
            }
        });
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
