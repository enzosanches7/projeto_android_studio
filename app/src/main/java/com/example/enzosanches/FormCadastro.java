package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

// IMPORT FORÇADO PARA ACABAR COM O ERRO DA CLASSE DO MENU
import com.example.enzosanches.TelaMenuPrincipal;

public class FormCadastro extends AppCompatActivity {

    private EditText edtNomeCadastro, edtEmailCadastro, edtSenhaCadastro;
    private Button btnCadastrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        // Inicializando os componentes da tela
        edtNomeCadastro = findViewById(R.id.edtNomeCadastro);
        edtEmailCadastro = findViewById(R.id.edtEmailCadastro);
        edtSenhaCadastro = findViewById(R.id.edtSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        // Inicializando o Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Clique do botão de Cadastrar
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    cadastrarUsuario();
                }
            }
        });
    }

    private boolean validarCampos() {
        String nome = edtNomeCadastro.getText().toString().trim();
        String email = edtEmailCadastro.getText().toString().trim();
        String senha = edtSenhaCadastro.getText().toString();

        if (TextUtils.isEmpty(nome)) {
            edtNomeCadastro.setError("Informe seu nome");
            edtNomeCadastro.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmailCadastro.setError("Informe seu e-mail");
            edtEmailCadastro.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(senha) || senha.length() < 6) {
            edtSenhaCadastro.setError("A senha deve ter pelo menos 6 caracteres");
            edtSenhaCadastro.requestFocus();
            return false;
        }

        return true;
    }

    private void cadastrarUsuario() {
        String email = edtEmailCadastro.getText().toString().trim();
        String senha = edtSenhaCadastro.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(FormCadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                        // Abre a sua TelaMenuPrincipalActivity
                        Intent intent = new Intent(FormCadastro.this, TelaMenuPrincipalgit add ..class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(FormCadastro.this, "Erro ao cadastrar: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}