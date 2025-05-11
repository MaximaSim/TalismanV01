package com.example.talismanv01;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExpansionSelectionActivity extends AppCompatActivity {

    private CheckBox cbCity, cbForest, cbHigh, cbKK, cbDrag, cbKL, cbSn, cbQuell, cbFrost, cbBlood, cbFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expansion_selection);

        cbCity = findViewById(R.id.cbCity);
        cbForest = findViewById(R.id.cbForest);
        cbHigh = findViewById(R.id.cbHigh);
        cbKK = findViewById(R.id.cbKK);
        cbDrag = findViewById(R.id.cbDrag);
        cbKL = findViewById(R.id.cbKL);
        cbSn = findViewById(R.id.cbSn);
        cbQuell = findViewById(R.id.cbQuell);
        cbFrost = findViewById(R.id.cbFrost);
        cbBlood = findViewById(R.id.cbBlood);
        cbFire = findViewById(R.id.cbFire);

        Button confirmButton = findViewById(R.id.btnConfirm);
        confirmButton.setOnClickListener(v -> {
            ArrayList<String> selectedExpansions = new ArrayList<>();
            selectedExpansions.add("Base");
            if (cbCity.isChecked()) selectedExpansions.add("City") ;
            if (cbForest.isChecked()) selectedExpansions.add("Forest");
            if(cbHigh.isChecked()) selectedExpansions.add("High");
            if(cbKK.isChecked()) selectedExpansions.add("KK");
            if(cbDrag.isChecked()) selectedExpansions.add("Drag");
            if(cbKL.isChecked()) selectedExpansions.add("KL");
            if(cbSn.isChecked()) selectedExpansions.add("Sn");
            if(cbQuell.isChecked()) selectedExpansions.add("Quell");
            if(cbFrost.isChecked()) selectedExpansions.add("Frost");
            if(cbBlood.isChecked()) selectedExpansions.add("Blood");
            if(cbFire.isChecked()) selectedExpansions.add("Fire");

            if(!selectedExpansions.isEmpty()) {
                Intent intent = new Intent(this, CharacterSelectionActivity.class);
                intent.putStringArrayListExtra("SELECTED_EXPANSIONS", selectedExpansions);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Wähle eine Erweiterung",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

