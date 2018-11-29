package com.proyecto.jerbo.agenda2.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.proyecto.jerbo.agenda2.Adapters.ProcesoAdapter;
import com.proyecto.jerbo.agenda2.Clases.Proceso;
import com.proyecto.jerbo.agenda2.ProcesoViewModel;
import com.proyecto.jerbo.agenda2.R;

import java.util.List;

public class PruebaRoomActivity extends AppCompatActivity {

    private ProcesoViewModel procesoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_room);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ProcesoAdapter procesoAdapter = new ProcesoAdapter();
        recyclerView.setAdapter(procesoAdapter);

        procesoViewModel = ViewModelProviders.of(this).get(ProcesoViewModel.class);
        procesoViewModel.getAll().observe(this, new Observer<List<Proceso>>() {
            @Override
            public void onChanged(@Nullable List<Proceso> procesos) {
                procesoAdapter.setDatos(procesos);
            }
        });
    }
}
