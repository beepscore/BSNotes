package com.beepscore.bsnotes;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.beepscore.bsnotes.data.NoteItem;
import com.beepscore.bsnotes.data.NotesDataSource;

import java.util.List;

public class NotesActivity extends ListActivity {
    /**
     * Called when the activity is first created.
     */

    private NotesDataSource datasource;
    List<NoteItem> notesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new NotesDataSource(this);
        refreshDisplay();
    }

    private void refreshDisplay() {
        notesList = datasource.findAll();
        ArrayAdapter<NoteItem> adapter =
        new ArrayAdapter<NoteItem>(this,
                android.R.layout.simple_list_item_1,
                notesList);
        setListAdapter(adapter);
    }
}
