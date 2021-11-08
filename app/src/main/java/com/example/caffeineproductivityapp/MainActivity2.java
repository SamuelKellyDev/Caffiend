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

        for (String[] i: dataList) {
            for (String j: i) {
                j = j.replaceAll("[^0-9]", "");
            }
            entries.add(new Entry(Float.parseFloat(i[1]), Float.parseFloat(i[0])));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(2);
        dataSet.setValueTextColor(2);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return "Time: " + value;
            }
        };
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.entriesButton) {
            finish();
        }
    }
}