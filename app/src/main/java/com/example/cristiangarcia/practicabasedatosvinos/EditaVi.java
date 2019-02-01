package com.example.cristiangarcia.practicabasedatosvinos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class EditaVi extends AppCompatActivity {

    DataSourceVi DSV;

    EditText nomVi;
    EditText anadaVi;
    EditText procVi;
    EditText denomVi;
    Spinner tipoVi;
    EditText bodegaVi;
    EditText FechaVi;
    EditText precioVi;
    EditText graduVi;
    EditText comentario;
    EditText v_olor;
    EditText v_gusto;
    EditText v_visual;
    EditText nota;

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
        montaSpinners("");


        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnCrear = findViewById(R.id.btnCrearVino);
        nomVi = findViewById(R.id.editName); //
        anadaVi = findViewById(R.id.editAnada);
        procVi = findViewById(R.id.editProc);
        denomVi = findViewById(R.id.editDenom);
        tipoVi = findViewById(R.id.spTipus); //
        bodegaVi = findViewById(R.id.editBodega);
        FechaVi = findViewById(R.id.editFecha); //
        precioVi = findViewById(R.id.editPrecio);
        graduVi = findViewById(R.id.editGrad);
        comentario = findViewById(R.id.editComent);
        v_gusto = findViewById(R.id.editVgusto);
        v_olor = findViewById(R.id.editVolor);
        v_visual = findViewById(R.id.editVvista);
        nota = findViewById(R.id.editNota);

        String id = extras.getString("IDVINO");

        if(id.equals("")){ //Crear un vino
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
                    vinito.setTipus(tipoVi.getSelectedItem().toString());
                    vinito.setIdBodega(Long.parseLong(bodegaVi.getText().toString()));
                    vinito.setData(FechaVi.getText().toString());
                    vinito.setPreu(Double.parseDouble(precioVi.getText().toString()));
                    vinito.setGraduacio(graduVi.getText().toString());
                    vinito.setComentari(comentario.getText().toString());
                    vinito.setValOlfativa(v_olor.getText().toString());
                    vinito.setValGustativa(v_gusto.getText().toString());
                    vinito.setValVisual(v_visual.getText().toString());
                    DSV.open();
                    Toast.makeText(EditaVi.this, "Vino creado.", Toast.LENGTH_SHORT).show();
                    DSV.createVi(vinito);
                    DSV.close();
                    finish();
                }
            });

        } else{ //Editar un vino.
            Long id_vino = Long.parseLong(id);
            btnCrear.setVisibility(View.GONE);
            btnBorrar.setVisibility(View.VISIBLE);
            btnEditar.setVisibility(View.VISIBLE);
            //Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();
            DSV.open();
            vinito = DSV.getVi(id_vino);
            DSV.close();

            nomVi.setText(vinito.getNomVi());
            anadaVi.setText(vinito.getAnada());
            procVi.setText(vinito.getLloc());
            denomVi.setText(String.valueOf(vinito.getIdDenominacio()));
            FechaVi.setText(vinito.getData());
            bodegaVi.setText(String.valueOf(vinito.getIdBodega()));
            precioVi.setText(String.valueOf(vinito.getPreu()));
            graduVi.setText(vinito.getGraduacio());
            comentario.setText(vinito.getComentari());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vinito.setNomVi(nomVi.getText().toString());
                    vinito.setAnada(anadaVi.getText().toString());
                    vinito.setLloc(procVi.getText().toString());
                    vinito.setIdDenominacio(Long.parseLong(denomVi.getText().toString()));
                    vinito.setTipus(tipoVi.getSelectedItem().toString());
                    vinito.setData(FechaVi.getText().toString());
                    vinito.setIdBodega(Long.parseLong(bodegaVi.getText().toString()));
                    vinito.setPreu(Double.parseDouble(precioVi.getText().toString()));
                    vinito.setGraduacio(graduVi.getText().toString());
                    vinito.setComentari(comentario.getText().toString());
                    DSV.open();
                    DSV.updateVi(vinito);
                    Toast.makeText(EditaVi.this, "Vino editado.", Toast.LENGTH_SHORT).show();
                    DSV.close();
                    finish();
                }
            });
            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DSV.open();
                    Toast.makeText(EditaVi.this, "Vino borrado.", Toast.LENGTH_SHORT).show();
                    DSV.deleteVi(vinito);
                    DSV.close();
                    finish();
                }
            });


        }

    }
    private void montaSpinners(String t) {
        List<String> llista;
        DSV.open();
        llista = DSV.getAllTipus();
        DSV.close();
        Spinner spinner = findViewById(R.id.spTipus);
        // Crear adapter
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, llista);
        // Drop down estil – llista amb radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // assignar adapter
        spinner.setAdapter(dataAdapter);
        if (t!=null && !t.equals("")) {
            selectValue(spinner,t); // Si hi ha un valor assignat posicionar-se
        }
    }
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
