package com.example.congressounirp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.congressounirp.R;
import com.example.congressounirp.model.DatabaseConnection;
import com.example.congressounirp.model.Trabalho;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AvaliarApresentacaoTrabalho extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtSiglaNo;
    EditText edtNotaApres;
    EditText edtNotaPoster;

    Button btnSalvarNotaApres;

    Trabalho trabalho;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar_apresentacao_trabalho);
        txtTitulo      = (TextView) findViewById(R.id.txtTituloTrabApres);
        txtSiglaNo     = (TextView) findViewById(R.id.txtSiglaNoApres);
        edtNotaApres  = (EditText) findViewById(R.id.edtNotaApres);
        edtNotaPoster  = (EditText) findViewById(R.id.edtNotaPoster);

        btnSalvarNotaApres = (Button) findViewById(R.id.btnSalvarNotaApres);

        Intent intent = getIntent();

        if (intent != null) {

            Bundle parametro = intent.getExtras();

            if (parametro != null) {
                // mostrando os dados recebidos na tela, usando a tela de layout
                txtTitulo.setText(parametro.getString("titulo"));
                txtSiglaNo.setText(parametro.getString("siglaNo"));
                edtNotaApres.setText(parametro.getString("notaApres"));
                edtNotaPoster.setText(parametro.getString("NotaPoster"));
                edtNotaApres.requestFocus();
            } else {
                Toast.makeText(this, "PARÂMETRO NULO",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "INTENT NULO",
                    Toast.LENGTH_LONG).show();
        }

        btnSalvarNotaApres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trabalho = new Trabalho();
                trabalho.setNotaApres(edtNotaApres.getText().toString());
                trabalho.setNotaPoster(edtNotaPoster.getText().toString());
                trabalho.setSiglaNo(txtSiglaNo.getText().toString());

                DatabaseConnection conexao = new DatabaseConnection();
                DatabaseReference databaseReference =
                        conexao.conectCloudDataBase(getBaseContext());

                databaseReference.child("Trabs").child(trabalho.getSiglaNo()).child("notaApres").setValue(trabalho.getNotaApres());
                databaseReference.child("Trabs").child(trabalho.getSiglaNo()).child("notaPoster").setValue(trabalho.getNotaPoster());

                Toast.makeText(getBaseContext(), "A nota deste trabalho foi atualizada com sucesso.", Toast.LENGTH_LONG).show();
                Intent it = new Intent(AvaliarApresentacaoTrabalho.this, ListarTrabalhos.class);
                startActivity(it);
            }
        });
    }
}