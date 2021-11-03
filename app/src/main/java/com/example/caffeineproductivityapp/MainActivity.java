package com.example.caffeineproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int amount, time;
    EditText amountInput;
    EditText timeInput;
    Button entryButton;
    TextView amountText;
    TextView timeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountInput = findViewById(R.id.amountInput);
        timeInput = findViewById(R.id.timeInput);
        amountText = findViewById(R.id.amountText);
        timeText = findViewById(R.id.timeText);
        entryButton = findViewById(R.id.entryButton);
        entryButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        amount = Integer.parseInt(amountInput.getText().toString());
        time = Integer.parseInt(timeInput.getText().toString());
        if (view.getId() == R.id.entryButton) {
            amountText.setText("Amount: " + amount);
            timeText.setText("Time: " + time);
        }
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        Button btnTag = new Button(this);
        btnTag.setLayoutParams((ViewGroup.LayoutParams) );
        btnTag.setText();
        btnTag.setId();
        layout.addView(btnTag);
    }
    /*
    public int[] sort() {
        return;
    }
     */
}