package com.example.admin.hw1_hulbert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CEG4110_Hulbert_HW1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceg4110__hulbert__hw1);

        Button btSecondActivity = findViewById(R.id.DrawingButton);
        Button btFirstActivity = findViewById(R.id.TextColorChanger);

        btFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ColorChanger.class);
                startActivity(startIntent);
            }
        });
        btSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrawingActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
