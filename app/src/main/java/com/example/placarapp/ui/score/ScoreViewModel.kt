package com.example.placarapp.ui.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel: ViewModel() {

    val goalHome = MutableLiveData<Int>()
    val goalAway = MutableLiveData<Int>()

    init {
        restartGame()
    }

    fun restartGame() {
        goalHome.value = 0
        goalAway.value = 0
    }

    fun scoreGoalHomeTeam(){
        scoreGoal(goalHome)
    }

    fun scoreGoalAwayTeam(){
        scoreGoal(goalAway)
    }

    fun scoreGoal(teamScore: MutableLiveData<Int>){
        teamScore.value = teamScore.value?.plus(1)
    }

}