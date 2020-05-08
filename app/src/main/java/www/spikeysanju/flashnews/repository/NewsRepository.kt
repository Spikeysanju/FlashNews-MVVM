package www.spikeysanju.flashnews.repository

import retrofit2.Retrofit
import www.spikeysanju.flashnews.api.RetrofitInstance
import www.spikeysanju.flashnews.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase){

    // get Breaking News
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getTechNews(source:String, pageNumber:Int) =
        RetrofitInstance.api.getTechNews(source, pageNumber)


}