package com.beepscore.bsnotes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void refreshDisplay() {
        notesList = datasource.findAll();
        ArrayAdapter<NoteItem> adapter;
        adapter = new ArrayAdapter<NoteItem>(this,
                R.layout.list_item_layout,
                notesList);
        setListAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.action_create == item.getItemId()) {
            createNote();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        NoteItem note = notesList.get(position);

        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());

        // constant
        int CREATE_REQUEST_CODE = 1001;
        startActivityForResult(intent, CREATE_REQUEST_CODE);
    }

    private void createNote() {
        NoteItem note = NoteItem.getNew();

        // Android will instantiate NoteEditorActivity from class
        Intent intent = new Intent(this, NoteEditorActivity.class);

        // pass a few small objects, easier than passing a large object
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());

        // constant
        int CREATE_REQUEST_CODE = 1001;
        startActivityForResult(intent, CREATE_REQUEST_CODE);
    }

}
