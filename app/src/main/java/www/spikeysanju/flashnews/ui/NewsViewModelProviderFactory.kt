package www.spikeysanju.flashnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import www.spikeysanju.flashnews.repository.NewsRepository
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

class NewsViewModelProviderFactory(val newsRepository: NewsRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}