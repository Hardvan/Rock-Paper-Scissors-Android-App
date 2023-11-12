package com.example.rock_paper_scissors_app_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Points extends AppCompatActivity {

    TextView tv_points_display;

    int wins, draws, loss;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);

        tv_points_display = findViewById(R.id.tv_points_display);

        Intent intent = getIntent();
        wins = Integer.parseInt(intent.getStringExtra("Wins"));
        draws = Integer.parseInt(intent.getStringExtra("Draws"));
        loss = Integer.parseInt(intent.getStringExtra("Loss"));

        calculatePoints();

    }

    public void calculatePoints() {

        points = getPoints();

        tv_points_display.setText(points);

    }

    public int getPoints() {

        float total = wins + draws + loss;

        float wins_perc = (wins/total)*100;
        float draws_perc = (draws/total)*100;
        float loss_perc = (loss/total)*100;

        int win_wt = 4;
        int draw_wt = 2;
        int loss_wt = 1;

        float weighted_average = (wins_perc*win_wt + draws_perc*draw_wt + loss_perc*loss_wt)/(win_wt+draw_wt+loss_wt);

        int p = Math.round(weighted_average);

        // Randomise "p" more.

        return p;

    }

}