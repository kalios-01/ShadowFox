package com.kaliostech.myfitness.repositories

import com.kaliostech.myfitness.data.dao.RunDAO
import com.kaliostech.myfitness.data.entities.Run
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
){
    suspend fun insertRun(run:Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run:Run) = runDAO.deleteRun(run)
    fun getAllRunsSortedByDate()= runDAO.getAllRunsSortedByDate()
    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()
    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()
    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()


    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()
    fun getTotalDistance() = runDAO.getTotalDistance()
    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()


}