package com.campus.mastermeme.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        AvailableMemeEntity::class,
        MemeEntity::class
    ],
    version = 1
)
abstract class MasterMemeDatabase : RoomDatabase() {
    abstract val dao: MemeDao
}