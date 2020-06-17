package com.example.accountbook.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.accountbook.R;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView tv_month;
    Button btn_next;
    Button btn_last;
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tv_month=root.findViewById(R.id.text_month);
        btn_next=root.findViewById(R.id.btn_next);
        btn_last=root.findViewById(R.id.btn_last);

        tv_month.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month>1)
                {
                    month=month-1;
                    tv_month.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
                }
                else
                {
                    year=year-1;
                    month=12;
                    tv_month.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month<12)
                {
                    month=month+1;
                    tv_month.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
                }
                else
                {
                    year=year+1;
                    month=1;
                    tv_month.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
                }
            }
        });
        return root;
    }
}