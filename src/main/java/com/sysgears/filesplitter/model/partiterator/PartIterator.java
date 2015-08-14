package com.sysgears.filesplitter.model.partiterator;

/**
 * The PartIterator class provides functionality to iterate...
 */
public class PartIterator implements IPartIterator {

    /**
     * Part size.
     */
    private final long partSize;

    /**
     * Full parts count.
     */
    private long fullPartsCount;

    /**
     * Remaining values count.
     */
    private long remainingValuesCount;

    /**
     * Creates PartIterator instance specified by total size and part size.
     *
     * @param totalSize total values count
     * @param partSize part size
     */
    public PartIterator(final long totalSize, final int partSize) {
        this.partSize = partSize;
        this.fullPartsCount = totalSize / partSize;
        this.remainingValuesCount = totalSize - fullPartsCount * partSize;
    }

    /**
     * Returns true in next part is present.
     *
     * @return true in next part is present
     */
    public boolean hasNext() {
        return (fullPartsCount > 0) || (remainingValuesCount > 0);
    }

    /**
     * Returns size of the next part. If there is no parts returns 0.
     *
     * @return part size
     */
    public long nextPartSize() {
        long partSize = 0;
        if (fullPartsCount > 0) {
            partSize = this.partSize;
            --fullPartsCount;
        } else if (remainingValuesCount > 0) {
            partSize = remainingValuesCount;
            remainingValuesCount = 0;
        }

        return partSize;
    }
}