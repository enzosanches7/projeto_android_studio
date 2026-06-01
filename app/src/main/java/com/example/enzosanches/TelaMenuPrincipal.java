package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// Nome da classe igualzinho ao nome do arquivo físico
public class TelaMenuPrincipal extends AppCompatActivity {

    private Button btnAdicionarPeca, btnListaDesejos, btnVerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Deixado com o "2" no final para reconhecer seu layout com sucesso!
        setContentView(R.layout.activity_tela_menu_principal2);

        btnAdicionarPeca = findViewById(R.id.btnAdicionarPeca);
        btnListaDesejos = findViewById(R.id.btnListaDesejos);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);

        if (btnVerPerfil != null) {
            btnVerPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TelaMenuPrincipal.this, TelaPerfil.class);
                    startActivity(intent);
                }
            });
        }
    }
}