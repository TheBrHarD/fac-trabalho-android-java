package com.example.congressounirp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.congressounirp.R;
import com.example.congressounirp.model.DatabaseConnection;
import com.example.congressounirp.model.Trabalho;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastrarTrabalho extends AppCompatActivity {
    private EditText edtsiglaNo;
    private EditText edttitulo;
    private EditText edtautor;
    private EditText edtinstituicao;
    private EditText edtcodigoAvalResuno;
    private EditText edtcodigoAvalApres;
    private EditText edtorientador;
    private Spinner spnCategoria;
    private Button cadastrarTrab;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_trabalho);

        edtsiglaNo = findViewById(R.id.edtSiglaNo);
        edtorientador = findViewById(R.id.edtOrientador);
        edttitulo = findViewById(R.id.edtTitulo);
        edtautor = findViewById(R.id.edtAutor);
        edtautor = findViewById(R.id.edtAutor);
        edtinstituicao = findViewById(R.id.edtInstituicao);
        edtcodigoAvalResuno = findViewById(R.id.edtCodigoAvalResuno);
        edtcodigoAvalApres = findViewById(R.id.edtCodigoAvalApres);
        spnCategoria = findViewById(R.id.spnCategoria);
        cadastrarTrab = findViewById(R.id.btnCadastrarTrab);

        spnCategoria = findViewById(R.id.spnCategoria);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.categoria,
                android.R.layout.simple_spinner_item);
        spnCategoria.setAdapter(adapter);

        cadastrarTrab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Trabalho trabalho= new Trabalho();
                    trabalho.setId(UUID.randomUUID().toString());
                    trabalho.setSiglaNo(edtsiglaNo.getText().toString());
                    trabalho.setNomeOrientador(edtorientador.getText().toString());
                    trabalho.setTitulo(edttitulo.getText().toString());
                    trabalho.setNomeAutor(edtautor.getText().toString());
                    trabalho.setInstituicao(edtinstituicao.getText().toString());
                    trabalho.setCodigoAvalResumo(edtcodigoAvalResuno.getText().toString());
                    trabalho.setCodigoAvalApres(edtcodigoAvalApres.getText().toString());
                    trabalho.setCategoria(spnCategoria.getSelectedItem().toString());

                    DatabaseConnection conexao = new DatabaseConnection();
                    databaseReference = conexao.conectCloudDataBase(getBaseContext());
                    databaseReference.child("Trabs").child(trabalho.getSiglaNo()).setValue(trabalho);

                    Toast.makeText(getBaseContext(), "O cadastro foi realizado com sucesso.", Toast.LENGTH_LONG).show();
                    limparTela();
                } catch (Exception ex) {
                    Toast.makeText(getBaseContext(), "Erro, tente novamente." + ex, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void limparTela(){
        this.edtsiglaNo.setText("");
        this.edttitulo.setText("");
        this.edtautor.setText("");
        this.edtorientador.setText("");
        this.edtinstituicao.setText("");
        this.edtcodigoAvalResuno.setText("");
        this.edtcodigoAvalApres.setText("");
        this.edtsiglaNo.requestFocus();

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}