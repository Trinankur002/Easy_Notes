package com.example.easynotes;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(NotesData notesData);

    @Query("select * from Notes order by NoteTitle")
    List<NotesData> getAll();

    @Query("update Notes set NoteTitle =:title, theNote =:note where id =:id")
    void update(int id , String title , String note);

    @Delete
    void delete(NotesData notesData);

    @Query("update Notes set pinned =:pin where id=:id ")
    void pin(int id , boolean pin);
}
