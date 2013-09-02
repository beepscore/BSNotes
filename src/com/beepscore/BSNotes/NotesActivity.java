package com.beepscore.bsnotes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.beepscore.bsnotes.data.NoteItem;
import com.beepscore.bsnotes.data.NotesDataSource;

import java.util.List;

public class NotesActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private NotesDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new NotesDataSource();
        List<NoteItem> notes = datasource.findAll();
        NoteItem note = notes.get(0);

        Log.i("NOTES", note.getKey());
    }
}
