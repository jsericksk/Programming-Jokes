package com.kproject.programmingjokes.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kproject.programmingjokes.R
import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.commom.ViewState
import com.kproject.programmingjokes.databinding.FragmentHomeBinding
import com.kproject.programmingjokes.presentation.JokeListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val KEY_FAVORITE_LIST_CHANGED = "key_favorite_list_changed"
const val KEY_BUNDLE_FAVORITE_LIST_CHANGED = "key_bundle_favorite_list_changed"

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var adapter: JokeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        createOptionsMenu()
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbToolbar.tbMainToolbar.setupWithNavController(navController, appBarConfiguration)

        adapter = JokeListAdapter(onFavoriteClickListener = { joke, position ->
            homeViewModel.addOrDeleteFavorite(joke, position)
        })
        binding.rvJokeList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataState()
        observeFragmentResult()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun createOptionsMenu() {
        binding.tbToolbar.tbMainToolbar.inflateMenu(R.menu.menu_main)
        binding.tbToolbar.tbMainToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_reload -> {
                    homeViewModel.getRandomJokes()
                    true
                }
                R.id.menu_favorites -> {
                    findNavController().navigate(R.id.navigateToFavoritesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun observeDataState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.dataStateResult.collect { dataState ->
                    when (dataState) {
                        is DataState.Loading -> {
                            setContentVisibility(ViewState.Loading)
                        }
                        is DataState.Success -> {
                            val jokeList = dataState.data
                            adapter.submitList(jokeList)
                            setContentVisibility(ViewState.Success)
                        }
                        is DataState.Error -> {
                            binding.tvErrorMessage.text = dataState.errorMessage
                            setContentVisibility(ViewState.Error)
                        }
                    }
                }
            }
        }
    }

    /**
     * Observa o resultado de {FavoritesFragment} para atualizar a lista de piadas caso a lista de
     * favoritos tenha mudado (algum favorito tenha sido removido).
     */
    private fun observeFragmentResult() {
        setFragmentResultListener(KEY_FAVORITE_LIST_CHANGED) { requestKey, bundle ->
            val favoriteListChanged = bundle.getBoolean(KEY_BUNDLE_FAVORITE_LIST_CHANGED)
            if (favoriteListChanged) {
                homeViewModel.updateJokeList()
            }
        }
    }

    private fun setContentVisibility(viewState: ViewState) {
        with (binding) {
            when (viewState) {
                ViewState.Loading -> {
                    pbLoading.visibility = View.VISIBLE
                    rvJokeList.visibility = View.GONE
                    llError.visibility = View.GONE
                }
                ViewState.Success -> {
                    pbLoading.visibility = View.GONE
                    rvJokeList.visibility = View.VISIBLE
                    llError.visibility = View.GONE
                }
                ViewState.Error -> {
                    pbLoading.visibility = View.GONE
                    rvJokeList.visibility = View.GONE
                    llError.visibility = View.VISIBLE
                }
            }
        }
    }
}