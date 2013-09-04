package com.beepscore.bsnotes.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.*;

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

        // Java Map is an unordered collection (a dictionary)
        // shared preferences doesn't know value type
        Map<String, ?> notesMap = notePrefs.getAll();
        if (null == notesMap) {
            // avoid potential NullPointerException
            notesMap = Collections.emptyMap();
        }

        // TreeSet sorts the keySet
        SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());

        // ArrayList is a concrete class that implements List interface.
        // Using polymorphism.
        // ArrayList stores data in the order it was added.
        List<NoteItem> noteList = new ArrayList<NoteItem>();

        // enumerate over keys
        for (String key : keys) {
            NoteItem note = new NoteItem();
            note.setKey(key);
            note.setText((String) notesMap.get(key));
            noteList.add(note);
            Log.i("NOTES_TAG", "findAll()" + note.getKey() + ": " + note.getText());
        }

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
