package com.example.accountbook.ui.chart;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.accountbook.R;
import com.example.accountbook.backend.AccountInquiry;
import com.example.accountbook.backend.AccountItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class ChartsFragment extends Fragment {

    private LineChartView lineChartView;
    private PieChartView pieChartViewIncome;
    private PieChartView pieChartViewExpense;
    private LineChartData lineChartData;
    private Line incomeLine;
    private Line expenseLine;
    private Line placeholderLine;
    private Axis axisX;
    private Axis axisY;
    private PieChartData pieChartIncomeData;
    private PieChartData pieChartExpenseData;

    private LinearLayout incomeLabelWrapper;
    private LinearLayout expenseLabelWrapper;

    private AccountInquiry inquiry;

    static final private int LINE_CHART_EXPENSE_COLOR = 0xffff6666;
    static final private int LINE_CHART_INCOME_COLOR = 0xff99cc99;

    static final private int LINE_CHART_AXIS_X_COLOR = 0xff454545;
    static final private int LINE_CHART_AXIS_Y_COLOR = 0xff454545;

    static final private String LINE_CHART_AXIS_X_TEXT = "时间";
    static final private String LINE_CHART_AXIS_Y_TEXT = "收入/支出";

    static final private ValueShape LINE_CHART_INCOME_DOT = ValueShape.DIAMOND;
    static final private ValueShape LINE_CHART_EXPENSE_DOT = ValueShape.SQUARE;

    static final private int[] pieChartLabelColor = {
            0xff996699,
            0xff666666,
            0xff003366,
            0xffffff99,
            0xff99cc99,
            0xff6666cc
    };


    static final private int CHART_ON_UNDEFINED = 0;
    static final private int CHART_ON_MONTH = 1;
    static final private int CHART_ON_SEASON = 2;
    static final private int CHART_ON_YEAR = 3;
    static final private int statusActiveColor = 0xFF00574B;
    static final private int statusInactiveColor = Color.BLACK;
    private int chartStatus;

    private Button buttonSwitchToMonth;
    private Button buttonSwitchToSeason;
    private Button buttonSwitchToYear;

    private TextView incomeSumDisplay;
    private TextView expenseSumDisplay;

    private double income;
    private double expense;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_charts, container, false);
        /**
        final TextView textView = root.findViewById(R.id.text_notifications);
        chartsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */

        /**
         * 1711326 hrl
         * implementation of chart page
         */

        View view = root;

        // init database
        inquiry = new AccountInquiry(this.getActivity());

        // init chart status
        chartStatus = CHART_ON_UNDEFINED;

        // init chart
        lineChartView           = view.findViewById(R.id.chart_line_chart);
        pieChartViewIncome      = view.findViewById(R.id.chart_pie_chart_income);
        pieChartViewExpense     = view.findViewById(R.id.chart_pie_chart_expense);
        pieChartIncomeData      = new PieChartData();
        pieChartExpenseData     = new PieChartData();
        lineChartData           = new LineChartData();
        incomeLine              = new Line();
        expenseLine             = new Line();
        axisX                   = new Axis();
        axisY                   = new Axis();
        placeholderLine = new Line();
        placeholderLine.setHasLines(false);
        placeholderLine.setHasLabels(false);
        placeholderLine.setHasPoints(false);
        List<PointValue> placeholderValue = new ArrayList<>();
        placeholderValue.add(new PointValue(1, 0));
        placeholderValue.add(new PointValue(2, 1));
        placeholderLine.setValues(placeholderValue);

        // init layout
        incomeLabelWrapper      = view.findViewById(R.id.chart_pie_labels_income);
        expenseLabelWrapper     = view.findViewById(R.id.chart_pie_labels_expense);

        // init button
        buttonSwitchToMonth     = view.findViewById(R.id.chart_toggle_button_month);
        buttonSwitchToSeason    = view.findViewById(R.id.chart_toggle_button_season);
        buttonSwitchToYear      = view.findViewById(R.id.chart_toggle_button_year);

        // init textview
        incomeSumDisplay        = view.findViewById(R.id.chart_income_num);
        expenseSumDisplay       = view.findViewById(R.id.chart_expense_num);

        // configure textview
        income = 0.0;
        expense = 0.0;

        // configure chart
        // line chart
        final boolean lineChartHasLine = true;
        final int     axisTextSize     = 12;
        final boolean hasSplitLineX = true;
        final boolean hasSplitLineY = true;
        incomeLine.setHasLines(lineChartHasLine);
        expenseLine.setHasLines(lineChartHasLine);
        incomeLine.setColor(LINE_CHART_INCOME_COLOR);
        expenseLine.setColor(LINE_CHART_EXPENSE_COLOR);
        incomeLine.setShape(LINE_CHART_INCOME_DOT);
        expenseLine.setShape(LINE_CHART_EXPENSE_DOT);
        axisX.setTextSize(axisTextSize);
        axisY.setTextSize(axisTextSize);
        axisX.setTextColor(LINE_CHART_AXIS_X_COLOR);
        axisY.setTextColor(LINE_CHART_AXIS_Y_COLOR);
        axisX.setHasLines(hasSplitLineX);
        axisY.setHasLines(hasSplitLineY);
        axisX.setName(LINE_CHART_AXIS_X_TEXT);
        axisY.setName(LINE_CHART_AXIS_Y_TEXT);

        // pie chart
        final boolean pieChartHasCenterCircle         = true;
        final boolean pieChartHasLabel                = false;
        final boolean pieChartLabelOutside            = false;
        final int     pieChartCenterTextSize          = 26;
        String  pieChartCenterMessageIncome     = "收入";
        String  pieChartCenterMessageExpense    = "支出";
        pieChartIncomeData.setHasCenterCircle(pieChartHasCenterCircle);
        pieChartExpenseData.setHasCenterCircle(pieChartHasCenterCircle);
        pieChartIncomeData.setCenterText1(pieChartCenterMessageIncome);
        pieChartExpenseData.setCenterText1(pieChartCenterMessageExpense);
        pieChartIncomeData.setHasLabels(pieChartHasLabel);
        pieChartExpenseData.setHasLabels(pieChartHasLabel);
        pieChartIncomeData.setHasLabelsOutside(pieChartLabelOutside);
        pieChartExpenseData.setHasLabelsOutside(pieChartLabelOutside);
        pieChartIncomeData.setCenterText1FontSize(pieChartCenterTextSize);
        pieChartExpenseData.setCenterText1FontSize(pieChartCenterTextSize);
        // pieChartViewIncome.setPieChartData(pieChartIncomeData);
        // pieChartViewExpense.setPieChartData(pieChartExpenseData);

        // configure button
        buttonSwitchToMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chartStatus != CHART_ON_MONTH) {
                    switchTo(CHART_ON_MONTH);
                }
            }
        });
        buttonSwitchToSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chartStatus != CHART_ON_SEASON) {
                    switchTo(CHART_ON_SEASON);
                }
            }
        });
        buttonSwitchToYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chartStatus != CHART_ON_YEAR) {
                    switchTo(CHART_ON_YEAR);
                }
            }
        });

        // inquiry.insert(new Date(), 1000, true, AccountItem.TYPE_CONSUME, "gggg");
        Log.e("test", Integer.toString(inquiry.inquiryAll().size()));

        // init data
        switchTo(CHART_ON_MONTH);

        return root;
    }
    private void switchTo(int status) {
        buttonSwitchToMonth.setTextColor(statusInactiveColor);
        buttonSwitchToSeason.setTextColor(statusInactiveColor);
        buttonSwitchToYear.setTextColor(statusInactiveColor);
        switch (status) {
            case CHART_ON_MONTH:
                switchToMonth();
                buttonSwitchToMonth.setTextColor(statusActiveColor);
                break;
            case CHART_ON_SEASON:
                switchToSeason();
                buttonSwitchToSeason.setTextColor(statusActiveColor);
                break;
            case CHART_ON_YEAR:
                switchToYear();
                buttonSwitchToYear.setTextColor(statusActiveColor);
                break;
            case CHART_ON_UNDEFINED:
                Log.e(" - ChartFragment", "Undefined Switch behavior occurred.");
                break;
        }
        chartStatus = status;
        incomeSumDisplay.setText(Double.toString(income));
        expenseSumDisplay.setText(Double.toString(expense));


        List<Line> lines = new ArrayList<>();
        lines.add(incomeLine);
        lines.add(expenseLine);
        lines.add(placeholderLine);
        lineChartData.setLines(lines);
        lineChartData.setAxisXBottom(axisX);
        lineChartData.setAxisYLeft(axisY);
        lineChartView.setLineChartData(lineChartData);
    }

    private void switchToMonth() {
        // 求出最近30天
        Calendar today = Calendar.getInstance();
        Date[] days = new Date[30];
        for (int i = 29; i>=0; i--) {
            days[i] = today.getTime();
            today.add(Calendar.DATE, -1);
        }

        List<AxisValue> axisXLabels = new ArrayList<>();
        for(int i = 0; i< 5; i++) {
            axisXLabels.add(new AxisValue(i).setLabel(Integer.toString(i)));
        }
        axisX.setValues(axisXLabels);
        List<PointValue> points = new ArrayList<>();
        points.add(new PointValue(2, 24));
        incomeLine.setValues(points);

    }

    private void switchToSeason() {

    }

    private void switchToYear() {

    }

}