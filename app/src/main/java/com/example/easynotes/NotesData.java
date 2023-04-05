package com.example.easynotes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Notes")
public class NotesData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id = 0 ;

    @ColumnInfo(name = "NoteTitle")
    public String title ="";

    @ColumnInfo(name = "theNote")
    public String note = "";

    @ColumnInfo(name = "pinned")
    boolean Pinned = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPinned() {
        return Pinned;
    }

    public void setPinned(boolean pinned) {
        Pinned = false;
    }
}
