package com.proyecto.jerbo.agenda2.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Clases.Compromiso;
import com.proyecto.jerbo.agenda2.Fragments.CompromisoFragment;
import com.proyecto.jerbo.agenda2.R;

public class MostrarCompromisos extends AppCompatActivity implements CompromisoFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        setContentView(R.layout.activity_mostrar_compromisos);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_mostrar_compromisos,new CompromisoFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        (this).getSupportActionBar().setTitle("Agenda 2.0");
        (this).getSupportActionBar().setSubtitle("Compromisos");
        getMenuInflater().inflate(R.menu.compromiso_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.compromiso_añadir){
            startActivity(new Intent(this,AddCompromisoActivity.class));
            Toast.makeText(this, "kappa", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Compromiso item) {

    }
}
