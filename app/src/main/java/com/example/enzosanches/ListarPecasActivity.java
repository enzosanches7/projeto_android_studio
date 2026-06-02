package com.example.enzosanches;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class ListarPecasActivity extends AppCompatActivity {

    private RecyclerView rvPecas;
    private PecaAdapter adapter;
    private List<Peca> listaPecas;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private TextView btnVoltarListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pecas);

        btnVoltarListar = findViewById(R.id.btnVoltarListar);
        rvPecas = findViewById(R.id.rvPecas);

        listaPecas = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btnVoltarListar.setOnClickListener(v -> finish());

        rvPecas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PecaAdapter(listaPecas);
        rvPecas.setAdapter(adapter);

        // CONFIGURAÇÃO DO CLIQUE LONGO DIRECTA PELO ADAPTER (SEM ERROS DE INTERFACE!)
        adapter.setOnPecaLongClickListener((peca, position) -> {
            abrirDialogExclusao(peca, position);
        });

        carregarPecasDoFirebase();
    }

    private void carregarPecasDoFirebase() {
        String currentUserId = "";
        if (mAuth.getCurrentUser() != null) {
            currentUserId = mAuth.getCurrentUser().getUid();
        }

        db.collection("pecas")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaPecas.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Peca peca = document.toObject(Peca.class);
                            peca.setId(document.getId());
                            listaPecas.add(peca);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ListarPecasActivity.this, "Erro ao carregar peças.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void abrirDialogExclusao(Peca peca, int posicao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Componente");
        builder.setMessage("Deseja mesmo remover a " + peca.getNome() + " da sua lista?");

        builder.setPositiveButton("Sim, Excluir", (dialog, which) -> {
            if (peca.getId() != null) {
                db.collection("pecas").document(peca.getId())
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            if (posicao < listaPecas.size()) {
                                listaPecas.remove(posicao);
                                adapter.notifyItemRemoved(posicao);
                                Toast.makeText(this, "Componente removido!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Erro ao excluir do banco.", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}