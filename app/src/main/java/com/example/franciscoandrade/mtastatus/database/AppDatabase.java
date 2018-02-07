package com.example.franciscoandrade.mtastatus.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Wayne Kellman on 1/25/18.
 */

@Database(entities = {StationsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StationsDao stationsDao();
}
