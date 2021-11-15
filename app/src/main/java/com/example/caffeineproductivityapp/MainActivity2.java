package com.example.caffeineproductivityapp;


import static com.example.caffeineproductivityapp.MainActivity.dataList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    LineChart lineChart;
    Button entriesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        entriesButton = findViewById(R.id.entriesButton);
        entriesButton.setOnClickListener(this);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        List<Entry> entries = new ArrayList<Entry>();
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setMaxVisibleValueCount(2);

        for (String[] i: dataList) {
            entries.add(new Entry(Float.parseFloat(i[1]), Float.parseFloat(i[0])));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(2);
        dataSet.setValueTextColor(2);
        dataSet.setFillAlpha(110);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);

        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        YAxis yAxis = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        yAxis.setTextSize(10f);
        yAxis.setTextColor(Color.RED);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(true);
        yAxis.setGranularity(1f);

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if (axis == xAxis)
                    return "Time: " + value;
                else
                    return "Amount: " + value;
            }
        };
        xAxis.setValueFormatter(formatter);
        yAxis.setValueFormatter(formatter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.entriesButton) {
            finish();
        }
    }
}