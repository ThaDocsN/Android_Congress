package com.lambdaschool.congressdetails.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview
import com.lambdaschool.congressdetails.repo.CongressPersonOverviewRepository

import java.util.ArrayList

class CongressOverviewViewModel : ViewModel() {

    private var overviewList: MutableLiveData<ArrayList<CongresspersonOverview>>? = null

    val overView: MutableLiveData<ArrayList<CongresspersonOverview>>?
        get() {
            if (overviewList == null) {
                load()
            }
            return overviewList
        }

    private fun load() {
        overviewList = CongressPersonOverviewRepository.overList
    }
}
