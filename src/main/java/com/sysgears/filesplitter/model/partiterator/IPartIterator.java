package com.sysgears.filesplitter.model.partiterator;

/**
 * The IPartIterator interface defines the behavior of a part iterator object.
 */
public interface IPartIterator {

    /**
     * Returns true if next part is present.
     *
     * @return true if next part is present
     */
    public boolean hasNext();

    /**
     * Returns next part size.
     *
     * @return next part size
     */
    public int nextPartSize();
}