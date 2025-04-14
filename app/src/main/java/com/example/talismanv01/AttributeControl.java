package com.example.talismanv01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AttributeControl {
    private int value;
    private final int minValue;
    private final TextView tvValue;
    private final TextView tvLabel;
    private final Button btnMinus;
    private final Button btnPlus;

    public AttributeControl(Context context, ViewGroup container, String label, int initialValue, int minValue) {
        this.value = initialValue;
        this.minValue = minValue;

        // Inflate the layout
        View view = LayoutInflater.from(context).inflate(R.layout.attribute_control, container, true);

        // Find all views
        tvLabel = view.findViewById(R.id.tvAttributeName);
        tvValue = view.findViewById(R.id.tvAttributeValue);
        btnMinus = view.findViewById(R.id.btnMinus);
        btnPlus = view.findViewById(R.id.btnPlus);

        // Set initial values
        tvLabel.setText(label);
        tvValue.setText(String.valueOf(value));

        // Set click listeners
        btnMinus.setOnClickListener(v -> {
            if (value > minValue) {
                value--;
                tvValue.setText(String.valueOf(value));
            }
        });

        btnPlus.setOnClickListener(v -> {
            value++;
            tvValue.setText(String.valueOf(value));
        });
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value >= minValue) {
            this.value = value;
            tvValue.setText(String.valueOf(value));
        }
    }
}