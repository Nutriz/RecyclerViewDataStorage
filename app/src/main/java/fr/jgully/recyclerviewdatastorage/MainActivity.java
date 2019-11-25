package fr.jgully.recyclerviewdatastorage;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import fr.jgully.recyclerviewdatastorage.NewPeopleDialogFragment.OnNewPeopleCreated;

public class MainActivity extends AppCompatActivity implements OnNewPeopleCreated {

    private List<People> peoples = new ArrayList<>();
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add test data
        peoples.add(new People("People test "));

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter = new PeopleAdapter(peoples);
        recyclerView.setAdapter(peopleAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NewPeopleDialogFragment alertDialog = NewPeopleDialogFragment.newInstance(peoples.size());
        alertDialog.show(fm, "new_people_dialog_fragment");
    }

    private void notifyDataChangedOnUiThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                peopleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreated(People people) {
        peoples.add(people);
        notifyDataChangedOnUiThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            peoples.clear();
            notifyDataChangedOnUiThread();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
