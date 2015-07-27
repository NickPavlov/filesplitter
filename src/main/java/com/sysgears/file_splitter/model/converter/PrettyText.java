package com.sysgears.file_splitter.model.converter;

/**
 * The <code>PrettyText</code> class provides
 * functionality for creating pretty text.
 */
public class PrettyText {

    /**
     * Returns command line separator with title.
     *
     * @param title string
     * @return string
     */
    public static String createSeparator(final String title, final int width) {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < Math.round(width - title.length()) / 2 - 1; ++i) {
            separator.append("-");
        }

        return separator + title + separator;
    }

    private PrettyText() {
    }
}