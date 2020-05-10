package www.spikeysanju.flashnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import www.spikeysanju.flashnews.MainActivity
import www.spikeysanju.flashnews.R
import www.spikeysanju.flashnews.adapters.NewsAdapter
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel
import www.spikeysanju.flashnews.utils.Resource
import www.spikeysanju.flashnews.utils.hide
import www.spikeysanju.flashnews.utils.show

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "Top News"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init RV
        setUpRecyclerView()

        // set onClick listener for RV Item
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_articleDetailsFragment,
                bundle
            )
        }



        viewModel = (activity as MainActivity).viewModel


        viewModel.techNews.observe(viewLifecycleOwner, Observer {  response ->

            when(response){

                is Resource.Loading ->{
                    news_progress.show()
                }

                is Resource.Success -> {
                    news_progress.hide()
                    response.data?.let {  newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    news_progress.hide()
                    response.message?.let {message ->
                        Log.d(TAG,"An Error Occured:$message")
                    }
                }

            }

        })

    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        top_news_rv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
