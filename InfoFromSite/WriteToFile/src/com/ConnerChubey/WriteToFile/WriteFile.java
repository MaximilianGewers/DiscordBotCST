package com.ConnerChubey.WriteToFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Good tutorials: http://www.homeandlearn.co.uk/java/java.html
/*
 * Library Class
 */

public class WriteFile {
    private String path;
    private boolean appendToFile = true;

    /**
     * 
     * Constructor for the WriteFile class.
     * 
     * @param path
     */
    public WriteFile ( String path ) {
        this.path = path;
    }

    /**
     * 
     * Constructor for the WriteFile class.
     * 
     * @param path
     * @param append
     */
    public WriteFile ( String path, boolean append ) {
        this.path = path;
        appendToFile = append;
    }

    /**
     * 
     * Purpose: Write a single line to a file
     * 
     * @param textLine
     * @throws IOException
     */
    public void writeToFile( String textLine ) throws IOException {
        FileWriter write = new FileWriter( path, appendToFile );
        PrintWriter printLine = new PrintWriter( write );
        printLine.printf( "%s" + "%n", textLine );
        printLine.close();
    }

    /**
     * 
     * Purpose: Write and array to the file
     * 
     * @param array
     * @throws IOException
     */
    public void writeArrayToFile( String[] array ) throws IOException {
        for ( String line : array ) {
            writeToFile( line );
        }
    }

    /**
     * 
     * Purpose: Clears all content in a file (useful for writing array to a file)
     * 
     * @throws IOException
     */
    public void wipeFile() throws IOException {
        FileWriter write = new FileWriter( path, false );
        PrintWriter printLine = new PrintWriter( write );
        printLine.close();
    }

}
