package com.beepscore.bsnotes.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevebaker on 9/2/13.
 */
public class NotesDataSource {

    // constant
    private static final String PREFKEY = "notes";

    private SharedPreferences notePrefs;

    // Constructor.
    // Adding constructor with argument(s)
    // invalidates calling default constructor- new NotesDataSource()
    public NotesDataSource(Context context) {
        notePrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
    }

    public List<NoteItem> findAll() {
        // ArrayList is a concrete class that implements List interface.
        // Using polymorphism.
        // ArrayList stores data in the order it was added.
        List<NoteItem> noteList = new ArrayList<NoteItem>();

        // add one note
        NoteItem note = NoteItem.getNew();
        noteList.add(note);

        return noteList;
    }

    public boolean update(NoteItem note) {

        SharedPreferences.Editor editor = notePrefs.edit();
        // if notePrefs doesn't have an entry with this key, add one.
        // if notePrefs has an entry with this key, set value.
        editor.putString(note.getKey(), note.getText());
        editor.commit();
        return true;
    }

    public boolean remove(NoteItem note) {

        if (notePrefs.contains(note.getKey())) {
            SharedPreferences.Editor editor = notePrefs.edit();
            // if notePrefs doesn't have an entry with this key, add one.
            // if notePrefs has an entry with this key, set value.
            editor.remove(note.getKey());
            editor.commit();
        }
        return true;
    }
}
