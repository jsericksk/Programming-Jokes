package com.kproject.programmingjokes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kproject.programmingjokes.R
import com.kproject.programmingjokes.databinding.RecyclerviewItemJokeBinding
import com.kproject.programmingjokes.presentation.model.JokeUiState

class JokeListAdapter(
    private val onFavoriteClickListener: (joke: JokeUiState, position: Int) -> Unit
) : ListAdapter<JokeUiState, JokeListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemJokeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(
        private val binding: RecyclerviewItemJokeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val joke = getItem(position)
            with (binding) {
                tvSetup.text = joke.setup
                tvPunchline.text = joke.punchline
                ibFavorite.setBackgroundResource(favoriteIcon(joke.isFavorite))
                ibFavorite.setOnClickListener {
                    ibFavorite.setBackgroundResource(favoriteIcon(!joke.isFavorite))
                    onFavoriteClickListener.invoke(joke, position)
                }
            }
        }
    }

    private fun favoriteIcon(isFavorite: Boolean): Int {
        if (isFavorite) {
            return R.drawable.ic_favorite
        }
        return R.drawable.ic_favorite_border
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<JokeUiState>() {
        override fun areItemsTheSame(oldItem: JokeUiState, newItem: JokeUiState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JokeUiState, newItem: JokeUiState): Boolean {
            return oldItem == newItem
        }
    }
}