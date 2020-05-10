package www.spikeysanju.flashnews.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import www.spikeysanju.flashnews.MainActivity
import www.spikeysanju.flashnews.R
import www.spikeysanju.flashnews.adapters.NewsAdapter
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel
import www.spikeysanju.flashnews.utils.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import www.spikeysanju.flashnews.utils.Resource
import www.spikeysanju.flashnews.utils.hide
import www.spikeysanju.flashnews.utils.show

/**
 * A simple [Fragment] subclass.
 */

class SearchFragment : Fragment(R.layout.fragment_search) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "Search Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        // init RV
        setUpRecyclerView()

        // search news text watcher
        searchNewsETListener()


        // set onClick listener for RV Item
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_articleDetailsFragment,
                bundle
            )
        }



        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Loading -> {
                    search_progress.show()
                }

                is Resource.Success -> {
                    search_progress.hide()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    search_progress.hide()
                    response.message?.let { message ->
                        Log.d(TAG, "An Error Occured:$message")
                    }
                }

            }

        })
    }

    private fun searchNewsETListener() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                // co-routines job
                var job: Job? = null
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_NEWS_TIME_DELAY)
                    s?.let {
                        if (s.toString().isNotEmpty()) {
                            viewModel.searchNews(s.toString())
                        }
                    }
                }

            }

        })
    }


    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        search_news_rv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
