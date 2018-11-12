package com.lambdaschool.congressdetails.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonDetails;
import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;
import com.lambdaschool.congressdetails.R;
import com.lambdaschool.congressdetails.viewModel.DetailCongressPersonViewModel;

public class DetailCongressPerson extends AppCompatActivity {

    DetailCongressPersonViewModel viewModel;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_congress_person);

        name = findViewById(R.id.textView);

        String congressPersonId = getIntent().getStringExtra(ListActivity.CONGRESS_PERSON_ID);
        if (congressPersonId != null && congressPersonId != ""){
            viewModel = ViewModelProviders.of(this).get(DetailCongressPersonViewModel.class);
            viewModel.getDetails(congressPersonId).observe(this, new Observer<CongresspersonDetails>() {
                @Override
                public void onChanged(@Nullable CongresspersonDetails congresspersonDetails) {
                    if (congresspersonDetails != null){
                        name.setText(congresspersonDetails.getFirstName());
                    }
                }
            });
        }

    }

}
