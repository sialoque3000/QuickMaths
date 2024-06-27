package com.example.quickmaths;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private int score = 0;
    private int life = 3;
    private TypeOperation typeOperation;
    private Random random = new Random(System.currentTimeMillis());
    private TextView TxtInput;
    private Button btnValidate;
    private TextView TxtCalcul;
    private Menu menu;

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
        TxtCalcul = findViewById(R.id.TxtCalcul);

        premierTerme = random.nextInt(MAX_BOUNDS);
        deuxiemeTerme = random.nextInt(MAX_BOUNDS);
        typeOperation = TypeOperation.ADD;
        typeOperation = typeOperation.setValue(random.nextInt(4));
        resultat = RetourResultat(premierTerme,deuxiemeTerme);
        Affichage();

        btnValidate.setOnClickListener(view -> {
            if (Comparaison()) {
                score++;
                premierTerme = random.nextInt(MAX_BOUNDS);
                deuxiemeTerme = random.nextInt(MAX_BOUNDS);
                typeOperation = typeOperation.setValue(random.nextInt(4));
                resultat = RetourResultat(premierTerme,deuxiemeTerme);
                Affichage();
            }
            else {
                switch (life) {
                    case 3:
                        MenuItem heart3 = menu.findItem(R.id.heart3);
                        heart3.setIcon(R.drawable.black_heart);
                        break;
                    case 2:
                        MenuItem heart2 = menu.findItem(R.id.heart2);
                        heart2.setIcon(R.drawable.black_heart);
                        break;
                    case 1:
                        MenuItem heart1 = menu.findItem(R.id.heart1);
                        heart1.setIcon(R.drawable.black_heart);
                        break;
                    default:
                        break;
                }
                life--;
                premierTerme = random.nextInt(MAX_BOUNDS);
                deuxiemeTerme = random.nextInt(MAX_BOUNDS);
                typeOperation = typeOperation.setValue(random.nextInt(4));
                resultat = RetourResultat(premierTerme,deuxiemeTerme);
                Affichage();

                if(life == 0) {
                    CustomDialogFragment diagFrag = CustomDialogFragment.newInstance(score);
                    diagFrag.show(getSupportFragmentManager(),"CustomDialogFragment");
                }
            }
            TxtInput.setText("");
        });
    }

    private int RetourResultat(int a,int b) {
        switch (typeOperation) {
            case ADD:
                return a + b;
            case SUBSTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
        }

        return 0;
    }

    private boolean Comparaison() {
        int input = Integer.parseInt(TxtInput
                .getText()
                .toString()
                .trim()
        );
        return input == resultat;
    }

    private void Affichage() {
        // Texte du calcul
        String text = premierTerme + " " + typeOperation.getSymbole() + " " + deuxiemeTerme;
        TxtCalcul.setText(text);

        //Score
        if(menu != null) {
            MenuItem scoreItem = menu.findItem(R.id.score);
            scoreItem.setTitle(score+" Pts");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem heart1 = menu.findItem(R.id.heart1);
        MenuItem heart2 = menu.findItem(R.id.heart2);
        MenuItem heart3 = menu.findItem(R.id.heart3);
        MenuItem score = menu.findItem(R.id.score);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }
}