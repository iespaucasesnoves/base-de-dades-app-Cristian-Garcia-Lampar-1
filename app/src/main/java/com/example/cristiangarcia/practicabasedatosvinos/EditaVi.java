package com.example.cristiangarcia.practicabasedatosvinos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class EditaVi extends AppCompatActivity {

    DataSourceVi DSV;

    EditText nomVi;
    EditText anadaVi;
    EditText procVi;
    EditText denomVi;
    EditText tipoVi;
    EditText bodegaVi;
    EditText FechaVi;
    EditText precioVi;
    EditText graduVi;
    EditText Comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_vi);
        Bundle extras = this.getIntent().getExtras();
        DSV = new DataSourceVi(this);
        nomVi = findViewById(R.id.editName);
        anadaVi = findViewById(R.id.editAnada);
        procVi = findViewById(R.id.editProc);
        denomVi = findViewById(R.id.editDenom);
        tipoVi = findViewById(R.id.editTipo);
        FechaVi = findViewById(R.id.editFecha);
        precioVi = findViewById(R.id.editPrecio);
        graduVi = findViewById(R.id.editGrad);
        Comentario = findViewById(R.id.editComent);

        String id = extras.getString("IDVINO");
        //Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();
        Vi vinito = DSV.getVi(Long.parseLong(id));

        //nomVi.setText(vinito.getNomVi());
    }
}
