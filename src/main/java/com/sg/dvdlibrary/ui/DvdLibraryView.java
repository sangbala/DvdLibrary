package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.*;

public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List the DVDs in the collection");
        io.print("2. Add a DVD to the collection");
        io.print("3. View an existing DVD");
        io.print("4. Remove a DVD to the collection");
        io.print("5. Edit the information for an existing DVD");
        io.print("6. Search for a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 7);
    }

    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter Dvd title");
        String releaseDate = io.readString("Please enter release date");
        String mpaaRating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio");
        String ratingOrNote = io.readString("Please enter rating or note");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setRatingOrNote(ratingOrNote);

        return currentDvd;
    }

    public void displayAddDvdBanner() {
        io.print("=== Add Dvd ===");
    }

    public void displayAddSuccessBanner() {
        io.readString(
                "Dvd successfully added.  Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorName(),
                    currentDvd.getStudio(),
                    currentDvd.getRatingOrNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display Dvd ===");
    }

    public String getDvdTitle() {
        return io.readString("Please enter the Dvd Title.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("Dvd title: "+dvd.getTitle());
            io.print("Dvd Release Date: "+dvd.getReleaseDate());
            io.print("MPAA Rating: "+dvd.getMpaaRating());
            io.print("Director's Name: "+dvd.getDirectorName());
            io.print("Studio: "+dvd.getStudio());
            io.print("Rating or Note "+ dvd.getRatingOrNote());
            io.print("");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("Dvd successfully removed.");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displaySearchDvdBanner() {
        io.print("=== Search Dvd ===");
    }

    public void displaySearchResult(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " is found.");
            io.print("Dvd title: "+dvd.getTitle());
            io.print("Dvd Release Date: "+dvd.getReleaseDate());
            io.print("MPAA Rating: "+dvd.getMpaaRating());
            io.print("Director's Name: "+dvd.getDirectorName());
            io.print("Studio: "+dvd.getStudio());
            io.print("Rating or Note "+ dvd.getRatingOrNote());
            io.print("");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit Dvd ===");
    }

    public Dvd editDvdInfo(String s) {
        String title = s;
        String releaseDate = io.readString("Please update release date");
        String mpaaRating = io.readString("Please update new MPAA rating");
        String directorName = io.readString("Please update director's name");
        String studio = io.readString("Please update studio");
        String ratingOrNote = io.readString("Please update rating or note");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setRatingOrNote(ratingOrNote);

        return currentDvd;
    }

    public void displayEditSuccessResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("Dvd successfully editted.");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditFailResult() {

        io.print("No such Dvd. No editting made");
        io.readString("Please hit enter to continue.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
