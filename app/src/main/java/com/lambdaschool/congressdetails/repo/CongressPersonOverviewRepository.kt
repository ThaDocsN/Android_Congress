package com.lambdaschool.congressdetails.repo

import android.arch.lifecycle.MutableLiveData

import com.lambdaschool.congressdataapiaccess.CongressDao
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview

import java.util.ArrayList

object CongressPersonOverviewRepository {

    val overList: MutableLiveData<ArrayList<CongresspersonOverview>>
        get() {
            val liveData = MutableLiveData()
            val rawData = CongressDao.getAllMembers()
            liveData.setValue(rawData)
            return liveData
        }
}
