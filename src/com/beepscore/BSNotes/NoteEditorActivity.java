package com.beepscore.bsnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import com.beepscore.bsnotes.data.NoteItem;

/**
 * Created by stevebaker on 9/3/13.
 */
public class NoteEditorActivity extends Activity {

    private NoteItem note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // change launcher icon to options button with id home
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();

        // author calls intent to recreate the note,
        // probably would be clearer to pass the note
        note = new NoteItem();
        note.setKey(intent.getStringExtra(NoteItem.KEY));
        note.setText(intent.getStringExtra(NoteItem.TEXT));

        EditText et = (EditText) findViewById(R.id.noteText);
        et.setText(note.getText());
        // position cursor at end of text
        et.setSelection(note.getText().length());
    }

    @Override
    // called when hardware back button pressed
    public void onBackPressed() {
        saveAndFinish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            saveAndFinish();
        }
        // return false tells caller that current method has handled event,
        // caller does not need to do anything else.
        return false;
    }

    private void saveAndFinish() {
        EditText et = (EditText) findViewById(R.id.noteText);
        String noteText = et.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(NoteItem.KEY, note.getKey());
        intent.putExtra(NoteItem.TEXT, noteText);
        setResult(RESULT_OK, intent);

        // return to parent activity
        finish();
    }
}
