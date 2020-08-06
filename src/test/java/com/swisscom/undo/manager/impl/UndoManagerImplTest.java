package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UndoManagerImplTest {

    @Test
    public void emptyUndoManagerTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument();
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 0);
        assertNotNull(undoManager.getDocument());
        assertEquals(0, undoManager.getBufferSize());
        assertEquals(-1, undoManager.getCurrentIndex());
        assertEquals(0, undoManager.getOperations().size());
    }

    @Test
    public void registerChangeTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument();
        Change insertChange = new ChangeFactoryImpl().createInsertion(0, "");
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 10);
        undoManager.registerChange(insertChange);
        assertEquals(10, undoManager.getBufferSize());
        assertEquals(0, undoManager.getCurrentIndex());
        assertEquals(1, undoManager.getOperations().size());
        assertTrue(undoManager.canUndo());
        assertFalse(undoManager.canRedo());
    }

    @Test
    public void insertionChangeWithUndoAndRedoTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument();
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 10);

        Change insertChange = new ChangeFactoryImpl().createInsertion(0, "Test");
        undoManager.registerChange(insertChange);
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());

        undoManager.undo();
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        undoManager.redo();
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());
    }

    @Test
    public void deleteChangeWithUndoAndRedoTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test"), 0);
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 10);

        Change deleteChange = new ChangeFactoryImpl().createDeletion(4, "Test");
        undoManager.registerChange(deleteChange);
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        undoManager.undo();
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());

        undoManager.redo();
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        assertTrue(undoManager.canUndo());
        assertFalse(undoManager.canRedo());
    }

    @Test
    public void bufferSizeTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument();
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 2);

        Change insertChange = new ChangeFactoryImpl().createInsertion(0, "Test");
        undoManager.registerChange(insertChange);

        Change deleteChange = new ChangeFactoryImpl().createDeletion(4, "Test");
        undoManager.registerChange(deleteChange);

        Change anotherInsertChange = new ChangeFactoryImpl().createInsertion(0, "TEST");
        undoManager.registerChange(anotherInsertChange);

        assertEquals(2, undoManager.getOperations().size());
        assertEquals("DELETE", undoManager.getOperations().get(0).getType());

        undoManager.undo();
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        undoManager.undo();
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());

        undoManager.redo();
        undoManager.redo();
        assertEquals("TEST", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());
    }

    @Test
    public void clearHistoryTest() {
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 0);
        UndoManagerImpl undoManager = new UndoManagerImpl(textDocument, 10);

        Change insert1 = new ChangeFactoryImpl().createInsertion(5, "test ");
        undoManager.registerChange(insert1);

        Change insert2 = new ChangeFactoryImpl().createInsertion(10, "test ");
        undoManager.registerChange(insert2);

        Change insert3 = new ChangeFactoryImpl().createInsertion(15, "test ");
        undoManager.registerChange(insert3);

        Change insert4 = new ChangeFactoryImpl().createInsertion(20, "test ");
        undoManager.registerChange(insert4);

        Change insert5 = new ChangeFactoryImpl().createInsertion(25, "test ");
        undoManager.registerChange(insert5);
        assertEquals(5, undoManager.getOperations().size());

        undoManager.undo();
        undoManager.undo();

        Change insert6 = new ChangeFactoryImpl().createInsertion(20, "TEST ");
        undoManager.registerChange(insert6);
        assertEquals(4, undoManager.getOperations().size());
    }
}
