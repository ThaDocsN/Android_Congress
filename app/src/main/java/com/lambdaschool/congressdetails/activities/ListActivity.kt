package com.lambdaschool.congressdetails.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.lambdaschool.congressdataapiaccess.CongressDao
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview
import com.lambdaschool.congressdetails.R
import com.lambdaschool.congressdetails.viewModel.CongressOverviewViewModel

import java.util.ArrayList


class ListActivity : AppCompatActivity() {
    internal var context: Context
    internal var viewModel: CongressOverviewViewModel
    internal var parentLayout: LinearLayout

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        context = this
        parentLayout = findViewById(R.id.parentlayout)

        viewModel = ViewModelProviders.of(this).get(CongressOverviewViewModel::class.java)
        viewModel.overView!!.observe(this, object : Observer<ArrayList<CongresspersonOverview>>() {
            fun onChanged(congresspersonOverviews: ArrayList<CongresspersonOverview>?) {
                if (congresspersonOverviews != null) {
                    for (person in congresspersonOverviews) {
                        parentLayout.addView(getDefaultTextView(person))

                    }
                }
            }
        })

        CongressDao.getAllMembers()
    }

    internal fun getDefaultTextView(person: CongresspersonOverview): TextView {
        val view = TextView(context)
        val personName = StringBuilder().append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n").append(person.getParty()).append("-").append(person.getState()).toString()
        val idTag = person.getId()
        view.tag = idTag
        view.setText(personName)
        view.textSize = 28f
        view.setOnClickListener {
            val intentCongressPerson = Intent(context, DetailCongressPerson::class.java)
            intentCongressPerson.putExtra(CONGRESS_PERSON_ID, idTag)
            startActivity(intentCongressPerson)
        }

        return view
    }

    companion object {

        val CONGRESS_PERSON_ID = "person_id"
    }
}
