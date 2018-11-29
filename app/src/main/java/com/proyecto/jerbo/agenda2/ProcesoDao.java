package com.proyecto.jerbo.agenda2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.proyecto.jerbo.agenda2.Clases.Proceso;

import java.util.List;

@Dao
public interface ProcesoDao {
    @Insert
    void insert(Proceso proceso);

    @Update
    void update(Proceso proceso);

    @Delete
    void delete(Proceso proceso);

    @Query("DELETE FROM Proceso")
    void deleteAll();

    @Query("SELECT * FROM Proceso ORDER BY cliente DESC")
    LiveData<List<Proceso>> selectAll();
}
