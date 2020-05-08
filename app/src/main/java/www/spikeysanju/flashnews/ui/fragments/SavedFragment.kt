package www.spikeysanju.flashnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import www.spikeysanju.flashnews.MainActivity

import www.spikeysanju.flashnews.R
import www.spikeysanju.flashnews.ui.viewmodels.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

}
