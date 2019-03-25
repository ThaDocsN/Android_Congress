package com.lambdaschool.congressdetails.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails
import com.lambdaschool.congressdetails.repo.DetailCongressPersonRepo

class DetailCongressPersonViewModel : ViewModel() {
    private var liveData: MutableLiveData<CongresspersonDetails>? = null

    fun getDetails(id: String): LiveData<CongresspersonDetails>? {
        if (liveData == null) {
            loadData(id)
        }
        return liveData
    }

    private fun loadData(id: String) {
        liveData = DetailCongressPersonRepo.getDetails(id)
    }
}
