package ru.otus.animations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationViewModel:ViewModel() {
    val data:MutableLiveData<Any> by lazy {
        MutableLiveData<Any>()
    }
}