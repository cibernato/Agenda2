package com.proyecto.jerbo.agenda2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.proyecto.jerbo.agenda2.Clases.Proceso;

import java.util.List;

public class ProcesoViewModel extends AndroidViewModel {
    private ProcesoRepository  repository;
    private LiveData<List<Proceso>> all;


    public ProcesoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProcesoRepository(application);
        all = repository.getAll();
    }

    public LiveData<List<Proceso>> getAll() {
        return all;
    }
    public void insert(Proceso proceso){
        repository.insert(proceso);
    }
    public void  update(Proceso proceso){
        repository.update(proceso);
    }
    public void delete(Proceso proceso){
        repository.delete(proceso);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

}
