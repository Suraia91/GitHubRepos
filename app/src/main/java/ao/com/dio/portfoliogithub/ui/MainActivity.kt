package ao.com.dio.portfoliogithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import ao.com.dio.portfoliogithub.R
import ao.com.dio.portfoliogithub.core.createDialog
import ao.com.dio.portfoliogithub.core.createProgressDialog
import ao.com.dio.portfoliogithub.core.hideSoftKeyBoard
import ao.com.dio.portfoliogithub.databinding.ActivityMainBinding
import ao.com.dio.portfoliogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RepoListAdapter() }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setUpAdapter()
        setUpViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpAdapter() {
        binding.rvRepos.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> {
                    dialog.show()
                }
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }

            }
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyBoard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange : $newText")
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }
}