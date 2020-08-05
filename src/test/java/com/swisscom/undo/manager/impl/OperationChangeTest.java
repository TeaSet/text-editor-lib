package com.swisscom.undo.manager.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationChangeTest {

    @Test
    public void throwsNPEDeleteOperationTest() {
        DeleteOperationChange deleteOperation = new DeleteOperationChange();
        Exception applyException = assertThrows(NullPointerException.class, () -> deleteOperation.apply(null));
        assertEquals("Document may not be null!", applyException.getMessage());

        Exception revertException = assertThrows(NullPointerException.class, () -> deleteOperation.revert(null));
        assertEquals("Document may not be null!", revertException.getMessage());
    }

    @Test
    public void throwsNPEInsertOperationTest() {
        InsertOperationChange insertOperation = new InsertOperationChange();
        Exception applyException = assertThrows(NullPointerException.class, () -> insertOperation.apply(null));
        assertEquals("Document may not be null!", applyException.getMessage());

        Exception revertException = assertThrows(NullPointerException.class, () -> insertOperation.revert(null));
        assertEquals("Document may not be null!", revertException.getMessage());
    }

    @Test
    public void emptyDeleteChangeForEmptyDocumentTest() {
        DeleteOperationChange deleteOperation = new DeleteOperationChange();
        SimpleTextDocument textDocument = new SimpleTextDocument();

        deleteOperation.apply(textDocument);
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        deleteOperation.revert(textDocument);
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());
        assertEquals("DELETE", deleteOperation.getType());
    }

    @Test
    public void emptyDeleteChangeForNotEmptyDocumentTest() {
        DeleteOperationChange deleteOperation = new DeleteOperationChange();
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test"), 2);

        deleteOperation.apply(textDocument);
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        deleteOperation.revert(textDocument);
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());
        assertEquals("DELETE", deleteOperation.getType());
    }

    @Test
    public void deleteSubstringFromNotEmptyDocumentTest() {
        DeleteOperationChange deleteOperation = new DeleteOperationChange(11, " string");
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 2);

        deleteOperation.apply(textDocument);
        assertEquals("Test!", textDocument.getContent().toString());
        assertEquals(4, textDocument.getDot());

        deleteOperation.revert(textDocument);
        assertEquals("Test string!", textDocument.getContent().toString());
        assertEquals(11, textDocument.getDot());
    }

    @Test
    public void throwsExceptionWhenDeleteWrongStringTest() {
        DeleteOperationChange deleteOperation = new DeleteOperationChange(11, "string");
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test test test!"), 2);
        Exception applyException = assertThrows(IllegalStateException.class, () -> deleteOperation.apply(textDocument));
        assertEquals("Missing string: string to delete from content", applyException.getMessage());
    }

    @Test
    public void emptyInsertChangeForEmptyDocumentTest() {
        InsertOperationChange insertOperation = new InsertOperationChange();
        SimpleTextDocument textDocument = new SimpleTextDocument();

        insertOperation.apply(textDocument);
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        insertOperation.revert(textDocument);
        assertEquals("", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());
        assertEquals("INSERT", insertOperation.getType());
    }

    @Test
    public void emptyInsertChangeForNotEmptyDocumentTest() {
        InsertOperationChange insertOperation = new InsertOperationChange();
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test"), 2);

        insertOperation.apply(textDocument);
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());

        insertOperation.revert(textDocument);
        assertEquals("Test", textDocument.getContent().toString());
        assertEquals(0, textDocument.getDot());
        assertEquals("INSERT", insertOperation.getType());
    }

    @Test
    public void insertSubstringIntoNotEmptyDocumentTest() {
        InsertOperationChange insertOperation = new InsertOperationChange(11, " string");
        SimpleTextDocument textDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 2);

        insertOperation.apply(textDocument);
        assertEquals("Test string string!", textDocument.getContent().toString());
        assertEquals(18, textDocument.getDot());

        insertOperation.revert(textDocument);
        assertEquals("Test string!", textDocument.getContent().toString());
        assertEquals(11, textDocument.getDot());
    }
}
