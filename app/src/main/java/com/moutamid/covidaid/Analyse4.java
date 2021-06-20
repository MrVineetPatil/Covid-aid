package com.moutamid.covidaid;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

public class Analyse4 extends AppCompatActivity {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    PieDataSet pieDataSet1;
    PieDataSet pieDataSet2;
    ArrayList pieEntries;
    ArrayList pieEntry1;
    ArrayList pieEntry2;
    ArrayList PieEntryLabels;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse4);
        pieChart = findViewById(R.id.pieChart);
        next = findViewById(R.id.next);


        findViewById(R.id.backbtn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Analyse4.this, Analyse3.class));
            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Analyse3.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        pieDataSet = new PieDataSet(dataValues1(), "");
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        pieData = new PieData(pieDataSet);
        pieChart.setUsePercentValues(true);
        // pieChart.setCenterText("Below 60 Vs Gender");
        pieChart.getDescription().setEnabled(false);
        // pieChart.getDescription().setTextSize(30);
        pieChart.animateXY(2000, 2000);
        pieChart.setHoleRadius(50);
        pieChart.setTransparentCircleRadius(60);
        pieChart.setData(pieData);
        pieChart.invalidate();


//        pieEntry1 = new ArrayList<>();
//        pieEntry1.add(new PieEntry(2, "fuck"));
//        pieEntry2 = new ArrayList<>();
//        pieEntry2.add(new PieEntry(1, "you"));
//
//        ArrayList<IPieDataSet> pies = new ArrayList<IPieDataSet>();
//        PieDataSet piedataset = new PieDataSet(pieEntry1, "Above 60+");
//        piedataset.setColor(Color.rgb(0,63,92));
//        piedataset.setValueTextColor(Color.BLACK);
//        piedataset.setValueTextSize(18f);
//        pies.add(piedataset);
//        PieDataSet piedataset2 = new PieDataSet(pieEntry2, "Below 60");
//        piedataset2.setColor(Color.rgb(188,80,144));
//        piedataset2.setValueTextColor(Color.BLACK);
//        piedataset2.setValueTextSize(18f);
//        pies.add(piedataset2);
//
//
//
//        PieData data = new PieData(pies);
//        pieChart.setData(data);
//        pieChart.animateXY(2000,2000);
//        pieChart.getDescription().setEnabled(false);


//        getEntries();
//        pieDataSet = new PieDataSet(pieEntries, "");
//        pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        pieDataSet.setSliceSpace(2f);
//        pieDataSet.setValueTextColor(Color.WHITE);
//        pieDataSet.setValueTextSize(10f);
//        pieDataSet.setSliceSpace(5f);
    }

    //    private void getEntries() {
//        pieEntries = new ArrayList<>();
//        pieEntries.add(new PieEntry(2f, 0));
//        pieEntries.add(new PieEntry(4f, 1));
//        pieEntries.add(new PieEntry(6f, 2));
//        pieEntries.add(new PieEntry(8f, 3));
//        pieEntries.add(new PieEntry(7f, 4));
//        pieEntries.add(new PieEntry(3f, 5));
//    }
    private ArrayList<PieEntry> dataValues1() {
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(23, "No"));
        dataVals.add(new PieEntry(77, "Yes"));
        return dataVals;
    }
}
