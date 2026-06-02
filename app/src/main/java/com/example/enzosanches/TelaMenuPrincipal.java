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
        // Seu layout com o 2 no final para abrir certinho
        setContentView(R.layout.activity_tela_menu_principal2);

        // Inicializando os botões da tela
        btnAdicionarPeca = findViewById(R.id.btnAdicionarPeca);
        btnListaDesejos = findViewById(R.id.btnListaDesejos);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);

        // Clique para abrir a tela de Adicionar Peça
        if (btnAdicionarPeca != null) {
            btnAdicionarPeca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TelaMenuPrincipal.this, AdicionarPecaActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Clique para abrir a tela de Perfil
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