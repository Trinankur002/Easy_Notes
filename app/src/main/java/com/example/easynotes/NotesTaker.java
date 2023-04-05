package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

public class NotesTaker extends AppCompatActivity {

    EditText editText_title, edittext_notes;
    ImageView imageview_save;
    NotesData notesData;

    boolean isOldNotes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);



        imageview_save = findViewById(R.id.imageview_save);
        editText_title = findViewById(R.id.editText_title);
        edittext_notes = findViewById(R.id.edittext_notes);

        notesData = new NotesData();
        try {
            notesData= (NotesData) getIntent().getSerializableExtra("old note");
            editText_title.setText(notesData.getTitle());
            edittext_notes.setText(notesData.getNote());
            isOldNotes = true;
        }catch (Exception exception){
            exception.printStackTrace();
        }


//        notesData= (NotesData) getIntent().getSerializableExtra("old note");

        imageview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_title.getText().toString();
                String description = edittext_notes.getText().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTaker.this,"Just type it ... may you forget later !!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isOldNotes) {
                    notesData = new NotesData();

                }


                notesData.setTitle(title);
                notesData.setNote(description);

                Intent intent = new Intent();
                intent.putExtra("note", notesData);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}