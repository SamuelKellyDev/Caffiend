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
        timeInput = findViewById(R.id.timeInput);
        entryButton = findViewById(R.id.entryButton);
        entryButton.setOnClickListener(this);
    }
    // This method gets called everytime a button is clicked
    public void onClick(View view) {
        amount = amountInput.getText().toString();
        time = timeInput.getText().toString();
        list.add(amount + "," + time);
        if (view.getId() == R.id.entryButton) {
            TableLayout tbl1 = (TableLayout) findViewById(R.id.tbl1);
            TableRow tblRow1 = new TableRow(this);
            TextView amountText1 = new TextView(this);
            TextView timeText1 = new TextView(this);
            EditText amountText = new EditText(this);
            EditText timeText = new EditText(this);
            amountText1.setText("Amount: ");
            timeText1.setText("Time: ");
            amountText.setText(amount);
            amountText.setTextSize(25);
            timeText.setText(time);
            timeText.setTextSize(25);
            tblRow1.addView(amountText1);
            tblRow1.addView(amountText);
            tblRow1.addView(timeText1);
            tblRow1.addView(timeText);
            tbl1.addView(tblRow1);
        }

        /*
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        Button btnTag = new Button(this);
        btnTag.setLayoutParams((ViewGroup.LayoutParams) );
        btnTag.setText();
        btnTag.setId();
        layout.addView(btnTag);
         */
    }
    /*
    public int[] sort() {
        return;
    }
     */
}