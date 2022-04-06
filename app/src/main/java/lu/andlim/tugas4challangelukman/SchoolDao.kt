package lu.andlim.tugas4challangelukman

import androidx.room.*

@Dao
interface SchoolDao {
    @Query("SELECT * FROM School")
    fun getAllSchool() : List<School>

    @Insert()
    fun insertSchool(school: School): Long

//    @Update
//    fun updateSchool() : Int
//
//    @Delete
//    fun deleteSchool() : Int
}