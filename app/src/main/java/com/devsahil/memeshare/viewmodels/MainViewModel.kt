package com.devsahil.memeshare.viewmodels

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsahil.memeshare.models.RandomMeme
import com.devsahil.memeshare.repository.MemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MemeRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMeme()
        }
    }

    val meme : LiveData<RandomMeme>
    get()= repository.meme

    fun nextMeme(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMeme()
        }
    }

    fun shareMeme( view: View, url: String?){
        val intent = Intent(Intent.ACTION_SEND)

        intent.type= "text/plain"

        intent.putExtra(Intent.EXTRA_TEXT, "$url")
        val chooser = Intent.createChooser(intent,"Share this meme with")

        view.context.startActivity(chooser)
    }


}