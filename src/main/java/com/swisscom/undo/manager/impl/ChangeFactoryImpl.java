package com.swisscom.undo.manager.impl;

import com.swisscom.undo.manager.api.Change;
import com.swisscom.undo.manager.api.ChangeFactory;

public class ChangeFactoryImpl implements ChangeFactory {

    @Override
    public Change createDeletion(int pos, String s) {
        return new DeleteOperationChange(pos, s);
    }

    @Override
    public Change createInsertion(int pos, String s) {
        return new InsertOperationChange(pos, s);
    }
}
