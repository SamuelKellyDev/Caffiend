package com.example.caffeineproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
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
    List<int[]> list = new ArrayList<int[]>();
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
    // Sets text and the size of the text, BP means boilerplate
    public void textConfig(TextView amountTextBP, TextView timeTextBP, EditText amountText, EditText timeText, String amount, String time, Button deleteButton, int textSize) {
        amountTextBP.setText("Amount: ");
        timeTextBP.setText("Time: ");
        amountText.setText(amount);
        timeText.setText(time);
        deleteButton.setText("Delete");
        amountTextBP.setTextSize(textSize);
        timeTextBP.setTextSize(textSize);
        amountText.setTextSize(textSize);
        timeText.setTextSize(textSize);
        deleteButton.setTextSize(textSize);
    }
    // This method gets called everytime a button is clicked, BP means boilerplate
    public void onClick(View view) {
        setContentView(R.layout.activity_main);
        TableRow tblRow1 = new TableRow(this);
        TextView amountTextBP = new TextView(this);
        TextView timeTextBP = new TextView(this);
        EditText amountText = new EditText(this);
        EditText timeText = new EditText(this);
        if (view.getId() == R.id.entryButton) {
            Button deleteButton = new Button(this);
            deleteButton.setTag("deleteButton");
            TableLayout tbl1 = findViewById(R.id.tbl1);
            textConfig(amountTextBP, timeTextBP, amountText, timeText, amountInput.getText().toString(), timeInput.getText().toString(), deleteButton, 25);
            View views[] = {deleteButton, amountTextBP, amountText, timeTextBP, timeText};
            addViewsToTable(views, tblRow1, tbl1);
            int[] arrayNums = {deleteButton.getId(), tblRow1.getId()};
            list.add(arrayNums);
            deleteButton.setOnClickListener(this);
        }
        else if (view.getTag() == "deleteButton") {
            TableLayout tbl1 = findViewById(R.id.tbl1);
            for (int[] i: list) {
                if (i[0] == view.getId())
                    tbl1.removeView(findViewById(i[1]));
            }
        }
    }
    /*
    public int[] sort() {
        return;
    }
     */
}