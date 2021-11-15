package com.example.caffeineproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String amount, time;
    EditText amountInput;
    Button timeInput;
    Button entryButton;
    Button sideButton;
    int hour, minute;
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
        timeInput.setOnClickListener(this);
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
        if ((amountInput.getText().toString().isEmpty() || timeInput.getText().toString().isEmpty()) && view.getId() == R.id.entryButton) {
            Toast toast = Toast.makeText(getApplicationContext(), "Input cannot be empty", 23);
            toast.show();
            return;
        }
        else if (view.getId() == R.id.entryButton) {
            TableRow tblRow1 = new TableRow(this);
            TextView amountText = new TextView(this);
            TextView timeText = new TextView(this);
            Button deleteButton = new Button(this);
            deleteButton.setId(View.generateViewId()); // had to set generated id's bc the auto id is just the exact same so delete remove wasn't working
            tblRow1.setId(View.generateViewId());
            timeText.setId(View.generateViewId());
            amountText.setId(View.generateViewId());
            TableLayout tbl1 = findViewById(R.id.tbl1);
            textConfig(amountText, timeText, amountInput.getText().toString(), timeInput.getText().toString(), deleteButton, 25);
            dataList.add(new String[]{amountInput.getText().toString(), timeInput.getText().toString(), String.valueOf(amountText.getId()), String.valueOf(timeText.getId())});
            addViewsToTable(new View[]{deleteButton, amountText, timeText}, tblRow1, tbl1);
            IDlist.add(new int[]{deleteButton.getId(), tblRow1.getId()});
            // replaces all text besides numbers with nothing to make for easier processing of user provided data
            for (String[] i: dataList) {
                for (int j = 0; j < i.length; j++) {
                    i[j] = i[j].replaceAll("[^0-9]", "");
                }
            }
            if (dataList.size() > 1)
                sort(dataList);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                // Removes views and data when delete button is clicked by searching through the stored data of views
                public void onClick(View view) {
                    for (int[] i: IDlist) {
                        if (i[0] == view.getId()) {
                            TableLayout tbl1 = findViewById(R.id.tbl1);
                            tbl1.removeView(findViewById(i[1]));
                            dataList.remove(IDlist.indexOf(i));
                            IDlist.remove(i);
                            if (dataList.size() > 1)
                                sort(dataList);
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
        else if (view.getId() == R.id.timeInput) {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    timeInput.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                }
            };

            int style = AlertDialog.THEME_HOLO_DARK;
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        }
    }
    // This method sorts data from smallest to largest so graph displays correctly and makes rows in correct order as well
    public void sort(List<String[]> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = i + 1; j < dataList.size(); j++) {
                if (Float.parseFloat(dataList.get(i)[1]) > Float.parseFloat(dataList.get(j)[1])) {
                    TextView amountText1 = findViewById(Integer.parseInt(dataList.get(i)[2]));
                    TextView timeText1 = findViewById(Integer.parseInt(dataList.get(i)[3]));
                    TextView amountText2 = findViewById(Integer.parseInt(dataList.get(j)[2]));
                    TextView timeText2 = findViewById(Integer.parseInt(dataList.get(j)[3]));
                    String[] valueSave = {amountText1.getText().toString(), timeText1.getText().toString()}; // stores data before switch
                    amountText1.setText(amountText2.getText().toString());
                    timeText1.setText(timeText2.getText().toString());
                    amountText2.setText(valueSave[0]);
                    timeText2.setText(valueSave[1]);
                    String[] iData = dataList.get(i); // stores data before switch
                    dataList.set(i, dataList.get(j));
                    dataList.set(j, iData);
                    break;
                }
            }
        }
    }

}