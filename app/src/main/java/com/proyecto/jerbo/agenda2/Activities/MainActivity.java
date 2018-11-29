package com.proyecto.jerbo.agenda2.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Fragments.ContenedorFragment;
import com.proyecto.jerbo.agenda2.Fragments.HonorariosFragment;
import com.proyecto.jerbo.agenda2.Fragments.InicioFragment;
import com.proyecto.jerbo.agenda2.Fragments.ProcesosFragment;
import com.proyecto.jerbo.agenda2.Fragments.ProcessFragment;
import com.proyecto.jerbo.agenda2.Fragments.TramiteFragment;
import com.proyecto.jerbo.agenda2.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ProcesosFragment.OnFragmentInteractionListener,
        TramiteFragment.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener,
        HonorariosFragment.OnFragmentInteractionListener,

        ProcessFragment.OnFragmentInteractionListener,
        InicioFragment.OnFragmentInteractionListener {


    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, new InicioFragment(), "xd").commit();
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("desea salir =?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        drawer = findViewById(R.id.drawer_layout);
        int id = item.getItemId();
        Fragment miFrag = null;
        boolean FragSel = false;
        switch (id) {
            case R.id.drawer_inicio:
                drawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.drawer_clientes:
                Toast.makeText(this, "falta eso mas :'(", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.drawer_procesos:
                startActivity(new Intent(this, MostrarProcesosActivity.class));
                return true;

            case R.id.drawer_agenda:
                Toast.makeText(this, " por implemnentar xd", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.drawer_financiero:
                Toast.makeText(this, "por imp,enytar 2 xddddd", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.drawer_export:
                Toast.makeText(this, "alto ahi xd", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.drawer_backup:
                Toast.makeText(this, "pecador", Toast.LENGTH_SHORT).show();
                return true;

        }


        if (FragSel == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, miFrag).addToBackStack("drawer").commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPause() {
        //if (dialog != null) { dialog.dismiss(); dialog = null; }
        super.onPause();
    }
}
