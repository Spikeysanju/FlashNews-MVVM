package www.spikeysanju.flashnews.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import www.spikeysanju.flashnews.MainActivity
import www.spikeysanju.flashnews.R
import www.spikeysanju.flashnews.adapters.NewsAdapter
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel


        // init RV
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


    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        search_news_rv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
