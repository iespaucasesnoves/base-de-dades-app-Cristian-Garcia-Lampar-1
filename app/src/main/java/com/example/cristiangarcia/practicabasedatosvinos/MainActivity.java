package com.example.cristiangarcia.practicabasedatosvinos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private static ListView lv;

    @Override
    public void onResume() {
        super.onResume();
        mostraVins();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lista);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // En fer onClick a un element de la llista cridam l'activity d'edició i passam la clau primària
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idvinito = findViewById(R.id.ViID);
                String s = idvinito.getText().toString();
                Intent in = new Intent(MainActivity.this, EditaVi.class);
                in.putExtra("IDVINO", s);
                startActivity(in);
            }
        });
        Button btNou = findViewById(R.id.btnCrear);
        btNou.setOnClickListener(
                // Cridam l'activity d'edició indicant que es un insert (clau primària en blanc per exemple)
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getApplicationContext(), EditaVi.class);
                        in.putExtra("IDVINO", "");
                        startActivity(in);
                    }
                }
        );
        mostraVins(); // Carrega la llista
    }

    public void mostraVins() {
        // Obrim la base de dades
        DataSourceVi bd;
        bd = new DataSourceVi(this);
        bd.open();
        // Obtenim tots els vins
        List<Vi> llistaVins = bd.getAllVi();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < llistaVins.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            Vi vi = llistaVins.get(i);
            map.put("id", String.valueOf(vi.getId()));
            map.put("nomVi", vi.getNomVi());
            map.put("data", vi.getData());
            map.put("tipus", vi.getTipus());
            map.put("preu", String.valueOf(vi.getPreu()));
            map.put("nota", String.valueOf(vi.getNota()));
            map.put("comentario", vi.getComentari());
            llista.add(map);
        }

        //Tanquem la BD
        bd.close();
        //Assignar a la listview
        adapter = new SimpleAdapter(this, llista, R.layout.llistavins,
                new String[]{"id", "nomVi", "data", "tipus", "preu", "nota", "comentario"},
                new int[]{R.id.ViID, R.id.viName, R.id.viFecha, R.id.viTipo, R.id.viPreu, R.id.viNota, R.id.viComentario});
        lv.setAdapter(adapter);
    }
}
