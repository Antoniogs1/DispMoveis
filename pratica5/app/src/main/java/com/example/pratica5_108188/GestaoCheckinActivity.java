package com.example.pratica5_108188;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GestaoCheckinActivity extends AppCompatActivity {

    private DB dbHelper;
    private LinearLayout containerPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestao_checkin);

        dbHelper = new DB(this);
        containerPrincipal = findViewById(R.id.container_principal);

        loadCheckinList();
    }

    private void loadCheckinList() {
        // Limpa a lista antes de recarregar para evitar itens duplicados
        containerPrincipal.removeAllViews();
        Cursor cursor = dbHelper.getAllCheckinsWithCategory();

        if (cursor.moveToFirst()) {
            do {
                final String localName = cursor.getString(cursor.getColumnIndexOrThrow("Local"));

                // 1. Criar uma nova linha (LinearLayout horizontal) para cada item
                LinearLayout rowLayout = new LinearLayout(this);
                rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                rowLayout.setPadding(8, 8, 8, 8);
                rowLayout.setGravity(Gravity.CENTER_VERTICAL);

                // 2. Criar o TextView para o nome do local
                TextView tvLocal = new TextView(this);
                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                        0, // Largura zero para que o peso funcione
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f); // Peso 1 para ocupar o espaço disponível
                tvLocal.setLayoutParams(textParams);
                tvLocal.setText(localName);
                tvLocal.setTextSize(16f);

                // 3. Criar o ImageButton para deletar
                ImageButton btnDelete = new ImageButton(this);
                btnDelete.setImageResource(android.R.drawable.ic_delete);
                btnDelete.setTag(localName);
                btnDelete.setOnClickListener(v -> showDeleteConfirmationDialog((String) v.getTag()));

                // 4. Adicionar o TextView e o Botão à LINHA
                rowLayout.addView(tvLocal);
                rowLayout.addView(btnDelete);

                // 5. Adicionar a LINHA COMPLETA ao container principal
                containerPrincipal.addView(rowLayout);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void showDeleteConfirmationDialog(final String localName) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão")
                .setMessage("Tem certeza que deseja excluir " + localName + "?")
                .setPositiveButton("SIM", (dialog, which) -> {
                    dbHelper.deleteCheckin(localName);
                    Toast.makeText(GestaoCheckinActivity.this, localName + " excluído.", Toast.LENGTH_SHORT).show();
                    // Apenas recarrega a lista, é mais eficiente que reiniciar a Activity
                    loadCheckinList();
                })
                .setNegativeButton("NÃO", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gestao_relatorio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.voltar) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}