package com.example.pratica2_imc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nomeEdit;
    EditText idadeEdit;
    EditText pesoEdit;
    EditText alturaEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CicloDeVida", "MainActivity - onCreate()");
        setContentView(R.layout.activity_main);

        nomeEdit = findViewById(R.id.editNome);
        idadeEdit = findViewById(R.id.editIdade);
        pesoEdit = findViewById(R.id.editPeso);
        alturaEdit = findViewById(R.id.editAltura);

        Button btn = findViewById(R.id.btnRelatorio);
        btn.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, ResultadoActivity.class);
            it.putExtra("nome", nomeEdit.getText().toString());
            it.putExtra("idade", idadeEdit.getText().toString());
            it.putExtra("peso", pesoEdit.getText().toString());
            it.putExtra("altura", alturaEdit.getText().toString());
            startActivity(it);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CicloDeVida", "MainActivity - onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CicloDeVida", "MainActivity - onResume()");

        // Limpa os EditTexts
        nomeEdit.setText("");
        idadeEdit.setText("");
        pesoEdit.setText("");
        alturaEdit.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CicloDeVida", "MainActivity - onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CicloDeVida", "MainActivity - onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CicloDeVida", "MainActivity - onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CicloDeVida", "MainActivity - onDestroy()");
    }
}