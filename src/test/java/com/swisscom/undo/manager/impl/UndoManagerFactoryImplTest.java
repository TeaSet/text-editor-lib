package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.UndoManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UndoManagerFactoryImplTest {

    @Test
    public void createManagerTest() {
        UndoManagerFactoryImpl managerFactory = new UndoManagerFactoryImpl();
        UndoManager undoManager = managerFactory.createUndoManager(new SimpleTextDocument(), 5);
        assertNotNull(undoManager);
    }
}
