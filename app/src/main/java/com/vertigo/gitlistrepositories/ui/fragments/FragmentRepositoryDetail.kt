package com.vertigo.gitlistrepositories.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vertigo.gitlistrepositories.R
import com.vertigo.gitlistrepositories.contract.navigator
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.databinding.FragmentRepositoryDetailBinding
import com.vertigo.gitlistrepositories.ui.adapters.ShaAdapter
import com.vertigo.gitlistrepositories.utils.DateParser
import kotlinx.android.synthetic.main.fragment_list_repositories.*
import java.lang.IllegalArgumentException

class FragmentRepositoryDetail: Fragment(), ConnectionListener {
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository

    private val commitViewModel by viewModels<CommitViewModel>()
    private lateinit var adapter: ShaAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        repository = arguments?.getParcelable(ITEM) ?:
                throw IllegalArgumentException("Error in FragmentRepositoryDetail")

        commitViewModel.initCommit(repository.commitUrl)

        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = ShaAdapter()
        layoutManager = LinearLayoutManager(requireContext())

        initViews()

        return view
    }

    private fun initViews() {
        with(binding) {
            setProgressBar(true)
            loadAvatar(repository.avatarUrl)
            fullNameTextview.text = repository.fullName
            loginTextview.text = repository.login

            commitShaRecycler.layoutManager = layoutManager
            commitShaRecycler.adapter = adapter

            // Как только пришел ответ по коммиту, то прописываем данные + сетаем список Sha
            commitViewModel.commit.observe(viewLifecycleOwner, {
                setProgressBar(false)
                commitMessageTextview.text = it.commitMessage
                commitAuthorTextview.text = it.commitAuthor

                commitDateTextview.text = DateParser().getTrueDateFormat(it.commitDate)

                // сетаем список и обновляем адаптер
                adapter.shas = it.commitSha
                adapter.notifyDataSetChanged()
            })

            backButton.setOnClickListener {
                navigator().goBack()
            }
        }
    }

    override fun sendConnectionToast() {
        Toast.makeText(requireContext(), "Huston, Commit have problem!", Toast.LENGTH_LONG).show()
    }

    private fun loadAvatar(url: String) {
        with(binding) {
            Glide.with(binding.root)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_user_photo_error)
                .fitCenter()
                .into(avatarImageview)
        }
    }

    private fun setProgressBar(loader: Boolean) {
        with(binding) {
            if (loader) detailProgressbar.visibility = View.VISIBLE
            else detailProgressbar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ITEM = "ITEM"

        fun newInstance(item: Repository): FragmentRepositoryDetail {
            val args = Bundle()
            args.putParcelable(ITEM, item)
            val fragment = FragmentRepositoryDetail()
            fragment.arguments = args
            return fragment
        }
    }
}