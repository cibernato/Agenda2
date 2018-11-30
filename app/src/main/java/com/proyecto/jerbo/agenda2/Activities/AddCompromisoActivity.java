package com.proyecto.jerbo.agenda2.Activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Clases.Compromiso;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.Fragments.DatePickerFragment;
import com.proyecto.jerbo.agenda2.Fragments.TimePickerFragment;
import com.proyecto.jerbo.agenda2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddCompromisoActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener, TimePickerFragment.TimeDialogListener {
    private Compromiso comp;
    private TextView fecha, hora, persona, comentario, recor;
    private String tipo;
    AlertDialog alertDialog1;
    Intent recb;
    ConexionSQLiteHelper conn;
    String[] values = {" 5 min ", " 30 min", " 1 hora", "2 horas "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compromiso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recor = findViewById(R.id.compromiso_recordatorio);
        hora = findViewById(R.id.compromiso_add_hora);
        fecha = findViewById(R.id.comprmiso_add_fecha);
        persona = findViewById(R.id.compromiso_add_persona);
        comentario = findViewById(R.id.compromiso_add_comentario);
        Spinner spn = findViewById(R.id.compromiso_spinner);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        recb = getIntent();
        comp = (Compromiso) recb.getSerializableExtra("comp");
        if (comp != null) {
            fecha.setText(comp.getFecha());
            hora.setText(comp.getHora());
            persona.setText(comp.getPersona());
            comentario.setText(comp.getComentario());
            String[] p = getResources().getStringArray(R.array.opciones);
            for (int i = 0; i < p.length; i++) {
                if (comp.getTipo().equals(p[i])) {
                    spn.setSelection(i);
                }
            }
            if (comp.getRecordatorioText() != null) {
                recor.setText(comp.getRecordatorioText());
            }
        }

    }

    private void registrarDatos() {
        conn = new ConexionSQLiteHelper(this, Utils.TABLE_COMPROMISO_NAME, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values;
        if (validarDatos() && comp == null) {
            values = llenarValues();
            long a = db.insert(Utils.TABLE_COMPROMISO_NAME, Utils.COMPROMISO_FECHA_ROW, values);
            Toast.makeText(this, "id " + a, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MostrarCompromisos.class));
        } else {
            values = llenarValues();
            db.update(Utils.TABLE_COMPROMISO_NAME, values, "id =?", new String[]{String.valueOf(comp.getId())});
            startActivity(new Intent(this, MostrarCompromisos.class));
        }
        db.close();
    }

    private ContentValues llenarValues() {
        ContentValues values = new ContentValues();
        values.put(Utils.COMPROMISO_FECHA_ROW, fecha.getText().toString().trim());
        values.put(Utils.COMPROMISO_HORA_ROW, hora.getText().toString().trim());
        values.put(Utils.COMPROMISO_TIPO_ROW, tipo);
        values.put(Utils.COMPROMISO_COMENTARIO_ROW, comentario.getText().toString().trim());
        values.put(Utils.COMPROMISO_PERSONA_ROW, persona.getText().toString().trim());
        values.put(Utils.COMPROMISO_ID_PROCESO_ROW, 0);
        if (!recor.getText().toString().isEmpty()) {
            values.put(Utils.COMPROMISO_RECORDATORIO_ROW, 1);
            values.put(Utils.COMPROMISO_RECORDATORIO_TEXT_ROW, recor.getText().toString().trim());
        }
        return values;
    }

    private boolean validarDatos() {
        return !(fecha.getText().toString().isEmpty() && hora.getText().toString().isEmpty() && tipo.isEmpty() && comentario.getText().toString().isEmpty() && persona.getText().toString().isEmpty());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        (this).getSupportActionBar().setTitle("Compromiso");
        (this).getSupportActionBar().setSubtitle("Añadir");
        getMenuInflater().inflate(R.menu.compromiso_save_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_compromiso:
                registrarDatos();
                return true;
            case R.id.delete_compromiso:
                //comp = (Compromiso) recb.getSerializableExtra("comp");
                if (comp != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("¿Desea eliminar este compromiso?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    eliminarComprimiso();
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
                }else
                startActivity(new Intent(this, MostrarCompromisos.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void eliminarComprimiso() {
        conn = new ConexionSQLiteHelper(this, Utils.TABLE_COMPROMISO_NAME, null, 1);
        Compromiso a = (Compromiso) recb.getSerializableExtra("comp");
        SQLiteDatabase db = conn.getWritableDatabase();
        if (a != null) {
            String[] params = {String.valueOf(a.getId())};
            try {
                db.delete(Utils.TABLE_COMPROMISO_NAME, "id =?", params);
            } catch (Exception e) {
                Log.e("db", e.getLocalizedMessage());
            }
            Toast.makeText(this, "Objeto eliminado", Toast.LENGTH_SHORT).show();

        }
        db.close();
        startActivity(new Intent(this, MostrarCompromisos.class));
    }

    @Override
    public void onFinishDialog(Date date) {
        Toast.makeText(this, "Selected Date :" + formatDate(date), Toast.LENGTH_SHORT).show();
        fecha.setText(formatDate(date));
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    public void crearDate(View view) {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "dale diualog xd");

    }

    @Override
    public void onFinishDialog(String time) {
        Toast.makeText(this, "Selected Time : " + time, Toast.LENGTH_SHORT).show();
        hora.setText(time);
    }

    public void createHora(View view) {
        TimePickerFragment dialog = new TimePickerFragment();
        dialog.show(getSupportFragmentManager(), "kappa");
    }

    public void createRecordatorio(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione tiempo: ");
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lw = ((AlertDialog) dialog).getListView();
                switch (item) {
                    case 0:
                        recor.setText("Recordatorio progamado " + lw.getAdapter().getItem(lw.getCheckedItemPosition()) + " antes");
                        break;
                    case 1:
                        recor.setText("Recordatorio progamado " + lw.getAdapter().getItem(lw.getCheckedItemPosition()) + " antes");
                        break;
                    case 2:
                        recor.setText("Recordatorio progamado " + lw.getAdapter().getItem(lw.getCheckedItemPosition()) + " antes");
                        break;
                    case 3:
                        recor.setText("Recordatorio progamado " + lw.getAdapter().getItem(lw.getCheckedItemPosition()) + " antes");
                        break;
                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }
}
