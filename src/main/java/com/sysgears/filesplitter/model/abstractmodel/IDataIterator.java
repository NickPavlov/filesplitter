package com.sysgears.filesplitter.model.abstractmodel;

/**
 * The IDataIterator interface defines the behavior of an iterator object.
 */
public interface IDataIterator<T> {

    /**
     * Returns true if the next object exist, false otherwise.
     *
     * @return true if the next object exist, false otherwise.
     */
    public boolean hasNext();

    /**
     * Returns the next data object.
     *
     * @return data object
     */
    public T next();
}