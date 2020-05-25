package www.spikeysanju.flashnews.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import www.spikeysanju.flashnews.repository.NewsRepository
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

class NewsViewModelProviderFactory(val app: Application, val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}