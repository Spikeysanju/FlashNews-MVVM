package www.spikeysanju.flashnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import www.spikeysanju.flashnews.db.ArticleDatabase
import www.spikeysanju.flashnews.repository.NewsRepository
import www.spikeysanju.flashnews.ui.NewsViewModelProviderFactory
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init viewModelProvider
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        // init bottom navigation
        bottom_navigation.setupWithNavController(nav_host_fragment.findNavController())

    }
}
