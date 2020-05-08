package www.spikeysanju.flashnews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import www.spikeysanju.flashnews.model.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase()  {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        // Check for DB instance if not null then get or insert or else create new DB Instance
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){

            instance?: createDatabase(context).also{ instance = it }

        }

        // create db instance
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()


    }

}