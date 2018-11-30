package com.proyecto.jerbo.agenda2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.proyecto.jerbo.agenda2.Fragments.ContenedorFragment;
import com.proyecto.jerbo.agenda2.Fragments.ProcesosFragment;
import com.proyecto.jerbo.agenda2.Fragments.ProcessFragment;
import com.proyecto.jerbo.agenda2.R;


public class MostrarProcesosActivity extends AppCompatActivity implements ProcesosFragment.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener,
        ProcessFragment.OnFragmentInteractionListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        setContentView(R.layout.activity_mostrar_procesos);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentMostrarProceso, new ProcesosFragment(), "Mostrar Cont").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.procesos_toolbar,menu);
        (this).getSupportActionBar().setTitle("Procesos");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case  R.id.add_proceso:
                startActivity(new Intent(this, AddProcesoActivity.class));
                return true;
        }
        return true;
    }
}
