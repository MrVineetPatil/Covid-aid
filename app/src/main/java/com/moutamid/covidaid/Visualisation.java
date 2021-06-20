package com.moutamid.covidaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class Visualisation extends AppCompatActivity {
    BarChart barChart;
    BarData barData1;
    BarData barData2;
    BarData barData3;
    BarDataSet barDataSet;
    BarDataSet barDataSet2;
    BarDataSet barDataSet3;
    ArrayList barEntry1;
    ArrayList barEntry2;
    ArrayList barEntry3;
    ArrayList barEntry4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualisation);
        barChart = findViewById(R.id.BarChart);

        Intent intent = getIntent();
        double result[] = intent.getDoubleArrayExtra("result");

        barEntry1 = new ArrayList<>();
        barEntry1.add(new BarEntry(1f, (float) result[0]));
        barEntry2 = new ArrayList<>();
        barEntry2.add(new BarEntry(2f, (float)result[1]));
        barEntry3 = new ArrayList<>();
        barEntry3.add(new BarEntry(3f, (float) result[2]));
        barEntry4 = new ArrayList<>();
        barEntry4.add(new BarEntry(4f, (float) result[3]));

        List<IBarDataSet> bars = new ArrayList<IBarDataSet>();
        BarDataSet dataset = new BarDataSet(barEntry1, "LGBM Model");
        dataset.setColor(Color.rgb(0,63,92));
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(18f);
        bars.add(dataset);
        BarDataSet dataset2 = new BarDataSet(barEntry2, "DL Model");
        dataset2.setColor(Color.rgb(122, 81, 149));
        dataset2.setValueTextColor(Color.BLACK);
        dataset2.setValueTextSize(18f);
        bars.add(dataset2);
        BarDataSet dataset3 = new BarDataSet(barEntry3, "ML Model");
        dataset3.setColor(Color.rgb(255,166,0));
        dataset3.setValueTextColor(Color.BLACK);
        dataset3.setValueTextSize(18f);
        bars.add(dataset3);
        BarDataSet dataset4 = new BarDataSet(barEntry4, "C 4.5");
        dataset4.setColor(Color.rgb(	239, 86, 117));
        dataset4.setValueTextColor(Color.BLACK);
        dataset4.setValueTextSize(18f);
        bars.add(dataset4);


        BarData data = new BarData(bars);
        barChart.setData(data);
        barChart.animateXY(2000,2000);
        barChart.getDescription().setEnabled(false);

//        barDataSet1 = new BarDataSet(barEntries, "Different Models for Prediction");
//        barData1 = new BarData(barDataSet1);
//        barChart.setData(barData1);
//
//
//        barDataSet1 = new BarDataSet(yVals1, "Company A");
//        barDataSet1.setColor(Color.rgb(104, 241, 175));
//        barDataSet2 = new BarDataSet(yVals2, "Company B");
//        barDataSet2.setColor(Color.rgb(164, 228, 251));
//        barDataSet3 = new BarDataSet(yVals2, "Company C");
//        barDataSet3.setColor(Color.rgb(164, 254, 600));
//
//        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
//        dataSets.add(set1);
//        dataSets.add(set2);
//
//        BarData data = new BarData(dataSets);
//        barChart.setData(data);

//        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(18f);
//        barChart.animateXY(1000,1000);
//        Description description = barChart.getDescription();
//        description.setText("Percentage Chance of Having COVID");
        //barChart.setDescription("Percentage Chance of Having COVID");

        //barChart.setPivotX();
        //barChart.getDescription().setPosition(1f,1f);
    }
//    private void getEntries() {
//
//
//    }
}