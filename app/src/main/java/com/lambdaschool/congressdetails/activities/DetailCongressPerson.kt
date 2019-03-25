package com.lambdaschool.congressdetails.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails
import com.lambdaschool.congressdetails.R
import com.lambdaschool.congressdetails.viewModel.DetailCongressPersonViewModel

class DetailCongressPerson : AppCompatActivity() {

    internal var viewModel: DetailCongressPersonViewModel
    internal var name: TextView
    internal var dateOfBirth: TextView
    internal var gender: TextView

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_congress_person)

        name = findViewById(R.id.textViewName)
        dateOfBirth = findViewById(R.id.textViewDateOfBirth)
        gender = findViewById(R.id.textViewGender)

        val congressPersonId = getIntent().getStringExtra(ListActivity.CONGRESS_PERSON_ID)
        if (congressPersonId != null && congressPersonId !== "") {
            viewModel = ViewModelProviders.of(this).get(DetailCongressPersonViewModel::class.java)
            viewModel.getDetails(congressPersonId)!!.observe(this, object : Observer<CongresspersonDetails>() {
                fun onChanged(congresspersonDetails: CongresspersonDetails?) {
                    if (congresspersonDetails != null) {
                        name.setText(StringBuilder().append(congresspersonDetails!!.getFirstName()).append(" ").append(congresspersonDetails!!.getLastName()).toString())
                        dateOfBirth.setText(StringBuilder().append("Born: ").append(congresspersonDetails!!.getDateOfBirth()).toString())
                        gender.setText(StringBuilder().append("Gender: ").append(congresspersonDetails!!.getGender()).toString())
                    }
                }
            })
        }

    }

}
