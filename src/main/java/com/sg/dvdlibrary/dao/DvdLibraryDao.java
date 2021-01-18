
package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.*;
import java.util.*;

public interface DvdLibraryDao {
    
    
    Dvd addDvd(String title, Dvd dvd)throws DvdLibraryDaoException;
    List <Dvd> getAllDvds()throws DvdLibraryDaoException;
    Dvd getDvd(String title)throws DvdLibraryDaoException;
    Dvd removeDvd(String title)throws DvdLibraryDaoException;
    Dvd searchDvd(String title);
    Dvd editDvd(String title, Dvd dvd)throws DvdLibraryDaoException;
           
}
