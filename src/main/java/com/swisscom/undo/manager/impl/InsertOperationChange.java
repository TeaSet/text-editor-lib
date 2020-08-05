package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import com.swisscom.undo.manager.api.Document;
import com.swisscom.undo.manager.enums.ChangeType;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class InsertOperationChange implements Change {

    private final int position;
    private final String changedString;

    public InsertOperationChange() {
        this.position = 0;
        this.changedString = "";
    }

    @Override
    public String getType() {
        return ChangeType.INSERT.name();
    }

    @Override
    public void apply(Document doc) {
        Objects.requireNonNull(doc, "Document may not be null!");
        doc.insert(position, changedString);
    }

    @Override
    public void revert(Document doc) {
        Objects.requireNonNull(doc, "Document may not be null!");
        doc.delete(doc.getDot(), changedString);
    }
}
