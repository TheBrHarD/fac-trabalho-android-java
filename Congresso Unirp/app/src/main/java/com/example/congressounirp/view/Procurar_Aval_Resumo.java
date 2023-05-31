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

public class Procurar_Aval_Resumo extends AppCompatActivity {

    private Trabalho usuarioSelecionado;

    private ListView lstTrabResumo;
    private List<Trabalho> lstTrabalhos;
    private ArrayAdapter<Trabalho> arrayAdapterTrabalhoRes;

    EditText edtBCodigoAvalRes;
    Button btnBTrabAvalRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_aval_resumo);

        edtBCodigoAvalRes = (EditText) findViewById(R.id.edtBuscarAvalRes);

        lstTrabResumo = (ListView) findViewById(R.id.lstTrabResumo);
        lstTrabalhos = new ArrayList<Trabalho>();

        btnBTrabAvalRes = (Button) findViewById(R.id.btnBuscar);

        btnBTrabAvalRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    eventoDataBase();
                    lstTrabResumo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            usuarioSelecionado = (Trabalho)parent.getItemAtPosition(position);

                            Bundle parametro = new Bundle();

                            parametro.putString("siglaNo",       usuarioSelecionado.getSiglaNo());
                            parametro.putString("titulo",     usuarioSelecionado.getTitulo());
                            parametro.putString("notaResumo",    usuarioSelecionado.getNotaApres());

                            Intent it = new Intent(Procurar_Aval_Resumo.this, AvaliarResumoTrabalho.class);
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
                        lstTrabalhos.clear();

                        for(DataSnapshot objSnapShot:dataSnapshot.getChildren()){
                            Trabalho trabalho = objSnapShot.getValue(Trabalho.class);
                            if(trabalho.getCodigoAvalResumo().equals(edtBCodigoAvalRes.getText().toString())) {
                                lstTrabalhos.add(trabalho);
                            }
                        }

                        arrayAdapterTrabalhoRes = new ArrayAdapter<Trabalho>
                                (Procurar_Aval_Resumo.this,
                                        android.R.layout.simple_list_item_1, lstTrabalhos);
                        lstTrabResumo.setAdapter(arrayAdapterTrabalhoRes);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}