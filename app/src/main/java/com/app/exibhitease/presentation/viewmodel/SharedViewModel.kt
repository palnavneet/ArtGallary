package com.app.exibhitease.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.app.exibhitease.presentation.data.repository.Art
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SharedViewModel : ViewModel(){

    private val _sharedArt = MutableStateFlow<Art?>(null)
    val selectedArt = _sharedArt.asStateFlow()

    fun selectedArt(art : Art){
        _sharedArt.value = art
    }




}