package com.example.congressounirp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.congressounirp.R;
import com.example.congressounirp.model.DatabaseConnection;
import com.example.congressounirp.model.Trabalho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Procurar_Aval_Apresentacao extends AppCompatActivity {

    private Trabalho usuarioSelecionado;

    private ListView lstTrabApres;
    private List<Trabalho> lstTrabalhosApres;
    private ArrayAdapter<Trabalho> arrayAdapterTrabalhoApres;

    EditText edtBCodigoAvalApres;
    Button btnBTrabAvalApres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_aval_apresentacao);

        edtBCodigoAvalApres = (EditText) findViewById(R.id.edtBuscarAvalApres);

        lstTrabApres = (ListView) findViewById(R.id.lstTrabApres);
        lstTrabalhosApres = new ArrayList<Trabalho>();

        btnBTrabAvalApres = (Button) findViewById(R.id.btnBuscarApres);
        btnBTrabAvalApres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    eventoDataBase();
                    lstTrabApres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            usuarioSelecionado = (Trabalho)parent.getItemAtPosition(position);

                            Bundle parametro = new Bundle();

                            parametro.putString("siglaNo",       usuarioSelecionado.getSiglaNo());
                            parametro.putString("titulo",     usuarioSelecionado.getTitulo());
                            parametro.putString("notaApres",    usuarioSelecionado.getNotaApres());
                            parametro.putString("notaPoster",    usuarioSelecionado.getNotaPoster());

                            Intent it = new Intent(Procurar_Aval_Apresentacao.this, AvaliarApresentacaoTrabalho.class);
                            it.putExtras(parametro);
                            startActivity(it);
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(getBaseContext(), "Erro, por favor tente novamente." + ex, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void eventoDataBase() {

        DatabaseConnection conexao = new DatabaseConnection();
        DatabaseReference databaseReference =
                conexao.conectCloudDataBase(getBaseContext());

        databaseReference.child("Trabs").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        lstTrabalhosApres.clear();

                        for(DataSnapshot objSnapShot:dataSnapshot.getChildren()){
                            Trabalho trabalho = objSnapShot.getValue(Trabalho.class);
                            if(trabalho.getCodigoAvalApres().equals(edtBCodigoAvalApres.getText().toString())) {
                                lstTrabalhosApres.add(trabalho);
                            }
                        }

                        arrayAdapterTrabalhoApres = new ArrayAdapter<Trabalho>
                                (Procurar_Aval_Apresentacao.this,
                                        android.R.layout.simple_list_item_1, lstTrabalhosApres);
                        lstTrabApres.setAdapter(arrayAdapterTrabalhoApres);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}