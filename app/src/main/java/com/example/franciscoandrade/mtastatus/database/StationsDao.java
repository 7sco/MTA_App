package com.example.franciscoandrade.mtastatus.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Wayne Kellman on 2/6/18.
 */
@Dao
public interface StationsDao {

    @Query("SELECT * FROM stationsentity")
    List<StationsEntity> getALL();

    @Query("SELECT * FROM stationsentity WHERE id IN (:stationID)")
    List<StationsEntity> loadALLByIds(int[] stationID);

    @Query("SELECT * FROM stationsentity WHERE stationName LIKE :stationName LIMIT 1")
    StationsEntity findByName(String stationName);

    @Query("SELECT * FROM stationsentity WHERE stationID LIKE :stationID LIMIT 1")
    StationsEntity findByID(int stationID);

    @Insert
    void insertAll(StationsEntity... stationsEntities);

    @Update
    void updatStaions(StationsEntity... stationsEntities);

    @Delete
    void delete(StationsEntity stationsEntity);
}
