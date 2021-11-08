package com.example.caffeineproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String amount, time;
    EditText amountInput;
    EditText timeInput;
    Button entryButton;
    Button sideButton;
    List<int[]> IDlist = new ArrayList<>();
    static List<String[]> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sideButton = findViewById(R.id.sideButton);
        amountInput = findViewById(R.id.amountInput);
        amountInput.setHint("ex: 23 mg");
        timeInput = findViewById(R.id.timeInput);
        timeInput.setHint("ex: 2:00 PM");
        entryButton = findViewById(R.id.entryButton);
        entryButton.setOnClickListener(this);
        sideButton.setOnClickListener(this);
    }
    // Combines view addition process to table into one method that takes all needed views in as parameters
    public void addViewsToTable(View[] views, TableRow tblRow, TableLayout tbl) {
        for (View i: views)
            tblRow.addView(i);
        tbl.addView(tblRow);
    }
    // Sets text and the size of the text, BP means boilerplate
    public void textConfig(TextView amountText, TextView timeText, String amount, String time, Button deleteButton, int textSize) {
        amountText.setText("Amount: " + amount);
        timeText.setText("Time: " + time);
        deleteButton.setText("Delete");
        amountText.setTextSize(textSize);
        timeText.setTextSize(textSize);
        deleteButton.setTextSize(textSize);
    }
    // This method gets called everytime a button is clicked, BP means boilerplate
    public void onClick(View view) {
        amountInput.setHint("ex: 23 mg");
        timeInput.setHint("ex: 2:00 PM");
        TableRow tblRow1 = new TableRow(this);
        TextView amountText = new TextView(this);
        TextView timeText = new TextView(this);
        if (view.getId() == R.id.entryButton) {
            Button deleteButton = new Button(this);
            deleteButton.setTag("deleteButton");
            TableLayout tbl1 = findViewById(R.id.tbl1);
            textConfig(amountText, timeText, amountInput.getText().toString(), timeInput.getText().toString(), deleteButton, 25);
            String[] dataNums = {amountInput.getText().toString(), timeInput.getText().toString()};
            dataList.add(dataNums);
            View views[] = {deleteButton, amountText, timeText};
            addViewsToTable(views, tblRow1, tbl1);
            deleteButton.setId(View.generateViewId()); // had to set generated id's bc the auto id is just the exact same so delete remove wasn't working
            tblRow1.setId(View.generateViewId());
            int[] arrayNums = {deleteButton.getId(), tblRow1.getId()};
            IDlist.add(arrayNums);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int[] i: IDlist) {
                        if (i[0] == view.getId()) {
                            TableLayout tbl1 = findViewById(R.id.tbl1);
                            tbl1.removeView(findViewById(i[1]));
                            dataList.remove(IDlist.indexOf(i));
                            IDlist.remove(i);
                            break;
                        }
                    }
                }
            });
        }
        else if (view.getId() == R.id.sideButton) {
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
        }
    }
    /*
    public int[] sort() {
        return;
    }
     */
}