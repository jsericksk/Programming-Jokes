package com.kproject.programmingjokes.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kproject.programmingjokes.databinding.FragmentFavoritesBinding
import com.kproject.programmingjokes.presentation.JokeListAdapter
import com.kproject.programmingjokes.presentation.home.KEY_BUNDLE_FAVORITE_LIST_CHANGED
import com.kproject.programmingjokes.presentation.home.KEY_FAVORITE_LIST_CHANGED
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private lateinit var adapter: JokeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbToolbar.tbMainToolbar.setupWithNavController(navController, appBarConfiguration)

        adapter = JokeListAdapter(onFavoriteClickListener = { joke, position ->
            favoritesViewModel.deleteFavorite(joke)
            // Informa que a lista de favoritos mudou
            setFragmentResult(
                KEY_FAVORITE_LIST_CHANGED,
                bundleOf(KEY_BUNDLE_FAVORITE_LIST_CHANGED to true)
            )
        })
        binding.rvFavoriteList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavoriteList()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeFavoriteList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoritesViewModel.favoriteList.collect { favoriteList ->
                    adapter.submitList(favoriteList)
                    if (favoriteList.isEmpty()) {
                        binding.llEmptyList.visibility = View.VISIBLE
                        binding.rvFavoriteList.visibility = View.GONE
                    } else {
                        binding.llEmptyList.visibility = View.GONE
                        binding.rvFavoriteList.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}