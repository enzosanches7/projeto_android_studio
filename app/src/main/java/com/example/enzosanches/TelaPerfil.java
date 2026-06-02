package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
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

        // Inicializa os componentes do layout
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        btnSair = findViewById(R.id.btnSair);
        btnVoltarPerfil = findViewById(R.id.btnVoltarPerfil);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Ação do botão voltar
        btnVoltarPerfil.setOnClickListener(v -> finish());

        // Ação do botão sair da conta
        btnSair.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(TelaPerfil.this, FormLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        carregarDadosUsuario();
    }

    private void carregarDadosUsuario() {
        if (mAuth.getCurrentUser() != null) {
            String userId = mAuth.getCurrentUser().getUid();

            db.collection("Usuarios").document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nome = documentSnapshot.getString("nome");
                            String email = mAuth.getCurrentUser().getEmail();

                            txtNomePerfil.setText(nome);
                            txtEmailPerfil.setText(email);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(TelaPerfil.this, "Erro ao carregar dados.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            txtEmailPerfil.setText("Usuário não autenticado");
        }
    }
}