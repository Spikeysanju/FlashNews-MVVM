package www.spikeysanju.flashnews.ui.viewmodels

import  androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import www.spikeysanju.flashnews.model.NewsResponse
import www.spikeysanju.flashnews.repository.NewsRepository
import www.spikeysanju.flashnews.utils.Resource

class NewsViewModel (val newsRepository: NewsRepository) :ViewModel() {


    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    val techNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val techNewsPage = 1



    init {
        getBreakingNews("in")
        getTechNews("techcrunch")
    }

    fun getBreakingNews(countryCode:String) = viewModelScope.launch {

        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }


    fun getTechNews(source:String) = viewModelScope.launch {

        techNews.postValue(Resource.Loading())
        val response = newsRepository.getTechNews(source, techNewsPage)
        techNews.postValue(handleBreakingNewsResponse(response))

    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful){
            response.body()?.let {resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }

}