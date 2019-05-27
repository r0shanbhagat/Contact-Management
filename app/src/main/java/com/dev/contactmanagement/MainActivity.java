package com.dev.contactmanagement;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dev.contactmanagement.databinding.ActivityMainBinding;
import com.dev.contactmanagement.viewmodel.ContactViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProvider.AndroidViewModelFactory.
                getInstance(getApplication()).create(ContactViewModel.class);
        setupToolbar();
        //Just Inserting some Dummy Value
        setDefaultValue();
        setUpList();
    }

    private void setDefaultValue() {
        viewModel.insertDefaultData(this);
    }


    /**
     * Preparing List
     */
    private void setUpList() {
        final String[] contactId = viewModel.getContactIdList(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, contactId);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.setModel(viewModel.getContactDetailModel(MainActivity.this, Integer.valueOf(contactId[position])));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * setupToolbar
     */
    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
