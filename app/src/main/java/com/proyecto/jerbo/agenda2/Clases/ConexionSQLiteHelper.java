package com.proyecto.jerbo.agenda2.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.proyecto.jerbo.agenda2.Clases.Utils;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA = "CREATE TABLE " + Utils.TABLE_NAME + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, cliente TEXT, expediente TEXT, juzgado TEXT, especialista TEXT)";
    final String CREAR_TABLA_COMPROMISOS = "CREATE TABLE " + Utils.TABLE_COMPROMISO_NAME + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, fecha TEXT, hora TEXT, tipo TEXT, comentario TEXT,persona TEXT , proceso_id INTEGER, recordatorio BOOLEAN NOT NULL CHECK (recordatorio IN (0,1)) DEFAULT 0, recordatorio_text TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
        db.execSQL(CREAR_TABLA_COMPROMISOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS procesos");
        onCreate(db);
    }

}
