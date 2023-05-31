package com.example.congressounirp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.congressounirp.R;
import com.example.congressounirp.model.Avaliador;
import com.example.congressounirp.model.DatabaseConnection;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarAvaliador extends AppCompatActivity {

    private EditText edtNomeAval;
    private EditText edtCodAval;
    private EditText edtEmailAval;
    private EditText edtCelularAval;
    private EditText edtCursoAval;
    private Spinner spnAreaAval;
    private Button btnCadastrarAval;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_avaliador);

        edtCodAval = (EditText) findViewById(R.id.edtCodAval);
        edtNomeAval = (EditText) findViewById(R.id.edtNomeAval);
        edtEmailAval = (EditText) findViewById(R.id.edtEmailAval);
        edtCelularAval = (EditText) findViewById(R.id.edtCelularAval);
        edtCursoAval = (EditText) findViewById(R.id.edtCursoAval);
        spnAreaAval = (Spinner) findViewById(R.id.spnAreaAval);
        btnCadastrarAval = (Button) findViewById(R.id.btnCadastrarAval);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.areaAval,
                android.R.layout.simple_spinner_item);
        spnAreaAval.setAdapter(adapter);

        btnCadastrarAval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Avaliador avaliador = new Avaliador();
                    avaliador.setCodigoAval(edtCodAval.getText().toString());
                    avaliador.setNomeAval(edtNomeAval.getText().toString());
                    avaliador.setEmailAval(edtEmailAval.getText().toString());
                    avaliador.setCursoAval(edtCursoAval.getText().toString());
                    avaliador.setCelularAval(edtCelularAval.getText().toString());
                    avaliador.setAreaAval(spnAreaAval.getSelectedItem().toString());

                    DatabaseConnection conexao = new DatabaseConnection();
                    databaseReference = conexao.conectCloudDataBase(getBaseContext());
                    databaseReference.child("Avals").child(avaliador.getCodigoAval()).setValue(avaliador);

                    Toast.makeText(getBaseContext(), "O cadastro foi realizado com sucesso.", Toast.LENGTH_LONG).show();
                    limparTela();
                }catch (Exception ex){
                    Toast.makeText(getBaseContext(), "Erro, tente novamente." + ex, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void limparTela(){
        this.edtCodAval.setText("");
        this.edtNomeAval.setText("");
        this.edtEmailAval.setText("");
        this.edtCursoAval.setText("");
        this.edtCelularAval.setText("");
        this.edtCodAval.requestFocus();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}