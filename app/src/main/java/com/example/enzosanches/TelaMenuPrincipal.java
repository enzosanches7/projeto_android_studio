package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TelaMenuPrincipal extends AppCompatActivity {

    private Button btnAdicionarPeca, btnListaDesejos, btnVerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu_principal2);

        btnAdicionarPeca = findViewById(R.id.btnAdicionarPeca);
        btnListaDesejos = findViewById(R.id.btnListaDesejos);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);

        // 1. Botão Adicionar Peça
        if (btnAdicionarPeca != null) {
            btnAdicionarPeca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TelaMenuPrincipal.this, AdicionarPecaActivity.class);
                    startActivity(intent);
                }
            });
        }

        // 2. Botão Lista de Desejos (Listagem)
        if (btnListaDesejos != null) {
            btnListaDesejos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TelaMenuPrincipal.this, ListarPecasActivity.class);
                    startActivity(intent);
                }
            });
        }

        // 3. Botão Meu Perfil Gamer
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