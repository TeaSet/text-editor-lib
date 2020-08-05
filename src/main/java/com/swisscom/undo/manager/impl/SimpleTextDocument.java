package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SimpleTextDocument implements Document {

    @Getter
    private StringBuilder content = new StringBuilder();
    private int currentPos;

    public SimpleTextDocument(StringBuilder content, int currentPos) {
        this.content = content;
        if (currentPos > content.length()) this.currentPos = content.length();
        else this.currentPos = Math.max(currentPos, 0);
    }

    @Override
    public void delete(int pos, String s) {
        this.setCurrentPos(pos);
        try {
            if (!content.substring(0, pos).contains(s)) {
                throw new IllegalStateException("Missing string: " + s + " to delete from content");
            }
            content.delete(pos - s.length(), pos);
            this.setDot(-s.length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalStateException("Incorrect dot position while deleting due to " + e.getMessage());
        }
    }

    @Override
    public void insert(int pos, String s) {
        this.setCurrentPos(pos);
        try {
            content.insert(pos, s);
            this.setDot(s.length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalStateException("Incorrect dot position while inserting");
        }
    }

    @Override
    public int getDot() {
        return currentPos;
    }

    private void setDot(int pos) {
        if (currentPos + pos > content.length() || currentPos < 0) {
            throw new IllegalStateException("Illegal dot position while updating current position");
        }
        this.currentPos += pos;
    }

    private void setCurrentPos(int pos) {
        if (pos <= content.length() && pos >= 0) currentPos = pos;
    }
}
