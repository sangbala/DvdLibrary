
package com.sg.dvdlibrary.dao;

public class DvdLibraryDaoException extends Exception {


    public DvdLibraryDaoException(String message) {
         super(message);
    }

    /**
     * Constructs an instance of <code>ClassRosterDaoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DvdLibraryDaoException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
