package iesb.br.caravanapi.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import iesb.br.caravanapi.DAO.FirebaseConfig;
import iesb.br.caravanapi.Entidades.Usuarios;
import iesb.br.caravanapi.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private FirebaseAuth autenticacao;
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
                    usuario =new Usuarios();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    ValidarLogin();
                }else
                    Toast.makeText(LoginActivity.this, "Preencha os Campos de Email e senha", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ValidarLogin(){
    autenticacao = FirebaseConfig.getFirebaseAutenticacao();
    autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){

                abrirTelaPrincipal();
                Toast.makeText(LoginActivity.this, "LoginActivity efetuado com Sucesso", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
    public void abrirTelaPrincipal(){
        Intent intentabrirTelaPrincipal = new Intent(LoginActivity.this,Principal.class);
        startActivity(intentabrirTelaPrincipal);
    }
}
