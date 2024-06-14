package com.example.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnScore;
    private Button btnExit;
    private Button btnInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Bouton Play
        btnPlay = findViewById(R.id.playButton);

        btnPlay.setOnClickListener(view -> {
            //TODO Changer l'interface a affiché
            Intent intent = new Intent(this,activity_info.class);
            startActivity(intent);
        });

        //Bouton Score
        btnScore = findViewById(R.id.scoreButton);

        btnScore.setOnClickListener(view -> {
            //TODO Changer l'interface a affiché
            Intent intent = new Intent(this,activity_info.class);
            startActivity(intent);
        });

        //Bouton Exit
        btnExit = findViewById(R.id.quitButton);

        btnExit.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });

        //Bouton Info
        btnInfo = findViewById(R.id.infoButton);

        btnInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this,activity_info.class);
            startActivity(intent);
        });
    }


}