package com.example.enzosanches;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private EditText edit_email, edit_senha;
    private AppCompatButton btn_entrar;
    private TextView txt_ir_cadastro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        mAuth = FirebaseAuth.getInstance();

        // Inicialização dos componentes da tela
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        btn_entrar = findViewById(R.id.btn_entrar);
        txt_ir_cadastro = findViewById(R.id.txt_ir_cadastro);

        // Transição para a tela de cadastro
        txt_ir_cadastro.setOnClickListener(v -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });

        // Clique do botão de Login
        btn_entrar.setOnClickListener(v -> {
            String email = edit_email.getText().toString().trim();
            String senha = edit_senha.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(FormLogin.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                // Chama a autenticação do Firebase
                autenticarUsuario(email, senha);
            }
        });
    }

    private void autenticarUsuario(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // LOGIN OK! Abre a tela que tem as 3 opções do app
                        // NOTA: Se a sua classe das 3 opções se chamar 'MainActivity', deixe como está.
                        // Se ela se chamar de outro jeito (ex: MenuOptions), mude ali embaixo para MenuOptions.class
                        Intent intent = new Intent(FormLogin.this, TelaMenuPrincipal.class);
                        startActivity(intent);

                        // Finaliza a tela de login para o fluxo ficar limpo
                        finish();
                    } else {
                        // Alerta caso o erro seja de usuário/senha incorretos
                        Toast.makeText(FormLogin.this, "Erro: Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}