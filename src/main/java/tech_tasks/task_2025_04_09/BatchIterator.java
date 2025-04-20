package tech_tasks.task_2025_04_09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class BatchIterator <T extends Number> implements Iterator<Collection<T>> {
    private final List<T> items;
    private final int batchSize;
    private final int chunks;
    private int currentChunk;

    public BatchIterator(Collection<T> items, int batchSize) {
        if (items == null || batchSize < 0) {
            throw new IllegalArgumentException("Items is null or batch size is negative");
        }
        this.items = List.copyOf(items);
        this.batchSize = batchSize;
        this.chunks = items.size() / batchSize + (items.size() % batchSize == 0 ? 0 : 1);
    }

    @Override
    public boolean hasNext() {
        return chunks >= currentChunk;
    }

    @Override
    public Collection<T> next() {
        int fromIndex = (currentChunk * batchSize) > items.size() ? 0 : currentChunk * batchSize;
        int toIndex = Math.min((currentChunk + 1) * batchSize, items.size());
        List<T> next = items.subList(fromIndex, toIndex);
        ++currentChunk;
        return next;
    }
}
