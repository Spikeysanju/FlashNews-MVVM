package www.spikeysanju.flashnews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import www.spikeysanju.flashnews.model.Article

@Dao
interface ArticleDao {

    // Update or Insert Articles
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long


    // Get All News from DB
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>


    // delete article from DB
    @Delete
    suspend fun deleteArticle(article: Article)

}