package com.vertigo.gitlistrepositories.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.databinding.FragmentListRepositoriesBinding
import com.vertigo.gitlistrepositories.ui.adapters.PaginationAdapter

class FragmentListRepositories: Fragment(), ConnectionListener {
    private var _binding: FragmentListRepositoriesBinding? = null
    private val binding get() = _binding!!


    private var fragmentListRepositoryClickListener: FragmentListRepositoryClickListener? = null

    private val repositoriesViewModel: RepositoriesViewModel by viewModels()
    private lateinit var adapter: PaginationAdapter
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * oldValue - для сохранения позиции на элементе, после которого были загружены новые эелементы.
     */
    private var oldValue = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListRepositoriesBinding.inflate(inflater, container, false)
        val view = binding.root

        repositoriesViewModel.loadContent()

        createRecycler()

        repositoriesViewModel.repositoriesViewModel.observe(viewLifecycleOwner, {
            repositories ->
                if (repositories.size == 0) {
                    sendConnectionToast()
                }
                setRecyclerLogic(repositories)
                oldValue = repositories.size
        })
        return view
    }

    private fun createRecycler() {
        adapter = PaginationAdapter(fragmentListRepositoryClickListener)
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        with(binding) {
            repositoriesRecycler.adapter = adapter
            repositoriesRecycler.layoutManager = layoutManager
            repositoriesRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setRecyclerLogic(repositories: MutableList<Repository>) {
        setProgressBar(true)
        Log.v("App",adapter.currentList.size.toString())

        with(binding) {
            adapter.submitList(repositories)

            repositoriesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (layoutManager.findLastCompletelyVisibleItemPosition() == repositories.size - 1) {
                        repositoriesViewModel.loadNewRepositories()
                        setProgressBar(true)
                    }
                }
            })
            adapter.notifyItemRangeInserted(oldValue, repositories.size)
        }
        setProgressBar(false)
    }

    private fun setProgressBar(loader: Boolean) {
        with(binding) {
            if (loader) progressbarMain.visibility = View.VISIBLE
            else progressbarMain.visibility = View.GONE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListRepositoryClickListener) {
            fragmentListRepositoryClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListRepositoryClickListener = null
        _binding = null
    }

    override fun sendConnectionToast() {
        Toast.makeText(requireContext(), "Huston, Repositories have problem!", Toast.LENGTH_LONG).show()
    }
}