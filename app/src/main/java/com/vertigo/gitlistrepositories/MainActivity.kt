package com.vertigo.gitlistrepositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.vertigo.gitlistrepositories.contract.Navigator
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.databinding.ActivityMainBinding
import com.vertigo.gitlistrepositories.ui.fragments.FragmentListRepositories
import com.vertigo.gitlistrepositories.ui.fragments.FragmentListRepositoryClickListener
import com.vertigo.gitlistrepositories.ui.fragments.FragmentRepositoryDetail

class MainActivity : AppCompatActivity(), Navigator, FragmentListRepositoryClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, FragmentListRepositories())
                .commit()
        }
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun onItemClicked(item: Repository) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, FragmentRepositoryDetail.newInstance(item = item))
            .commit()
    }
}