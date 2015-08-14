package com.sysgears.filesplitter.model.statistics.state;

/**
 * The ProgressState class provides functionality to display the progress.
 */
public class ProgressState implements IProgressState {

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

    /**
     * Returns a string representation of the ProgressState object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return 100 * current / total + "%";
    }
}