package com.proyecto.jerbo.agenda2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;

import com.proyecto.jerbo.agenda2.Clases.Proceso;

import java.util.List;

public class ProcesoRepository {

    private ProcesoDao procesoDao;
    private LiveData<List<Proceso>> liveData;

    public ProcesoRepository(Application application){
        ProcesoDatabase db = ProcesoDatabase.getInstace(application);
        procesoDao = db.procesoDao();
        liveData = procesoDao.selectAll();

    }


    public void insert(Proceso proceso) {
        new InsertProcesoAsync(procesoDao).execute(proceso);
    }

    public void update(Proceso proceso) {
        new UpdateProcesoAsync(procesoDao).execute(proceso);
    }

    public void delete(Proceso proceso) {
        new DeleteProcesoAsync(procesoDao).execute(proceso);
    }

    public void deleteAll() {
        new DeleteAllProcesoAsync(procesoDao).execute();
    }

    public LiveData<List<Proceso>> getAll() {
        return liveData;
    }
    private static class InsertProcesoAsync extends AsyncTask<Proceso,Void , Void>{
        private ProcesoDao procesoDao;
        private InsertProcesoAsync(ProcesoDao procesoDao){
            this.procesoDao=procesoDao;
        }

        @Override
        protected Void doInBackground(Proceso... procesos) {
            procesoDao.insert(procesos[0]);
            return null;
        }
    }
    private static class DeleteProcesoAsync extends AsyncTask<Proceso,Void , Void>{
        private ProcesoDao procesoDao;
        private DeleteProcesoAsync(ProcesoDao procesoDao){
            this.procesoDao=procesoDao;
        }

        @Override
        protected Void doInBackground(Proceso... procesos) {
            procesoDao.delete(procesos[0]);
            return null;
        }
    }
    private static class UpdateProcesoAsync extends AsyncTask<Proceso,Void , Void>{
        private ProcesoDao procesoDao;
        private UpdateProcesoAsync(ProcesoDao procesoDao){
            this.procesoDao=procesoDao;
        }

        @Override
        protected Void doInBackground(Proceso... procesos) {
            procesoDao.update(procesos[0]);
            return null;
        }
    }
    private static class DeleteAllProcesoAsync extends AsyncTask<Void,Void , Void>{
        private ProcesoDao procesoDao;
        private DeleteAllProcesoAsync(ProcesoDao procesoDao){
            this.procesoDao=procesoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            procesoDao.deleteAll();
            return null;
        }
    }

}
