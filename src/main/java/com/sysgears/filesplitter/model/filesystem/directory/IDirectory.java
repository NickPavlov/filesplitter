package com.sysgears.filesplitter.model.filesystem.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The IDirectory interface defines the behavior of a directory object.
 */
public interface IDirectory {

    /**
     * Returns an absolute path with an inner directory.
     * The original directory is not changed.
     *
     * @param innerDirectory inner directory name
     * @return current directory
     */
    public IDirectory appendInnerDirectory(final String innerDirectory) throws IOException;

    /**
     * Returns a directory.
     *
     * @return directory
     */
    public File getDirectory();

    /**
     * Returns a directory absolute path.
     *
     * @return absolute path
     */
    public String getAbsolutePath();

    /**
     * Returns a <code>Path</code> of the directory.
     *
     * @return <code>Path</code>
     */
    public Path getPath();
}