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

public class AvaliarResumoTrabalho extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtSiglaNo;
    EditText edtNotaResumo;

    Button btnSalvarNotaResumo;

    Trabalho trabalho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar_resumo_trabalho);
        txtTitulo      = (TextView) findViewById(R.id.txtTituloTrabRes);
        txtSiglaNo     = (TextView) findViewById(R.id.txtSiglaNoRes);
        edtNotaResumo  = (EditText) findViewById(R.id.edtNotaResumo);

        btnSalvarNotaResumo = (Button) findViewById(R.id.btnSalvarNotaResumo);

        Intent intent = getIntent();

        if (intent != null) {

            Bundle parametro = intent.getExtras();

            if (parametro != null) {

                txtTitulo.setText(parametro.getString("titulo"));
                txtSiglaNo.setText(parametro.getString("siglaNo"));
                edtNotaResumo.setText(parametro.getString("notaResumo"));
                edtNotaResumo.requestFocus();
            } else {
                Toast.makeText(this, "PARÂMETRO NULO",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "INTENT NULO",
                    Toast.LENGTH_LONG).show();
        }

        btnSalvarNotaResumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trabalho = new Trabalho();
                trabalho.setNotaResumo(edtNotaResumo.getText().toString());
                trabalho.setSiglaNo(txtSiglaNo.getText().toString());

                DatabaseConnection conexao = new DatabaseConnection();
                DatabaseReference databaseReference =
                        conexao.conectCloudDataBase(getBaseContext());

                databaseReference.child("Trabs").child(trabalho.getSiglaNo()).child("notaResumo").setValue(trabalho.getNotaResumo());

                Toast.makeText(getBaseContext(), "A nota deste trabalho foi atualizada com sucesso.", Toast.LENGTH_LONG).show();
                Intent it = new Intent(AvaliarResumoTrabalho.this, ListarTrabalhos.class);
                startActivity(it);
            }
        });
    }

}