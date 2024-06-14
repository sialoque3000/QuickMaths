package com.example.quickmaths;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private static final int MAX_BOUNDS = 50;
    private int premierTerme;
    private int deuxiemeTerme;
    private int resultat;
    private TypeOperation typeOperation;
    private Random random = new Random(System.currentTimeMillis());
    private TextView TxtInput;
    private Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TxtInput = findViewById(R.id.TxtInput);
        btnValidate = findViewById(R.id.validButton);

        premierTerme = random.nextInt(MAX_BOUNDS);
        deuxiemeTerme = random.nextInt(MAX_BOUNDS);
        typeOperation = typeOperation.setValue(random.nextInt(4));
        RetourResultat();

        btnValidate.setOnClickListener(view -> {
            if (Comparaison()) {

            }
        });
    }

    private void RetourResultat() {
        switch (typeOperation) {
            case ADD:
                resultat = premierTerme + deuxiemeTerme;
                break;
            case SUBSTRACT:
                resultat = premierTerme - deuxiemeTerme;
                break;
            case MULTIPLY:
                resultat = premierTerme * deuxiemeTerme;
                break;
            case DIVIDE:
                resultat = premierTerme / deuxiemeTerme;
                break;
        }
    }

    private boolean Comparaison() {
        int input = Integer.parseInt(TxtInput.getText().toString());
        return input == resultat;
    }
}