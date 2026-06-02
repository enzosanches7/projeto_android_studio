package com.example.enzosanches;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdicionarPecaActivity extends AppCompatActivity {

    private EditText edtNomePeca, edtCategoriaPeca, edtPrecoPeca;
    private Button btnSalvarPeca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_peca);

        // Inicializando os componentes do layout
        edtNomePeca = findViewById(R.id.edtNomePeca);
        edtCategoriaPeca = findViewById(R.id.edtCategoriaPeca);
        edtPrecoPeca = findViewById(R.id.edtPrecoPeca);
        btnSalvarPeca = findViewById(R.id.btnSalvarPeca);

        btnSalvarPeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomePeca.getText().toString();
                if (!nome.isEmpty()) {
                    Toast.makeText(AdicionarPecaActivity.this, nome + " salvo temporariamente!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdicionarPecaActivity.this, "Preencha o nome da peça", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}