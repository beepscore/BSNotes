package com.beepscore.bsnotes.data;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Model object.
// Use default super class java.lang.Object
// This is a "pojo", plain old java object
public class NoteItem {

    private String key;
    private String text;

    // accessor methods
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // suppress warnings about SimpleDateFormat class
    @SuppressLint("SimpleDateFormat")
    // returns a NoteItem instance
    // static method can be called on a class
    public static NoteItem getNew() {

        // Use en_US locale format. This enables alpha sort to be chronological.
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);

        String pattern = "yyyy-MM-dd HH:mm:ss Z";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());

        NoteItem note = new NoteItem();
        note.setKey(key);
        note.setText("");
        return note;
    }

}
