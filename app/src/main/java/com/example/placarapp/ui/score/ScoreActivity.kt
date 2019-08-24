package com.example.placarapp.ui.score

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.placarapp.R
import com.example.placarapp.model.Game
import kotlinx.android.synthetic.main.activity_score.btFinishGame
import kotlinx.android.synthetic.main.activity_score.btGoalAway
import kotlinx.android.synthetic.main.activity_score.btGoalHome
import kotlinx.android.synthetic.main.activity_score.btRestart
import kotlinx.android.synthetic.main.activity_score.tvAwayGoal
import kotlinx.android.synthetic.main.activity_score.tvAwayTeamName
import kotlinx.android.synthetic.main.activity_score.tvEventName
import kotlinx.android.synthetic.main.activity_score.tvHomeGoal
import kotlinx.android.synthetic.main.activity_score.tvHomeTeamName

class ScoreActivity : AppCompatActivity() {

    private lateinit var viewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        setExtras()

        initViewModel()
        initObservers()
        initView()

    }

    private fun initView() {
        btGoalHome.setOnClickListener {
            viewModel.scoreGoalHomeTeam()
        }
        btGoalAway.setOnClickListener {
            viewModel.scoreGoalAwayTeam()
        }
        btRestart.setOnClickListener { viewModel.restartGame() }
        btFinishGame.setOnClickListener { finish() }
    }

    private fun initObservers(){
        viewModel.goalHome.observe(this, Observer{
            tvHomeGoal.text = it.toString()
        })
        viewModel.goalAway.observe(this, Observer {
            tvAwayGoal.text = it.toString()
        })
    }

    private fun setExtras(){

        intent?.extras?.getParcelable<Game>(EXTRA_DEFAULT_KEY)?.apply {
                tvEventName.text = eventName
                tvHomeTeamName.text = homeTeamName
                tvAwayTeamName.text = awayTeamName
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)
    }

    companion object {
        private const val EXTRA_DEFAULT_KEY = "game"

        fun getIntent(context: Context, game: Game): Intent {
            return Intent(context, ScoreActivity::class.java).apply {
                putExtra(EXTRA_DEFAULT_KEY, game)
            }
        }
    }


}
