package com.example.league.domain.repository

import com.example.league.domain.models.Champion

interface LeagueRepository
{
    suspend fun getChampionsList(): List<Champion>
}