package com.swisscom.undo.manager.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleTextDocumentTest {

    @Test
    public void emptyDocumentTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder(), 0);
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void createDocumentWithWrongPositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder(), 2);
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void createDocumentWithNegativePositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder(), -2);
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void createDocumentWithInformationTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string"), 7);
        assertEquals("Test string", testDocument.getContent().toString());
        assertEquals(7, testDocument.getDot());
    }

    @Test
    public void createDocumentWithLastPositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string"), 11);
        assertEquals("Test string", testDocument.getContent().toString());
        assertEquals(11, testDocument.getDot());
    }

    @Test
    public void createDocumentWithLastPositionButWrongPositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string"), 999);
        assertEquals("Test string", testDocument.getContent().toString());
        assertEquals(11, testDocument.getDot());
    }

    @Test
    public void deleteEmptyStringFromDocumentTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder(), 0);
        testDocument.delete(0, "");
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void deleteEmptyStringFromWrongPositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        Exception exception = assertThrows(IllegalStateException.class, () -> testDocument.delete(1, ""));
        assertEquals("Incorrect dot position while deleting due to start 0, end 1, length 0", exception.getMessage());
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void deleteEmptyStringFromNegativePositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        Exception exception = assertThrows(IllegalStateException.class, () -> testDocument.delete(-1, ""));
        assertEquals("Incorrect dot position while deleting due to start 0, end -1, length 0", exception.getMessage());
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void deleteNotExistingStringFromEmptyStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        Exception exception = assertThrows(IllegalStateException.class, () -> testDocument.delete(0, "abc"));
        assertEquals("Missing string: abc to delete from content", exception.getMessage());
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void deleteNotExistingStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 0);
        Exception exception = assertThrows(IllegalStateException.class, () -> testDocument.delete(5, "abc"));
        assertEquals("Missing string: abc to delete from content", exception.getMessage());
        assertEquals("Test string!", testDocument.getContent().toString());
        assertEquals(5, testDocument.getDot());
    }

    @Test
    public void deleteExistingStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 0);
        testDocument.delete(5, "st ");
        assertEquals("Testring!", testDocument.getContent().toString());
        assertEquals(2, testDocument.getDot());
    }

    @Test
    public void deleteWholeStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 0);
        testDocument.delete(12, "Test string!");
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void insertEmptyStringIntoDocumentTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        testDocument.insert(0, "");
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void insertEmptyStringIntoDocumentFromWrongPositionTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        Exception exception = assertThrows(IllegalStateException.class, () -> testDocument.insert(1, ""));
        assertEquals("Incorrect dot position while inserting", exception.getMessage());
        assertEquals("", testDocument.getContent().toString());
        assertEquals(0, testDocument.getDot());
    }

    @Test
    public void insertStringIntoEmptyStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument();
        testDocument.insert(0, "Test string!");
        assertEquals("Test string!", testDocument.getContent().toString());
        assertEquals(12, testDocument.getDot());
    }

    @Test
    public void innerInsertTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 12);
        assertEquals(12, testDocument.getDot());
        testDocument.insert(5, "test ");
        assertEquals("Test test string!", testDocument.getContent().toString());
        assertEquals(10, testDocument.getDot());
    }

    @Test
    public void insertAndDeleteStringTest() {
        SimpleTextDocument testDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 12);
        assertEquals(12, testDocument.getDot());
        testDocument.insert(5, "test ");
        assertEquals("Test test string!", testDocument.getContent().toString());
        assertEquals(10, testDocument.getDot());

        testDocument.delete(9, " test");
        assertEquals("Test string!", testDocument.getContent().toString());
        assertEquals(4, testDocument.getDot());
    }
}
