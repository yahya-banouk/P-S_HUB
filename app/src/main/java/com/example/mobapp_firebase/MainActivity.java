package com.example.mobapp_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobapp_firebase.models.MainModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut,btnDashboard;
    public  FirebaseAuth mAuth;
    RecyclerView recyclerView;
    MainAdapter mainAdapter ;
    FloatingActionButton floatingActionButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pour eluminer la bar audessus
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        btnDashboard =findViewById(R.id.btnDashboard);
        mAuth = FirebaseAuth.getInstance();


        //////////////
        ////////////
        //////////
        ////////
        //////
        ////
        ///
        //RECYCLEVIEW
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);



        //button d'ajout
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });




        //////////////
        ////////////
        //////////
        ////////
        //////
        ////
        ///
        //

//        ListView listView = (ListView)findViewById(R.id.listView);
//
//        //
//
//        UserAccount tom = new UserAccount("Tom","admin");
//        UserAccount jerry = new UserAccount("Jerry","user");
//        UserAccount donald = new UserAccount("Donald","guest", false);
//
//        UserAccount[] users = new UserAccount[]{tom,jerry, donald};
//
//
//        // android.R.layout.simple_list_item_1 is a constant predefined layout of Android.
//        // used to create a ListView with simple ListItem (Only one TextView).
//
//        ArrayAdapter<UserAccount> arrayAdapter
//                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
//
//        listView.setAdapter(arrayAdapter);

        //
        ///
        ////
        //////
        ////////
        //////////
        ////////////
        //////////////


        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            setContentView(R.layout.activity_login);
            startActivity(new Intent(this, LoginActivity.class));
        });
        btnDashboard.setOnClickListener(view ->{

            setContentView(R.layout.activity_dashboard);
            startActivity(new Intent(this, Dashboard.class));
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
        else
        {
            //
            //
            // startActivity(new Intent(this, Dashboard.class));
            mainAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
                //s means query
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str)
    {
        ////
        ///
        // code that fetchs data from databse
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("name").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();
        ////
        ///
        //
        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }
}

