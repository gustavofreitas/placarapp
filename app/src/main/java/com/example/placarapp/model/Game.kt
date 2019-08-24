package com.example.placarapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
    val eventName: String,
    val homeTeamName: String,
    val awayTeamName: String) : Parcelable