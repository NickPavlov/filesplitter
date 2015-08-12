package com.sysgears.filesplitter.model.abstractmodel;

import java.io.IOException;
import java.nio.channels.Channel;

/**
 * The IData interface defines the behavior of the data storage object.
 */
public interface IData {

    /**
     * Returns a data name.
     *
     * @return data name
     */
    public String getName();

    /**
     * Returns data size.
     *
     * @return data size
     */
    public long getSize();

    /**
     * Returns a byte channel.
     *
     * @return channel
     * @throws IOException in case if I/O error occurred
     */
    public Channel getChannel() throws IOException;
}