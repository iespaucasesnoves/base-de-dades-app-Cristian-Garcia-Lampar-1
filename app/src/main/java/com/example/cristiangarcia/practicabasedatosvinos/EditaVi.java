package com.example.cristiangarcia.practicabasedatosvinos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Button btnEditar;
    Button btnBorrar;
    Button btnCrear;
    Vi vinito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_vi);
        Bundle extras = this.getIntent().getExtras();
        DSV = new DataSourceVi(this);

        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnCrear = findViewById(R.id.btnCrearVino);
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


        if(id.equals("")){
            btnCrear.setVisibility(View.VISIBLE);
            btnBorrar.setVisibility(View.GONE);
            btnEditar.setVisibility(View.GONE);
            vinito = new Vi();
            btnCrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vinito.setNomVi(nomVi.getText().toString());
                    vinito.setAnada(anadaVi.getText().toString());
                    vinito.setLloc(procVi.getText().toString());
                    vinito.setIdDenominacio(Long.parseLong(denomVi.getText().toString()));
                    vinito.setTipus(tipoVi.getText().toString());
                    vinito.setData(FechaVi.getText().toString());
                    vinito.setPreu(Long.parseLong(precioVi.getText().toString()));
                    vinito.setGraduacio(graduVi.getText().toString());
                    vinito.setComentari(Comentario.getText().toString());
                    DSV.open();
                    Toast.makeText(EditaVi.this, "Vino creado.", Toast.LENGTH_SHORT).show();
                    DSV.createVi(vinito);
                    DSV.close();
                }
            });

        } else{ //Editar un vino.
            Long id_vino = Long.parseLong(id);
            btnCrear.setVisibility(View.GONE);
            btnBorrar.setVisibility(View.VISIBLE);
            btnEditar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();
            DSV.open();
            vinito = DSV.getVi(id_vino);
            DSV.close();

            nomVi.setText(vinito.getNomVi());
            anadaVi.setText(vinito.getAnada());
            procVi.setText(vinito.getLloc());
            denomVi.setText(String.valueOf(vinito.getIdDenominacio()));
            tipoVi.setText(vinito.getTipus());
            FechaVi.setText(vinito.getData());
            precioVi.setText(String.valueOf(vinito.getPreu()));
            graduVi.setText(vinito.getGraduacio());
            Comentario.setText(vinito.getComentari());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vinito.setNomVi(nomVi.getText().toString());
                    vinito.setAnada(anadaVi.getText().toString());
                    vinito.setLloc(procVi.getText().toString());
                    vinito.setIdDenominacio(Long.parseLong(denomVi.getText().toString()));
                    vinito.setTipus(tipoVi.getText().toString());
                    vinito.setData(FechaVi.getText().toString());
                    vinito.setPreu(Long.parseLong(precioVi.getText().toString()));
                    vinito.setGraduacio(graduVi.getText().toString());
                    vinito.setComentari(Comentario.getText().toString());
                    DSV.open();
                    DSV.updateVi(vinito);
                    Toast.makeText(EditaVi.this, "Vino editado.", Toast.LENGTH_SHORT).show();
                    DSV.close();
                }
            });
            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DSV.open();
                    Toast.makeText(EditaVi.this, "Vino borrado.", Toast.LENGTH_SHORT).show();
                    DSV.deleteVi(vinito);
                    DSV.close();
                }
            });
        }


    }
}
