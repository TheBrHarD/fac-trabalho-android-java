package com.example.congressounirp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.congressounirp.R;
import com.example.congressounirp.model.DatabaseConnection;
import com.example.congressounirp.model.Trabalho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarTrabalhos extends AppCompatActivity {

    private ListView lstTrabalhos;
    private List<Trabalho> lstUsuario;
    private ArrayAdapter<Trabalho> arrayAdapterUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_apresentacao_trabalho);


        lstTrabalhos = (ListView) findViewById(R.id.lstApresentacaoTrab);
        lstUsuario = new ArrayList<Trabalho>();
        eventoDataBase();

        lstTrabalhos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                        for(DataSnapshot objSnapShot:dataSnapshot.getChildren()){
                            Trabalho traba = objSnapShot.getValue(Trabalho.class);
                            lstUsuario.add(traba);
                        }
                        arrayAdapterUsuario = new ArrayAdapter<Trabalho> (ListarTrabalhos.this, android.R.layout.simple_list_item_1, lstUsuario);
                        lstTrabalhos.setAdapter(arrayAdapterUsuario);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }


}