package com.beepscore.bsnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = this.getIntent();

        // author calls intent to recreate the note,
        // probably would be clearer to pass the note
        note = new NoteItem();
        note.setKey(intent.getStringExtra("key"));
        note.setText(intent.getStringExtra("text"));

        EditText et = (EditText) findViewById(R.id.noteText);
        et.setText(note.getText());
        // position cursor at end of text
        et.setSelection(note.getText().length());
    }

}
