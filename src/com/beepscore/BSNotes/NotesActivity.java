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

        datasource = new NotesDataSource(this);
        List<NoteItem> notes = datasource.findAll();
        NoteItem note = notes.get(0);
        note.setText("Updated!");
        datasource.update(note);

        notes = datasource.findAll();
        // Java converts integer to string
        Log.i("NOTES_TAG", "notes.size(): " + notes.size());
        note = notes.get(0);

        Log.i("NOTES_TAG", "onCreate" + note.getKey() + ": " + note.getText());
    }
}
