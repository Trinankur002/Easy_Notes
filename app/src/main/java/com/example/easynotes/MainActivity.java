package com.example.easynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    RecyclerView recyclerView ;
    NotesListAdapter notesListAdapter;
    List<NotesData> notesData = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;
    NotesData selectedNote;
    SearchView searchView_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView_home = findViewById(R.id.searchView_home);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        database = RoomDB.getInstance(this);
        notesData = database.mainDAO().getAll();


        updateRecycler(notesData);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotesTaker.class);
                startActivityForResult(intent,101);
            }
        });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                filter(newText);
                return true ;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true ;
            }
        });
    }

    private void filter(String newText) {
        List<NotesData> filteredList = new ArrayList<>();
        for (NotesData singleNotes : notesData){
            if (singleNotes.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(singleNotes);
            }
        }

        notesListAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101){
            if(resultCode== Activity.RESULT_OK){
                NotesData new_notes = (NotesData) data.getSerializableExtra("note");
                database.mainDAO().insert(new_notes);
                notesData.clear();
                notesData.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == 102){
            if(resultCode==Activity.RESULT_OK){
                NotesData new_notes = (NotesData) data.getSerializableExtra("note");
                database.mainDAO().update(new_notes.getId(), new_notes.getTitle(), new_notes.getNote());
                notesData.clear();
                notesData.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<NotesData> notesData) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesListAdapter = new NotesListAdapter(MainActivity.this, notesData ,notesClickListner );
        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClickListner notesClickListner = new NotesClickListner() {
        @Override
        public void onClick(NotesData notesData) {

            Intent intent = new Intent(MainActivity.this, NotesTaker.class);
            intent.putExtra("old note",notesData);
            startActivityForResult(intent, 102);
        }

        @Override
        public void onLongclick(NotesData notesData, CardView cardView) {
            selectedNote = new NotesData();
            selectedNote = notesData;
            showPopup(cardView);
        }
        
        
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu =new PopupMenu(this,cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.pin :
//                if (selectedNote.isPinned()){
//                    database.mainDAO().pin(selectedNote.getId(), false);
//                }
//                else {
//                    database.mainDAO().pin(selectedNote.getId(),true );
//                }
//                notesData.clear();
//                notesData.addAll(database.mainDAO().getAll());
//                notesListAdapter.notifyDataSetChanged();
//                return true;

            case R.id.delete:
                database.mainDAO().delete(selectedNote);
                notesData.remove(selectedNote);
                notesListAdapter.notifyDataSetChanged();
                return true;

            default:
                return false;
        }

    }
}