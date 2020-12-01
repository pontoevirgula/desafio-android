package com.chsl.desafioconcrete.presentation.view.activities


import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chsl.desafioconcrete.R
import com.chsl.desafioconcrete.core.bases.BaseActivity
import com.chsl.desafioconcrete.core.helper.NetworkChangeReceiver.Companion.networkChangeUnregisterReceiver
import com.chsl.desafioconcrete.core.helper.PaginationScroll
import com.chsl.desafioconcrete.data.entity.repositories.Items
import com.chsl.desafioconcrete.presentation.RepositoryPresentationContract
import com.chsl.desafioconcrete.presentation.presenter.RepositoryPresenter
import com.chsl.desafioconcrete.presentation.view.adapters.RepositoryListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_repositories.*
import kotlinx.android.synthetic.main.layout_error.*

class RepositoriesActivity : BaseActivity<RepositoryPresentationContract.RepositoryListPresenter>(),
    RepositoryPresentationContract.RepositoryListView {

    private val repositoryListAdapter: RepositoryListAdapter by lazy {
        RepositoryListAdapter(repositoryResponse, this) {
            intentActivity(it)
        }
    }

    private lateinit var repositoryResponse: ArrayList<Items>

    private var releasedLoad: Boolean = true
    private var countPages: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        swipeRefresh()
    }

    private fun swipeRefresh() {
        swipe_refresh.setOnRefreshListener {
            presenter!!.fetchRepository()
            countPages = 2
            repositoryListAdapter.clear(repositoryResponse)
            loading()
            swipe_refresh.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        createPresenter()
        initUi()
    }

    override fun createPresenter() = RepositoryPresenter(this)

    private fun initUi() {
        presenter?.fetchRepository()
        repositoryResponse = ArrayList()
        setupAdapter()

    }

    private fun setupAdapter() {
        with(rvRepositories) {
            adapter = repositoryListAdapter
            val layout = LinearLayoutManager(this@RepositoriesActivity)

            addOnScrollListener(object : PaginationScroll(layout) {
                override fun loadMoreItems() {
                    presenter?.fetchRepository(countPages++)
                    includeRepoLoadingBottom.visibility = View.VISIBLE
                    releasedLoad = false
                }

                override fun isLoading(): Boolean {
                    return releasedLoad
                }

                override fun hideMoreItems() {
                    includeRepoLoading.visibility = View.GONE
                }
            })
            layoutManager = layout

        }
    }

    override fun populateRepository(itemList: List<Items>) {
        this.repositoryResponse.addAll(itemList)
        repositoryListAdapter.notifyDataSetChanged()
    }

    override fun success() {
        rvRepositories.visibility = View.VISIBLE
        includeRepoLoading.visibility = View.GONE
        includeRepoError.visibility = View.GONE
        includeRepoLoadingBottom.visibility = View.GONE
        releasedLoad = true
    }

    override fun loading() {
        includeRepoLoading.visibility = View.VISIBLE
        includeRepoLoadingBottom.visibility = View.GONE
        rvRepositories.visibility = View.GONE
    }

    override fun error() {
        includeRepoError.visibility = View.VISIBLE
        includeRepoLoading.visibility = View.GONE
        includeRepoLoadingBottom.visibility = View.GONE
        rvRepositories.visibility = View.GONE

        image_refresh.setOnClickListener {
            ObjectAnimator.ofFloat(image_refresh, View.ROTATION, 0f, 360f).setDuration(300).start()
            presenter?.fetchRepository()
            countPages = 2
            repositoryListAdapter.clear(repositoryResponse)
            repositoryListAdapter.notifyDataSetChanged()
            loading()
        }
    }

    override fun onConnectionChange(isConnected: Boolean) {
        if(!isConnected){
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.no_connection), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun intentActivity(it: Items) {
        val intent = Intent(this, PullsRequestsActivity::class.java)
        intent.putExtra("creator", it.owner.login)
        intent.putExtra("repo", it.name)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkChangeUnregisterReceiver(this)
    }

}
