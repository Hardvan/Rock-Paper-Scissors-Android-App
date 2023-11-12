package com.example.rock_paper_scissors_app_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_rock, b_paper, b_scissors;
    ImageButton b_home, b_points;
    TextView tv_score, tv_roundno;
    ImageView iv_HumanChoice, iv_ComputerChoice, iv_result;

    int HumanScore, ComputerScore, Draw;
    int round_no = 1;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");

        b_rock = findViewById(R.id.b_rock);
        b_paper = findViewById(R.id.b_paper);
        b_scissors = findViewById(R.id.b_scissors);

        b_home = findViewById(R.id.b_home);
        b_points = findViewById(R.id.b_points);

        tv_score = findViewById(R.id.tv_score);
        tv_roundno = findViewById(R.id.tv_roundno);

        iv_HumanChoice = findViewById(R.id.iv_HumanChoice);
        iv_ComputerChoice = findViewById(R.id.iv_ComputerChoice);
        iv_result = findViewById(R.id.iv_result);

        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play_turn("rock");

            }
        });
        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play_turn("paper");

            }
        });
        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play_turn("scissors");

            }
        });

        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ChoosingDifficulty.class);
                startActivity(intent);

            }
        });

        b_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Points.class);
                intent.putExtra("Wins", Integer.toString(HumanScore));
                intent.putExtra("Draws", Integer.toString(Draw));
                intent.putExtra("Loss", Integer.toString(ComputerScore));
                startActivity(intent);

            }
        });

    }

    public void play_turn(String user_choice) {

        // Set the user image
        setUserImage(user_choice);

        // Getting Computer Choice
        String comp_choice = getCompChoice(user_choice,difficulty);

        // Set the computer image
        setCompImage(comp_choice);

        // Determining Winner
        String message = "";
        String outcome = "";

        if (comp_choice.equals(user_choice)) {

            Draw++;
            outcome = "Draw";
            message = "Draw.  Nobody Won.";

        }

        else if (user_choice.equals("rock") && comp_choice.equals("scissors")) {

            HumanScore++;
            outcome = "Win";
            message = "Rock crushes Scissors. You Win!";

        }

        else if (user_choice.equals("rock") && comp_choice.equals("paper")) {

            ComputerScore++;
            outcome = "Loss";
            message = "Paper engulfs Rock. AI Wins!";

        }

        else if (user_choice.equals("scissors") && comp_choice.equals("rock")) {

            ComputerScore++;
            outcome = "Loss";
            message = "Rock crushes Scissors. Ai Wins!";

        }

        else if (user_choice.equals("scissors") && comp_choice.equals("paper")) {

            HumanScore++;
            outcome = "Win";
            message = "Scissors cuts Paper. You Win!";

        }

        else if (user_choice.equals("paper") && comp_choice.equals("rock")) {

            HumanScore++;
            outcome = "Win";
            message = "Paper engulfs Rock. You Win!";

        }

        else if (user_choice.equals("paper") && comp_choice.equals("scissors")) {

            ComputerScore++;
            outcome = "Loss";
            message = "Scissors cuts Paper. AI Wins!";

        }

        displayResult(outcome, message);

    }

    public String getCompChoice(String user_choice, String difficulty) {

        int no = getRandomNumber();

        if (difficulty.equals("easy")) {

            // 66% chance of user winning
            if (no == 1 || no == 2)
                return getCombination(user_choice, "User Win");

            // 33% chance of computer winning
            else
                return getCombination(user_choice, "Comp Win");

        }

        else if (difficulty.equals("medium")) {

            // Purely Random
            if (no == 1)
                return "rock";
            else if (no == 2)
                return "paper";
            else
                return "scissors";
        }

        else {

            // 33% chance of user winning
            if (no == 1)
                return getCombination(user_choice, "User Win");

            // 66% chance of computer winning
            else
                return getCombination(user_choice, "Comp Win");

        }

    }

    public int getRandomNumber() {

        // Computer chooses 1, 2 or 3
        Random r = new Random();
        return r.nextInt(3) + 1;
    }

    public String getCombination(String user_choice, String who_wins) {

        if (who_wins.equals("User Win")) {

            if (user_choice.equals("rock"))
                return "scissors";
            else if (user_choice.equals("paper"))
                return "rock";
            else
                return "paper";

        }

        else {

            if (user_choice.equals("rock"))
                return "paper";
            else if (user_choice.equals("paper"))
                return "scissors";
            else
                return "rock";

        }

    }

    public void setUserImage(String user_choice) {

        switch(user_choice) {

            case "rock":
                iv_HumanChoice.setImageResource(R.drawable.rock);
                break;
            case "paper":
                iv_HumanChoice.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                iv_HumanChoice.setImageResource(R.drawable.scissors);
                break;

        }
    }

    public void setCompImage(String comp_choice) {

        switch(comp_choice) {

            case "rock":
                iv_ComputerChoice.setImageResource(R.drawable.rock);
                break;
            case "paper":
                iv_ComputerChoice.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                iv_ComputerChoice.setImageResource(R.drawable.scissors);
                break;

        }
    }

    public void displayToast(String message) {

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    public void displayResult(String outcome, String message) {

        displayToast(message);

        displayResultImage(outcome);

        // Displaying updated score
        String score_text = "Score: Human " + HumanScore + " Computer " + ComputerScore;
        tv_score.setText(score_text);

        // Displaying Round No
        String roundno_text = "Round - " + round_no++;
        tv_roundno.setText(roundno_text);

    }

    public void displayResultImage(String outcome) {

        switch(outcome) {

            case "Win":
                iv_result.setImageResource(R.drawable.win);
                break;
            case "Draw":
                iv_result.setImageResource(R.drawable.draw);
                break;
            case "Loss":
                iv_result.setImageResource(R.drawable.loss);
                break;

        }
    }

}