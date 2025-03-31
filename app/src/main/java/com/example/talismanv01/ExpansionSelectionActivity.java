package com.example.talismanv01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExpansionSelectionActivity extends AppCompatActivity {

    private CheckBox expansion1, expansion2, expansion3, expansion4, expansion5,expansion6,expansion7,expansion8,expansion9,expansion10,expansion11,expansion12; // Erweiterungen als Checkboxen
    private Button confirmButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expansion_selection);

        // Checkboxen initialisieren
        expansion1 = findViewById(R.id.checkbox_expansion1);
        expansion2 = findViewById(R.id.checkbox_expansion2);
        expansion3 = findViewById(R.id.checkbox_expansion3);
        expansion4 = findViewById(R.id.checkbox_expansion4);
        expansion5 = findViewById(R.id.checkbox_expansion5);
        expansion6 = findViewById(R.id.checkbox_expansion6);
        expansion7 = findViewById(R.id.checkbox_expansion7);
        expansion8 = findViewById(R.id.checkbox_expansion8);
        expansion9 = findViewById(R.id.checkbox_expansion9);
        expansion10 = findViewById(R.id.checkbox_expansion10);
        expansion11 = findViewById(R.id.checkbox_expansion11);
        expansion12 = findViewById(R.id.checkbox_expansion12);
        confirmButton = findViewById(R.id.btnConfirmExpansions);

        // Button-Klick: Gehe zur LobbyActivity mit den ausgewählten Erweiterungen
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> selectedExpansions = new ArrayList<>();

                if (expansion1.isChecked()) selectedExpansions.add("Expansion1");
                if (expansion2.isChecked()) selectedExpansions.add("Expansion2");
                if (expansion3.isChecked()) selectedExpansions.add("Expansion3");
                if (expansion4.isChecked()) selectedExpansions.add("Expansion4");
                if (expansion5.isChecked()) selectedExpansions.add("Expansion5");
                if (expansion6.isChecked()) selectedExpansions.add("Expansion6");
                if (expansion7.isChecked()) selectedExpansions.add("Expansion7");
                if (expansion8.isChecked()) selectedExpansions.add("Expansion8");
                if (expansion9.isChecked()) selectedExpansions.add("Expansion9");
                if (expansion10.isChecked()) selectedExpansions.add("Expansion10");
                if (expansion11.isChecked()) selectedExpansions.add("Expansion11");
                if (expansion12.isChecked()) selectedExpansions.add("Expansion12");

                // Gehe zur LobbyActivity und übertrage die Auswahl
                Intent intent = new Intent(ExpansionSelectionActivity.this, LobbyActivity.class);
                intent.putStringArrayListExtra("SELECTED_EXPANSIONS", selectedExpansions);
                intent.putExtra("isHost", true); // Markiere als Host
                startActivity(intent);
            }
        });
    }
}

