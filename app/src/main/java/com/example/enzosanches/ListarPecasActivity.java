package com.example.enzosanches;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class ListarPecasActivity extends AppCompatActivity {

    private RecyclerView rvPecas;
    private PecaAdapter adapter;
    private List<Peca> listaPecas;
    private FirebaseFirestore db;
    private TextView btnVoltarListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pecas);

        btnVoltarListar = findViewById(R.id.btnVoltarListar);
        rvPecas = findViewById(R.id.rvPecas);

        listaPecas = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        // Clique para voltar ao Menu Principal
        btnVoltarListar.setOnClickListener(v -> finish());

        rvPecas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PecaAdapter(listaPecas);
        rvPecas.setAdapter(adapter);

        carregarPecasDoFirebase();
    }

    private void carregarPecasDoFirebase() {
        db.collection("pecas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaPecas.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Peca peca = document.toObject(Peca.class);
                            listaPecas.add(peca);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ListarPecasActivity.this, "Erro ao carregar peças.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}