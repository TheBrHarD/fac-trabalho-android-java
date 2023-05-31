package com.example.congressounirp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.congressounirp.view.CadastrarAvaliador;
import com.example.congressounirp.view.CadastrarTrabalho;
import com.example.congressounirp.view.ListarTrabalhos;
import com.example.congressounirp.view.Procurar_Aval_Apresentacao;
import com.example.congressounirp.view.Procurar_Aval_Resumo;

public class MainActivity extends AppCompatActivity {

    /*

    Prova em conjunto com o Bruno Jesus Batista

     */

    Button btnCadastrarAvaliador;
    Button btnCadastrarTrabalho;
    Button btnListarTrabalho;
    Button btnAvaliarResumoTrabalho;
    Button btnAvaliarApresTrabalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarAvaliador = (Button) findViewById(R.id.btnCadastrarAval);
        btnCadastrarTrabalho = (Button) findViewById(R.id.btnCadastrarTrab);
        btnListarTrabalho = (Button) findViewById(R.id.btnListarTrab);
        btnAvaliarResumoTrabalho = (Button) findViewById(R.id.btnAvaliarResumoTrab);
        btnAvaliarApresTrabalho = (Button) findViewById(R.id.btnAvaliarApresTrab);

        btnCadastrarAvaliador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CadastrarAvaliador.class);
                startActivity(i);
            }
        });

        btnCadastrarTrabalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CadastrarTrabalho.class);
                startActivity(i);
            }
        });

        btnListarTrabalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListarTrabalhos.class);
                startActivity(i);
            }
        });

        btnAvaliarApresTrabalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Procurar_Aval_Apresentacao.class);
                startActivity(i);
            }
        });


        btnAvaliarResumoTrabalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Procurar_Aval_Resumo.class);
                startActivity(i);
            }
        });
    }

}