package com.example.rock_paper_scissors_app_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosingDifficulty extends AppCompatActivity {

    Button b_easy, b_medium, b_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_difficulty);

        b_easy = findViewById(R.id.b_easy);
        b_medium = findViewById(R.id.b_medium);
        b_hard = findViewById(R.id.b_hard);

        b_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("difficulty","easy");
                startActivity(intent);

            }
        });
        b_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("difficulty","medium");
                startActivity(intent);

            }
        });
        b_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("difficulty","hard");
                startActivity(intent);

            }
        });

    }
}