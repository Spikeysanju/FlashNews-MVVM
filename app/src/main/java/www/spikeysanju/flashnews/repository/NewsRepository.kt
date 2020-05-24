package www.spikeysanju.flashnews.repository

import www.spikeysanju.flashnews.api.RetrofitInstance
import www.spikeysanju.flashnews.db.ArticleDatabase
import www.spikeysanju.flashnews.model.Article

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

    // insert or update article
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    // get saved news
    fun getSavedNews() = db.getArticleDao().getAllArticles()

    // delete article
    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}