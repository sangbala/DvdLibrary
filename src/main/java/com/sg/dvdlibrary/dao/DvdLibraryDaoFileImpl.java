package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.*;
import java.util.*;
import java.io.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
 
        loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
        
        
    }

    @Override
    public List<Dvd> getAllDvds()throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title)throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title)throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd searchDvd(String title) {
        Dvd searchResult;
        if (dvds.containsKey(title)) {
            searchResult = dvds.get(title);
            return searchResult;
        } else {
            searchResult = null;
            return searchResult;
        }

    }

    @Override
    public Dvd editDvd(String title, Dvd dvd)throws DvdLibraryDaoException {
        loadLibrary();
        Dvd edittedDvd = dvds.put(title, dvd);
        writeLibrary();
        return edittedDvd;

    }

    private Dvd unmarshallDvd(String dvdAsText) {
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Titanic::December 19, 1997::PG-13::James Cameron::Unknown Studio::grandma's favorite
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |xxxx|xxx|xxxxxxxx|xxxxxxxxxxxxxxxxxx|xxxxxx|xxxxxx|xxxxxxxxxx|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]            [4]    [5]    [6]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvd title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new Dvd object to satisfy
        // the requirements of the Dvd constructor.
        Dvd dvdFromFile = new Dvd(title);

        // However, there are 5 remaining tokens that need to be set into the
        // new dvd object. Do this manually by using the appropriate setters.
        // Index 1 - releaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - mpaaRating
        dvdFromFile.setMpaaRating(dvdTokens[2]);

        // Index 3 - Director's Name
        dvdFromFile.setDirectorName(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - Comment
        dvdFromFile.setRatingOrNote(dvdTokens[5]);

        // We have now created a dvd! Return it!
        return dvdFromFile;
    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {
        // We need to turn a Dvd object into a line of text for our file.
        // For example, we need an in memory object to end up like this:

        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        // ReleaseDate
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // mpaaRating
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;

        // DirectorName
        dvdAsText += aDvd.getDirectorName() + DELIMITER;

        // Studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // RatingOrNote 
        dvdAsText += aDvd.getRatingOrNote();

        // We have now turned a Dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all Dvds in the library out to a LIBRARY_FILE. See loadRoster for
     * file format.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeLibrary() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the library file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        String dvdAsText;
        List dvdList = this.getAllDvds();
        for (Object currentDvd : dvdList) {
            // turn a Dvd into a String
            dvdAsText = marshallDvd((Dvd) currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
