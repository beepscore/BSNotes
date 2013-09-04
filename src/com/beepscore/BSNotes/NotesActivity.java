package com.beepscore.bsnotes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.beepscore.bsnotes.data.NoteItem;
import com.beepscore.bsnotes.data.NotesDataSource;

import java.util.List;

public class NotesActivity extends ListActivity {
    /**
     * Called when the activity is first created.
     */

    private static final int EDITOR_ACTIVITY_REQUEST = 1001;
    private static final int MENU_DELETE_ID = 1002;
    private int currentNoteId;

    private NotesDataSource datasource;
    List<NoteItem> notesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        registerForContextMenu(getListView());

        datasource = new NotesDataSource(this);
        refreshDisplay();
    }

    /**
     * Context menu methods
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        currentNoteId = (int) info.id;
        menu.add(0, MENU_DELETE_ID, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (MENU_DELETE_ID == item.getItemId()) {
            NoteItem note = notesList.get(currentNoteId);
            datasource.remove(note);
            refreshDisplay();
        }
        return super.onContextItemSelected(item);
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
        intent.putExtra(NoteItem.KEY, note.getKey());
        intent.putExtra(NoteItem.TEXT, note.getText());

        // constant
        startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (EDITOR_ACTIVITY_REQUEST == requestCode
            && RESULT_OK == resultCode) {
            NoteItem note = new NoteItem();
            note.setKey(data.getStringExtra(NoteItem.KEY));
            note.setText(data.getStringExtra(NoteItem.TEXT));
            datasource.update(note);
            refreshDisplay();
        }
    }

    private void createNote() {
        NoteItem note = NoteItem.getNew();

        // Android will instantiate NoteEditorActivity from class
        Intent intent = new Intent(this, NoteEditorActivity.class);

        // pass a few small objects, easier than passing a large object
        intent.putExtra(NoteItem.KEY, note.getKey());
        intent.putExtra(NoteItem.TEXT, note.getText());

        // constant
        startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST);
    }

}
