package com.sysgears.filesplitter.model.statistics;

/**
 * The ProgressState class provides functionality to display the progress.
 */
public class ProgressState {

    /**
     * Current progress value.
     */
    private final long current;

    /**
     * Total progress value.
     */
    private final long total;

    /**
     * Creates the ProgressState instance specified by a current and total value.
     *
     * @param current current value
     * @param total total value
     */
    public ProgressState(final long current, final long total) {
        this.current = current;
        this.total = total;
    }

    /**
     * Returns a current value.
     *
     * @return current value
     */
    public long getCurrent() {
        return current;
    }

    /**
     * Returns a total value.
     *
     * @return total value
     */
    public long getTotal() {
        return total;
    }
}