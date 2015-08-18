package com.sysgears.filesplitter.model.abstractmodel;

/**
 * The IDataFinder interface defines the behavior of the data finder.
 */
public interface IDataFinder extends IDataIterator<IData> {

    /**
     * Gets the data object by name.
     *
     * @param name data name
     * @return data object
     */
    public IData getByName(final String name);
}