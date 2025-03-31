package com.example.calculadorapratica1a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText entrada1, entrada2;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada1 = findViewById(R.id.numero1);
        entrada2 = findViewById(R.id.numero2);
        resultado = findViewById(R.id.resultado);

        Button botaoSoma = findViewById(R.id.btn_somar);
        Button botaoSub = findViewById(R.id.btn_subtrair);
        Button botaoMult = findViewById(R.id.btn_multiplicar);
        Button botaoDiv = findViewById(R.id.btn_dividir);

        botaoSoma.setOnClickListener(v -> operacoes("+"));
        botaoSub.setOnClickListener(v -> operacoes("-"));
        botaoMult.setOnClickListener(v -> operacoes("*"));
        botaoDiv.setOnClickListener(v -> operacoes("/"));

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void operacoes(String operacao) {
        String numero1 = entrada1.getText().toString();
        String numero2 = entrada2.getText().toString();

            double valor1 = Double.parseDouble(numero1);
            double valor2 = Double.parseDouble(numero2);
            double resultadoFinal = 0;

            switch (operacao) {
                case "+":
                    resultadoFinal = valor1 + valor2;
                    break;
                case "-":
                    resultadoFinal = valor1 - valor2;
                    break;
                case "*":
                    resultadoFinal = valor1 * valor2;
                    break;
                case "/":
                    if (valor2 == 0) {
                        resultado.setText("Erro: Divisão por 0 não permitida.");
                        return;
                    }
                    resultadoFinal = valor1 / valor2;
                    break;
            }
            resultado.setText("Resultado: " + resultadoFinal);
    }
}
