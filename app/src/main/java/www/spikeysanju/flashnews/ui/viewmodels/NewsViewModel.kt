package www.spikeysanju.flashnews.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import www.spikeysanju.flashnews.model.Article
import www.spikeysanju.flashnews.model.NewsResponse
import www.spikeysanju.flashnews.repository.NewsRepository
import www.spikeysanju.flashnews.utils.Resource

class NewsViewModel (val newsRepository: NewsRepository) :ViewModel() {


    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null


    val techNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var techNewsPage = 1
    var techNewsResponse: NewsResponse? = null


    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null



    init {
        getBreakingNews("in")
        getTechNews("techcrunch")
    }


    // get Breaking News
    fun getBreakingNews(countryCode:String) = viewModelScope.launch {

        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }


    // get Tech News
    fun getTechNews(source:String) = viewModelScope.launch {

        techNews.postValue(Resource.Loading())
        val response = newsRepository.getTechNews(source, techNewsPage)
        techNews.postValue(handleTechNewsResponse(response))

    }

    // search for news
    fun searchNews(searchQuery: String) = viewModelScope.launch {

        searchNews.postValue(Resource.Loading())

        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    // Handle Breaking News
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    // Handle tech News
    private fun handleTechNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                techNewsPage++
                if (techNewsResponse == null) {
                    techNewsResponse = resultResponse
                } else {
                    val oldArticles = techNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(techNewsResponse ?: resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }


    // Handle Search News Response
    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchNewsPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // save article
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    // get saved news
    fun getSavedArticles() = newsRepository.getSavedNews()


    // delete article
    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


}