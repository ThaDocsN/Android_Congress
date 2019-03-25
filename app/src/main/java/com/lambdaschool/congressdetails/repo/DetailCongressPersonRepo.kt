package com.lambdaschool.congressdetails.repo

import android.arch.lifecycle.MutableLiveData

import com.lambdaschool.congressdataapiaccess.CongressDao
import com.lambdaschool.congressdataapiaccess.CongresspersonDetails

object DetailCongressPersonRepo {

    fun getDetails(id: String): MutableLiveData<CongresspersonDetails> {
        val liveData = MutableLiveData()
        val profile = CongressDao.getMemberDetails(id)
        liveData.setValue(profile)
        return liveData
    }
}
