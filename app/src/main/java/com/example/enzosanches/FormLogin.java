package com.example.enzosanches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnLogin;
    private TextView btnCriarConta;
    private boolean isSenhaVisivel = false;

    // VARIÁVEIS DO FIREBASE
    private FirebaseAuth mAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Inicializando os componentes
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCriarConta = findViewById(R.id.btnCriarConta);

        // INICIANDO O FIREBASE
        mAuth = FirebaseAuth.getInstance();

        // Clique do botão de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    tentarLogin();
                }
            }
        });

        // Clique para ir para a tela de Cadastro
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        // Ouvinte do olhinho da senha (Mostrar / Esconder)
        edtSenha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (edtSenha.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
                        if (event.getRawX() >= (edtSenha.getRight() - edtSenha.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - edtSenha.getPaddingRight())) {
                            if (isSenhaVisivel) {
                                // Esconder Senha
                                edtSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                edtSenha.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_eye_off, 0);
                                isSenhaVisivel = false;
                            } else {
                                // Mostrar Senha
                                edtSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                edtSenha.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_eye, 0);
                                isSenhaVisivel = true;
                            }
                            edtSenha.setSelection(edtSenha.getText().length());
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

    private boolean validarCampos() {
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Informe um e-mail válido");
            edtEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(senha)) {
            edtSenha.setError("Informe a palavra-passe");
            edtSenha.requestFocus();
            return false;
        }

        return true;
    }

    private void tentarLogin() {
        String email = edtEmail.getText().toString().trim();
        String senhaDigitada = edtSenha.getText().toString();

        // COMANDO PARA LOGIN NO FIREBASE
        mAuth.signInWithEmailAndPassword(email, senhaDigitada).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // SUCESSO: Abre a classe TelaMenuPrincipal sem erros!
                    Intent intent = new Intent(FormLogin.this, TelaMenuPrincipal.class);
                    startActivity(intent);
                    finish(); // Fecha a tela de login

                } else {
                    Toast.makeText(FormLogin.this, "E-mail ou palavra-passe incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}