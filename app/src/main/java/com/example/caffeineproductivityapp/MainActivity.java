package com.example.caffeineproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountInput = findViewById(R.id.amountInput);
        amountInput.setHint("ex: 23 mg");
        timeInput = findViewById(R.id.timeInput);
        timeInput.setHint("ex: 2:00 PM");
        entryButton = findViewById(R.id.entryButton);
        entryButton.setOnClickListener(this);
    }
    // Combines view addition process to table into one method that takes all needed views in as parameters
    public void addViewsToTable(View[] views, TableRow tblRow, TableLayout tbl) {
        for (View i: views)
            tblRow.addView(i);
        tbl.addView(tblRow);
    }
    // Sets text and the size of the text
    public void textConfig(TextView amountText1, TextView timeText1, EditText amountText, EditText timeText, String amount, String time, int textSize) {
        amountText1.setText("Amount: ");
        timeText1.setText("Time: ");
        amountText.setText(amount);
        timeText.setText(time);
        amountText1.setTextSize(textSize);
        timeText1.setTextSize(textSize);
        amountText.setTextSize(textSize);
        timeText.setTextSize(textSize);
    }
    // This method gets called everytime a button is clicked
    public void onClick(View view) {
        TableRow tblRow1 = new TableRow(this);
        TextView amountText1 = new TextView(this);
        TextView timeText1 = new TextView(this);
        EditText amountText = new EditText(this);
        EditText timeText = new EditText(this);
        amount = amountInput.getText().toString();
        time = timeInput.getText().toString();
        list.add(amount + "," + time);
        if (view.getId() == R.id.entryButton) {
            TableLayout tbl1 = (TableLayout) findViewById(R.id.tbl1);
            textConfig(amountText1, timeText1, amountText, timeText, amount, time, 25);
            View views[] = {amountText1, amountText, timeText1, timeText};
            addViewsToTable(views, tblRow1, tbl1);
        }
    }
    /*
    public int[] sort() {
        return;
    }
     */
}