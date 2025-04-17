package com.example.pratica2_imc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CicloDeVida", "ResultadoActivity - onCreate()");
        setContentView(R.layout.activity_resultado);

        Intent it = getIntent();
        String nome = it.getStringExtra("nome");
        String idadeString = it.getStringExtra("idade");
        String pesoString = it.getStringExtra("peso");
        String alturaString = it.getStringExtra("altura");

        int idade;
        float peso;
        float altura;
        float imc;
        String classificacao;

        // Verifica se os campos estão vazios ou nulos
        if (idadeString == null || idadeString.isEmpty() ||
                pesoString == null || pesoString.isEmpty() ||
                alturaString == null || alturaString.isEmpty()) {
            // Tratar o caso em que os campos estão vazios
            Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_LONG).show();
            finish(); // Fecha a activity, pois não tem dados necessários
            return;
        }

        //Converte os dados somente se existirem
        idade = Integer.parseInt(idadeString);
        peso = Float.parseFloat(pesoString);
        altura = Float.parseFloat(alturaString);

        imc = peso / (altura * altura);

        if (imc < 18.5) classificacao = "Abaixo do peso";
        else if (imc < 25) classificacao = "Saudável";
        else if (imc < 30) classificacao = "Sobrepeso";
        else if (imc < 35) classificacao = "Obesidade Grau I";
        else if (imc < 40) classificacao = "Obesidade Grau II";
        else classificacao = "Obesidade Grau III";

        TextView resultView = findViewById(R.id.textResultado);
        resultView.setText(
                "Nome: " + nome +
                        "\nIdade: " + idade +
                        "\nPeso: " + peso +
                        "\nAltura: " + altura +
                        "\nIMC: " + String.format("%.2f", imc) +
                        "\nClassificação: " + classificacao
        );

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            Intent backIntent = new Intent(this, MainActivity.class);
            backIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(backIntent);
        });
    }

    @Override protected void onStart() { super.onStart(); Log.d("CicloDeVida", "ResultadoActivity - onStart()"); }
    @Override protected void onResume() { super.onResume(); Log.d("CicloDeVida", "ResultadoActivity - onResume()"); }
    @Override protected void onPause() { super.onPause(); Log.d("CicloDeVida", "ResultadoActivity - onPause()"); }
    @Override protected void onStop() { super.onStop(); Log.d("CicloDeVida", "ResultadoActivity - onStop()"); }
    @Override protected void onRestart() { super.onRestart(); Log.d("CicloDeVida", "ResultadoActivity - onRestart()"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d("CicloDeVida", "ResultadoActivity - onDestroy()"); }
}