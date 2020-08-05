package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeFactoryImplTest {

    @Test
    public void createDeletionTest() {
        ChangeFactoryImpl changeFactory = new ChangeFactoryImpl();
        Change deletionChange = changeFactory.createDeletion(0, "Test");
        assertTrue(deletionChange instanceof DeleteOperationChange);
        assertEquals("DELETE", deletionChange.getType());
    }

    @Test
    public void createInsertionTest() {
        ChangeFactoryImpl changeFactory = new ChangeFactoryImpl();
        Change insertionChange = changeFactory.createInsertion(0, "Test");
        assertTrue(insertionChange instanceof InsertOperationChange);
        assertEquals("INSERT", insertionChange.getType());
    }
}
