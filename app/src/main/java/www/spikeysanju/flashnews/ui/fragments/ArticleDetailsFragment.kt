package www.spikeysanju.flashnews.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_article_details.*
import www.spikeysanju.flashnews.MainActivity
import www.spikeysanju.flashnews.R
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        val article = args.article

        webView.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                webViewClient = webViewClient
            }
            loadUrl(article.url)

        }


    }
}
