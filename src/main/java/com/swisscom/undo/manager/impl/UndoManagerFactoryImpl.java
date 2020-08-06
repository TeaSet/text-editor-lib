package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Document;
import com.swisscom.undo.manager.api.UndoManager;
import com.swisscom.undo.manager.api.UndoManagerFactory;

public class UndoManagerFactoryImpl implements UndoManagerFactory {

    @Override
    public UndoManager createUndoManager(Document doc, int bufferSize) {
        return new UndoManagerImpl(doc, bufferSize);
    }
}
