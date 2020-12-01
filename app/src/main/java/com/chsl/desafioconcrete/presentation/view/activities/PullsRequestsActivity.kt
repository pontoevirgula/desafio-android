package com.chsl.desafioconcrete.presentation.view.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.chsl.desafioconcrete.R
import com.chsl.desafioconcrete.core.bases.BaseActivity
import com.chsl.desafioconcrete.core.helper.NetworkChangeReceiver.Companion.networkChangeUnregisterReceiver
import com.chsl.desafioconcrete.data.entity.pullrequests.PullsRequestsResponse
import com.chsl.desafioconcrete.presentation.PullsRequestsPresentationContract
import com.chsl.desafioconcrete.presentation.presenter.PullsRequestsPresenter
import com.chsl.desafioconcrete.presentation.view.adapters.PullsRequestsAdapter
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.pulls_activity.*


class PullsRequestsActivity : BaseActivity<PullsRequestsPresentationContract.PullsRequestsPresenter>(),
    PullsRequestsPresentationContract.PullsRequestsListView {

    private val pullsRequestsAdapter: PullsRequestsAdapter by lazy {
        PullsRequestsAdapter(pullsResponse, this) {
            intentActivity(it)
        }
    }

    private lateinit var pullsResponse: ArrayList<PullsRequestsResponse>
    private var creator: String = ""
    private var repo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pulls_activity)
        title = getString(R.string.pulls_list_activity)
        creator = intent.getStringExtra("creator").toString()
        repo = intent.getStringExtra("repo").toString()

    }

    override fun onStart() {
        super.onStart()
        createPresenter()
        initUi()
    }

    override fun createPresenter() = PullsRequestsPresenter(this)

    private fun initUi() {
        presenter?.fetchPullsRequests(creator, repo)
        pullsResponse = ArrayList()

        with(rvPullRequests) {
            adapter = pullsRequestsAdapter
            layoutManager = LinearLayoutManager(this@PullsRequestsActivity)
        }

    }

    override fun populatePullsRequests(itemList: List<PullsRequestsResponse>) {
        this.pullsResponse.addAll(itemList)
        pullsRequestsAdapter.notifyDataSetChanged()
    }

    override fun success() {
        rvPullRequests.visibility = View.VISIBLE
        includePullsLoading.visibility = View.GONE
        includePullsError.visibility = View.GONE
    }

    override fun loading() {
        includePullsLoading.visibility = View.VISIBLE
        rvPullRequests.visibility = View.GONE
    }

    override fun error() {
        includePullsError.visibility = View.VISIBLE
        includePullsLoading.visibility = View.GONE
        rvPullRequests.visibility = View.GONE

        image_refresh.setOnClickListener {
            ObjectAnimator.ofFloat(image_refresh, View.ROTATION, 0f, 360f).setDuration(300).start()
            presenter?.fetchPullsRequests(creator, repo)
            pullsRequestsAdapter.clear(pullsResponse)
            pullsRequestsAdapter.notifyDataSetChanged()
            loading()
        }
    }

    override fun onConnectionChange(isConnected: Boolean) {
        if(!isConnected){
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.no_connection), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun intentActivity(it: PullsRequestsResponse) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(it.htmlUrl)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, RepositoriesActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkChangeUnregisterReceiver(this)
    }
}
