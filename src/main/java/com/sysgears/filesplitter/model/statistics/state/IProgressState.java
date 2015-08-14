package com.sysgears.filesplitter.model.statistics.state;

/**
 * The IProgressState interface defines the behavior of a progress state object.
 */
public interface IProgressState {

    /**
     * Returns a current value.
     *
     * @return current value
     */
    public long getCurrent();

    /**
     * Returns a total value.
     *
     * @return total value
     */
    public long getTotal();
}