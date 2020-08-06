package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import com.swisscom.undo.manager.api.Document;
import com.swisscom.undo.manager.api.UndoManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class UndoManagerImpl implements UndoManager {

    private final List<Change> operations;
    private final Document document;
    private final int bufferSize;
    private int currentIndex = -1;

    public UndoManagerImpl(Document document, int bufferSize) {
        this.document = document;
        this.bufferSize = bufferSize;
        this.operations = new ArrayList<>(bufferSize);
    }

    @Override
    public void registerChange(Change change) {
        Objects.requireNonNull(change, "May not be null!");
        clearHistory();
        if (operations.size() == this.bufferSize) {
            operations.remove(0);
            this.currentIndex--;
        }
        operations.add(change);
        this.currentIndex++;
        change.apply(document);
    }

    @Override
    public boolean canUndo() {
        return currentIndex >= 0;
    }

    @Override
    public void undo() {
        if (!canUndo()) return;
        operations.get(currentIndex).revert(document);
        this.currentIndex--;
    }

    @Override
    public boolean canRedo() {
        return operations.size() > 0 && currentIndex < operations.size() - 1;
    }

    @Override
    public void redo() {
        if (!canRedo()) return;
        this.currentIndex++;
        operations.get(currentIndex).apply(document);
    }

    private void clearHistory() {
        int index = this.currentIndex + 1;
        if (index < operations.size()) {
            operations.subList(index, operations.size()).clear();
        }
    }
}
