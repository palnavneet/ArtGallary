package com.app.exibhitease.presentation.home_Screen

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.app.exibhitease.presentation.data.repository.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CustomImageViewModel : ViewModel(){


    private val _sharedUri = MutableStateFlow<Uri?>(null)
    val selectedArt = _sharedUri.asStateFlow()

    fun selectedUri(uri : Uri?){
        _sharedUri.value = uri
    }






}
