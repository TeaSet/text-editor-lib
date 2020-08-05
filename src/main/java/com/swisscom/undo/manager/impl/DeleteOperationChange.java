package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import com.swisscom.undo.manager.api.Document;
import com.swisscom.undo.manager.enums.ChangeType;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class DeleteOperationChange implements Change {

    private final int position;
    private final String changedString;

    public DeleteOperationChange() {
        this.position = 0;
        this.changedString = "";
    }

    @Override
    public String getType() {
        return ChangeType.DELETE.name();
    }

    @Override
    public void apply(Document doc) {
        Objects.requireNonNull(doc, "Document may not be null!");
        doc.delete(position, changedString);
    }

    @Override
    public void revert(Document doc) {
        Objects.requireNonNull(doc, "Document may not be null!");
        doc.insert(doc.getDot(), changedString);
    }
}
