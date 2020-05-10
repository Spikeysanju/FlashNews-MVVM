package www.spikeysanju.flashnews.repository

import www.spikeysanju.flashnews.api.RetrofitInstance
import www.spikeysanju.flashnews.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase){

    // get Breaking News
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    // get Tech News
    suspend fun getTechNews(source:String, pageNumber:Int) =
        RetrofitInstance.api.getTechNews(source, pageNumber)

    // search News
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)



}