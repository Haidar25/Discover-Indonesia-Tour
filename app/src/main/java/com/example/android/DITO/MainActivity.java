package com.example.android.DITO;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.android.DITO.model.AttractionCollection;
import com.example.android.DITO.model.AttractionRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	FirebaseAuth mAuth;
	ProgressBar progressBarMain;
	sharedpref mysharedpref;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	private void logout() {
		mAuth.signOut();
		startActivity(new Intent(MainActivity.this, Masuk.class));
		finish();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
            case R.id.menu_utama:
                Intent is = new Intent (MainActivity.this, Main2.class);
                startActivity(is);
                return true;
			case R.id.trivia_quiz:
				Intent intent = new Intent (MainActivity.this, SelamatdatangquizActivity.class);
				startActivity(intent);
				return true;
			case R.id.sign_out:
				logout();
				break;
			case R.id.review:
				Intent in = new Intent (MainActivity.this, ReviewActivity.class);
				startActivity(in);
				return true;
			case R.id.Settings:
				Intent i = new Intent (MainActivity.this, SettingActivity.class);
				startActivity(i);
				return true;
			case R.id.about:
				Intent ab = new Intent (MainActivity.this, AboutusActivity.class);
				startActivity(ab);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		mysharedpref = new sharedpref(this);
		if (mysharedpref.loadNightModeState() == true) {
			setTheme(R.style.DarkTheme);
		} else
		{
			setTheme(R.style.AppTheme);
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAuth = FirebaseAuth.getInstance();
		progressBarMain = findViewById(R.id.progressBar);
		
		// Initialize list to store collection of attractions
		AttractionRepository repository = AttractionRepository.getInstance(this);
		List<AttractionCollection> collections = repository.getCollections();

		// Hook the recycler view
		RecyclerView recyclerView = findViewById(R.id.main_recycler_view);

		// Set fixed size true and optimize recycler view performance
		// The data container has fixed number of attractions and not infinite list
		recyclerView.setHasFixedSize(true);

		// Connect the RecyclerView widget to the vertical linear layout
		// (not reverse layout - hence false)
		recyclerView.setLayoutManager(new LinearLayoutManager(this,
				LinearLayoutManager.VERTICAL, false));

		// Attach adapter to the RecyclerView widget which is connected to a layout manager
		MasterAdapter collectionAdapter = new MasterAdapter(this, collections);
		recyclerView.setAdapter(collectionAdapter);
	}
}
