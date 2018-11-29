package com.proyecto.jerbo.agenda2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.proyecto.jerbo.agenda2.Clases.Proceso;

@Database(entities = Proceso.class ,version = 1,exportSchema = false)
public abstract class ProcesoDatabase extends RoomDatabase {

    private static ProcesoDatabase instance ;

    public abstract ProcesoDao procesoDao();

    public static synchronized ProcesoDatabase getInstace(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ProcesoDatabase.class ,"proceso_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(dbCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populate(instance).execute();
        }
    };
    private static class Populate extends AsyncTask<Void,Void,Void>{
        private ProcesoDao dao;

        public Populate(ProcesoDatabase db) {
            dao=db.procesoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Proceso("0","x","s","l,mal"));
            dao.insert(new Proceso("1","x","s","l,mal"));
            dao.insert(new Proceso("2","x","s","l,mal"));
            return null;
        }
    }
}
