package com.proyecto.jerbo.agenda2.Activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Clases.Proceso;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.R;

public class AddProcesoActivity extends AppCompatActivity {
    TextInputEditText cliente, expediente, juzgado, especialista;
    Intent intent;
    Proceso temp;
    ConexionSQLiteHelper sqLiteHelper;

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

        intent = getIntent();
        temp = (Proceso) intent.getSerializableExtra("pro");
        if (temp != null) {
            cliente.setText(temp.getCliente());
            expediente.setText(temp.getExpediente());
            juzgado.setText(temp.getJuzgado());
            especialista.setText(temp.getEspecialista());
        }
    }

    private void registrarProceso() {
        sqLiteHelper = new ConexionSQLiteHelper(this, Utils.TABLE_NAME, null, 1);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        ContentValues values = llenarValues();
        if (validarDatos() && temp == null) {
            llenarValues();
            Long idResultante = db.insert(Utils.TABLE_NAME, Utils.CLIENTE_ROW, values);
            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MostrarProcesosActivity.class));
        } else {
            values = llenarValues();
            db.update(Utils.TABLE_NAME, values, "id =?", new String[]{String.valueOf(temp.getId())});
            startActivity(new Intent(getApplicationContext(), MostrarProcesosActivity.class));
        }
        db.close();
    }

    private ContentValues llenarValues() {
        ContentValues values = new ContentValues();
        values.put(Utils.CLIENTE_ROW, cliente.getText().toString().trim());
        values.put(Utils.ESPECIALISTA_ROW, especialista.getText().toString().trim());
        values.put(Utils.JUZGADO_ROW, juzgado.getText().toString().trim());
        values.put(Utils.EXPEDIENTE_ROW, expediente.getText().toString().trim());
        return values;
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
            case R.id.delete_proceso:
                if (temp != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("¿Desea eliminar este compromiso?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    eliminarProceso();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else
                    startActivity(new Intent(this, MostrarProcesosActivity.class));
                return true;
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

    private void eliminarProceso() {
        sqLiteHelper = new ConexionSQLiteHelper(this, Utils.TABLE_NAME, null, 1);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        if (temp != null) {
            String[] params = {String.valueOf(temp.getId())};
            try {
                db.delete(Utils.TABLE_NAME, "id =?", params);
            } catch (Exception e) {
                Log.e("db", e.getLocalizedMessage());
            }
            Toast.makeText(this, "Objeto eliminado", Toast.LENGTH_SHORT).show();
        }
        db.close();
        startActivity(new Intent(this, MostrarProcesosActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MostrarProcesosActivity.class));
    }
}
