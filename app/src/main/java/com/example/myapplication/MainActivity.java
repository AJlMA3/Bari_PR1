package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editA, editB, editX;
    private Button btnCalculate;
    private TextView txtResult;

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

        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editX = findViewById(R.id.editX);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        try {
            String strA = editA.getText().toString().trim();
            String strB = editB.getText().toString().trim();
            String strX = editX.getText().toString().trim();

            if (strA.isEmpty() || strB.isEmpty() || strX.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                return;
            }

            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);
            double x = Double.parseDouble(strX);
            double y;

            if (x <= 9) {
                // y = (a + b)^2 / x^2
                y = Math.pow(a + b, 2) / Math.pow(x, 2);
            } else {
                // y = x * (a^2 + b^2)
                y = x * (Math.pow(a, 2) + Math.pow(b, 2));
            }

            txtResult.setText("Результат: " + String.format("%.2f", y));

        } catch (NumberFormatException e) {
            txtResult.setText("Ошибка: введите корректные числа");
        }
    }
}
