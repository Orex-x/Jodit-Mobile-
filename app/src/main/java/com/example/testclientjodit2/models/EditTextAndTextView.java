package com.example.testclientjodit2.models;

public class EditTextAndTextView {
    String edit_text, text, text_hint;

    public EditTextAndTextView(String edit_text, String text, String text_hint) {
        this.edit_text = edit_text;
        this.text = text;
        this.text_hint = text_hint;
    }

    public String getEdit_text() {
        return edit_text;
    }

    public void setEdit_text(String edit_text) {
        this.edit_text = edit_text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_hint() {
        return text_hint;
    }

    public void setText_hint(String text_hint) {
        this.text_hint = text_hint;
    }
}
