package com.beepscore.bsnotes.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevebaker on 9/2/13.
 */
public class NotesDataSource {
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
        return true;
    }

    public boolean remove(NoteItem note) {
        return true;
    }
}
