package com.sysgears.filesplitter.model.math.partiterator;

/**
 * The IPartIterator interface defines the behavior of a part iterator object.
 */
public interface IPartIterator {

    /**
     * Returns true in next part is present.
     *
     * @return true in next part is present
     */
    public boolean hasNext();

    /**
     * Returns size of the next part. If there is no parts returns 0.
     *
     * @return part size
     */
    public long next();
}