package org.fugazi.gui.block.block_memento;

import java.util.ArrayList;

public class BlockCareTaker {
    private final ArrayList<BlockMemento> savedBlocks = new ArrayList<>();

    public void addMemento(BlockMemento _m) {
        savedBlocks.add(_m);
    }

    public BlockMemento getMemento(int _index) {
        return savedBlocks.get(_index);
    }
}