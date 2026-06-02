package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        btnVoltarPerfil = findViewById(R.id.btnVoltarPerfil);
        btnSair = findViewById(R.id.btnSair);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Clique para voltar ao Menu Principal
        btnVoltarPerfil.setOnClickListener(v -> finish());

        FirebaseUser usuarioAtual = mAuth.getCurrentUser();
        if (usuarioAtual != null) {
            String uid = usuarioAtual.getUid();
            String email = usuarioAtual.getEmail();

            txtEmailPerfil.setText(email);

            // Busca do Firestore o nome real salvo (Ex: crispim)
            db.collection("Usuarios").document(uid)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nome = documentSnapshot.getString("nome");
                            if (nome != null && !nome.isEmpty()) {
                                txtNomePerfil.setText(nome);
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(TelaPerfil.this, "Erro ao carregar dados.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            txtEmailPerfil.setText("Usuário não autenticado");
        }

        btnSair.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(TelaPerfil.this, FormLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}