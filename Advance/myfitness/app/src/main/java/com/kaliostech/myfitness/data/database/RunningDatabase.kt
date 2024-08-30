package com.kaliostech.myfitness.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kaliostech.myfitness.data.dao.RunDAO
import com.kaliostech.myfitness.data.entities.Run
import com.kaliostech.myfitness.util.Converters

@Database(
    entities = [Run::class], version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase:RoomDatabase() {
    abstract fun getRunDao():RunDAO
}