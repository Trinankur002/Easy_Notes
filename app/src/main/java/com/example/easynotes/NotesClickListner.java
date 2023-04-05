package com.example.easynotes;

import androidx.cardview.widget.CardView;

public interface NotesClickListner {

    void onClick(NotesData notesData);
    void onLongclick(NotesData notesData , CardView cardView);

}
