package lu.andlim.tugas4challangelukman

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [School::class], version = 1)
abstract class SchoolDatabase : RoomDatabase(){
    abstract fun schoolDao() : SchoolDao

    companion object{
        private var INSTANCE : SchoolDatabase? = null
        fun getInstance(context: Context): SchoolDatabase? {
            if (INSTANCE == null){
                synchronized(SchoolDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    SchoolDatabase::class.java, "School.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}