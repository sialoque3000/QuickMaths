package com.example.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quickmaths.database.CalculBaseHelper;
import com.example.quickmaths.database.ScoreDao;
import com.example.quickmaths.entities.Score;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Bouton retour
        View btnBack = findViewById(R.id.backButton);
        TextView txtScore = findViewById(R.id.textScore);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
        });

        ScoreDao dbScore = new ScoreDao(new CalculBaseHelper(this,"dbQuickMaths",1));

        Score highScore = dbScore.getHighestScoreEntity();

        String msg = highScore.getName() + " : " + highScore.getScore() + " pts";
        txtScore.setText(msg);
    }
}