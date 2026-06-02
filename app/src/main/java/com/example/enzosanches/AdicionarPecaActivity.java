package com.example.enzosanches;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AdicionarPecaActivity extends AppCompatActivity {

    private EditText editNomePeca, editCategoriaPeca, editPrecoPeca;
    private Button btnSalvarComponente;
    private TextView btnVoltarAdicionar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_peca);

        editNomePeca = findViewById(R.id.editNomePeca);
        editCategoriaPeca = findViewById(R.id.editCategoriaPeca);
        editPrecoPeca = findViewById(R.id.editPrecoPeca);
        btnSalvarComponente = findViewById(R.id.btnSalvarComponente);
        btnVoltarAdicionar = findViewById(R.id.btnVoltarAdicionar);

        db = FirebaseFirestore.getInstance();

        // Clique para voltar ao Menu Principal
        btnVoltarAdicionar.setOnClickListener(v -> finish());

        // Ação para salvar os dados no Firebase
        btnSalvarComponente.setOnClickListener(v -> {
            String nome = editNomePeca.getText().toString().trim();
            String categoria = editCategoriaPeca.getText().toString().trim();
            String preco = editPrecoPeca.getText().toString().trim();

            if (nome.isEmpty() || categoria.isEmpty() || preco.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> peca = new HashMap<>();
            peca.put("nome", nome);
            peca.put("categoria", categoria);
            peca.put("preco", "R$ " + preco);

            db.collection("pecas")
                    .add(peca)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Componente salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        finish(); // Voltar automaticamente após salvar
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Erro ao salvar componente.", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}