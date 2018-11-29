package com.proyecto.jerbo.agenda2.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.R;

public class AddProcesoActivity extends AppCompatActivity {
    TextInputEditText cliente, expediente, juzgado, especialista;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proceso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        setTitle("Añadir Proceso");

        cliente = findViewById(R.id.cliente_proceso_add);
        expediente = findViewById(R.id.expediente_proceso_add);
        juzgado = findViewById(R.id.juzgado_proceso_add);
        especialista = findViewById(R.id.especialista_proceso_add);
        guardar = findViewById(R.id.guardar_proceso);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProceso();
            }
        });
    }

    private void registrarProceso() {
        if (validarDatos()) {
            ConexionSQLiteHelper sqLiteHelper = new ConexionSQLiteHelper(this, Utils.TABLE_NAME, null, 1);
            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utils.CLIENTE_ROW, cliente.getText().toString().trim());
            values.put(Utils.ESPECIALISTA_ROW, especialista.getText().toString().trim());
            values.put(Utils.JUZGADO_ROW, juzgado.getText().toString().trim());
            values.put(Utils.EXPEDIENTE_ROW, expediente.getText().toString().trim());
            Long idResultante = db.insert(Utils.TABLE_NAME, Utils.CLIENTE_ROW, values);
            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
            db.close();
            startActivity(new Intent(getApplicationContext(), MostrarProcesosActivity.class));
        }else{
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarDatos() {
        if (!cliente.getText().toString().trim().isEmpty())
            if (!especialista.getText().toString().trim().isEmpty())
                if (!juzgado.getText().toString().trim().isEmpty())
                    if (!expediente.getText().toString().trim().isEmpty()) return true;
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.proceso_add_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_proceso:
                registrarProceso();
                return true;
            case android.R.id.home:
               onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MostrarProcesosActivity.class));
    }
}
