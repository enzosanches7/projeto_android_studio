package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class TelaPerfil extends AppCompatActivity {

    private TextView txtNomePerfil, txtEmailPerfil, btnVoltarPerfil;
    private Button btnSair;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Inicializa os componentes do layout perfeitamente
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        btnSair = findViewById(R.id.btnSair);
        btnVoltarPerfil = findViewById(R.id.btnVoltarPerfil);

        // Ação do botão voltar
        btnVoltarPerfil.setOnClickListener(v -> {
            finish();
        });

        // Ação do botão Sair da Conta
        btnSair.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(TelaPerfil.this, FormLogin.class);
            startActivity(intent);
            finish();
        });
    }
}