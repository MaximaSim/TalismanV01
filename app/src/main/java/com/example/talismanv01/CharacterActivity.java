package com.example.talismanv01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CharacterActivity extends AppCompatActivity {
    private GameCharacter character;
    private TextView charName, charStrength, charTalent, charGold, charLife, charFate;
    private Button btnIncreaseStrength, btnDecreaseStrength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        charName = findViewById(R.id.charName);
        charStrength = findViewById(R.id.charStrength);
        charTalent = findViewById(R.id.charTalent);
        charGold = findViewById(R.id.charGold);
        charLife = findViewById(R.id.charLife);
        charFate = findViewById(R.id.charFate);

        btnIncreaseStrength = findViewById(R.id.btnIncreaseStrength);
        btnDecreaseStrength = findViewById(R.id.btnDecreaseStrength);

        // Zufälligen Charakter zuweisen
        CharacterManager characterManager = new CharacterManager();
        character = characterManager.getRandomCharacter();

        // Werte in die UI setzen
        updateUI();

        // Buttons für Werteänderung
        btnIncreaseStrength.setOnClickListener(v -> {
            character.increaseStrength();
            updateUI();
        });

        btnDecreaseStrength.setOnClickListener(v -> {
            character.decreaseStrength();
            updateUI();
        });
    }

    private void updateUI() {
        charName.setText(character.getName());
        charStrength.setText(String.valueOf(character.getStrength()));
        charTalent.setText(String.valueOf(character.getTalent()));
        charGold.setText(String.valueOf(character.getGold()));
        charLife.setText(String.valueOf(character.getLife()));
        charFate.setText(String.valueOf(character.getFate()));
    }
}
