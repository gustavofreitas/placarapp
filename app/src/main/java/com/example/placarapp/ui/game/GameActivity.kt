package com.example.placarapp.ui.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placarapp.R
import com.example.placarapp.model.Game
import com.example.placarapp.ui.game.awayteam.AwayTeamFragment
import com.example.placarapp.ui.game.event.EventFragment
import com.example.placarapp.ui.game.hometeam.HomeTeamFragment
import com.example.placarapp.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.ivBack

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel

    private val mMessageReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context, gameIntent: Intent) {

            if (gameIntent.hasExtra("event_name")) {
                viewModel.eventName = gameIntent.getStringExtra("event_name") ?: ""
                showHomeTeamFragment()
            }
            if (gameIntent.hasExtra("home_team")) {
                viewModel.homeTeam = gameIntent.getStringExtra("home_team") ?: ""
                showAwayTeamFragment()
            }
            if (gameIntent.hasExtra("away_team")) {
                val game: Game = Game(viewModel.eventName, viewModel.homeTeam, viewModel.awayTeam)

                startActivity(ScoreActivity.getIntent(context, game))
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initViewModel()

        showEventFragment()

        ivBack.setOnClickListener {
            onBackPressed()
        }
        registerBroadCastReceiver()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    private fun registerBroadCastReceiver() {
        IntentFilter("FILTER_EVENT").apply {
            addAction("FILTER_HOME_TEAM")
            addAction("FILTER_AWAY_TEAM")
        }.also{
            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, it)

        }
    }

    private fun showEventFragment(){
        supportFragmentManager.beginTransaction().apply {
            add(R.id.containerGame, EventFragment())
        }.also {
            it.commit()
        }
    }

    private fun showHomeTeamFragment(){
        showNextFragment(HomeTeamFragment())
    }

    private fun showAwayTeamFragment(){
        showNextFragment(AwayTeamFragment())
    }

    private fun showNextFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            replace(R.id.containerGame, fragment)
            addToBackStack(null)
        }.also{
            it.commit()
        }
    }


}
