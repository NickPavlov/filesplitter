package com.sysgears.filesplitter.model.abstractmodel;

/**
 * The IDataFinder interface defines the behavior of the data finder.
 */
public interface IDataFinder {

    /**
     * Gets the data object by name.
     *
     * @param name data name
     * @return data object
     */
    public IData getByName(final String name);

    /**
     * Returns true if the next object exist, false otherwise.
     *
     * @return true if the next object exist, false otherwise.
     */
    public boolean hasNext();

    /**
     * Gets the next data object.
     *
     * @return data object
     */
    public IData next();
}
