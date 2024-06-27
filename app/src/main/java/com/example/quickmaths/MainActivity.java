package com.example.quickmaths;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

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
    private ImageButton btnTwitter;
    private ImageButton btnInstagram;
    private ImageButton btnDiscord;

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

        // Bouton Play
        btnPlay = findViewById(R.id.playButton);
        btnPlay.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        });

        // Bouton Score
        btnScore = findViewById(R.id.scoreButton);
        btnScore.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        });

        // Bouton Exit
        btnExit = findViewById(R.id.backButton);
        btnExit.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });

        // Bouton Info
        btnInfo = findViewById(R.id.infoButton);
        btnInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, activity_info.class);
            startActivity(intent);
        });

        // Bouton Twitter
        btnTwitter = findViewById(R.id.twitterButton);
        btnTwitter.setOnClickListener(view -> openWebPage("https://twitter.com"));

        // Bouton Instagram
        btnInstagram = findViewById(R.id.InstagramButton);
        btnInstagram.setOnClickListener(view -> openWebPage("https://instagram.com"));

        // Bouton Discord
        btnDiscord = findViewById(R.id.discordButton);
        btnDiscord.setOnClickListener(view -> openWebPage("https://discord.com"));
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}