package com.ConnerChubey.WriteToFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Library Class
 */

public class ReadFile {

    private String path;

    /**
     * 
     * Constructor for the ReadFile class.
     * 
     * @param filePath
     */
    public ReadFile( String path ) {
        this.path = path;
    }

    /**
     * 
     * Purpose:Returns a array string of all lines in a file
     * 
     * @return
     * @throws IOException
     */
    public String[] OpenFile () throws IOException {
        FileReader fr = new FileReader( path );
        BufferedReader textReader = new BufferedReader( fr );

        int numberOfLines = readLines();
        String[] textData = new String[numberOfLines];

        for ( int i = 0; i < numberOfLines; i++ ) {
            textData[i] = textReader.readLine();
        }

        textReader.close();
        return textData;
    }

    /**
     * 
     * Purpose:gets how many lines are in a file (private)
     * 
     * @return
     * @throws IOException
     */
    @ SuppressWarnings ( "unused" )
    int readLines () throws IOException {
        FileReader fileToRead = new FileReader( path );
        BufferedReader bf = new BufferedReader( fileToRead );

        String aLine;
        int numberOfLines = 0;

        while ( (aLine = bf.readLine()) != null ) {
            numberOfLines++ ;
        }

        fileToRead.close();
        bf.close();
        return numberOfLines;
    }

    /**
     * Purpose: Check if file exists
     * 
     * @return
     */
    public boolean exists () {
        File file = new File( path );
        return file.exists();
    }

    /**
     * 
     * Purpose: Create a new file
     * 
     * @throws IOException
     */
    public void createFile () throws IOException {
        File file = new File( path );
        try {
            file.createNewFile();
        } catch ( IOException e ) {
            System.out.println( e.getMessage() + "/nFile could not be created" );
        }
        return;
    }

    /**
     * Purpose: Finds a line in a file
     * 
     * @param lineToFind
     * @return
     * @throws IOException
     */
    public boolean lineExists ( String lineToFind ) throws IOException {
        boolean found = false;
        ReadFile file = new ReadFile( path );
        String[] arrayLines = file.OpenFile();

        for ( String line : arrayLines ) {
            char[] lineCharArray = line.toCharArray();
            int charNumber = 0;
            for ( char character : lineCharArray ) {
                if ( character == '{' ) {
                    break;
                }
                charNumber++ ;
            }
            String gameTitle = line.substring( 0, charNumber );
            if ( lineToFind.trim().toLowerCase().equals( gameTitle.trim().toLowerCase() ) ) {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * Purpose: Returns the line number a given string is on. (gets first occurrence )
     * 
     * @param lineToFind
     * @return
     * @throws IOException
     */
    public int getLineNumber ( String lineToFind ) throws IOException {
        int lineNumber = 1;
        ReadFile file = new ReadFile( path );
        String[] arrayLines = file.OpenFile();

        if ( lineExists( lineToFind ) ) {
            for ( String line : arrayLines ) {
                char[] lineCharArray = line.toCharArray();
                int charNumber = 0;
                for ( char character : lineCharArray ) {
                    if ( character == '{' ) {
                        break;
                    }
                    charNumber++ ;
                }
                String gameTitle = line.substring( 0, charNumber );
                if ( lineToFind.trim().toLowerCase().equals( gameTitle.trim().toLowerCase() ) ) {
                    break;
                }
                lineNumber++ ;
            }
        } else {
            System.out.println( "Could not find a line matching that string." );
            lineNumber = -1;
        }
        return lineNumber;
    }

    /**
     * Purpose: Returns the full line that includes the string given. (gets first occurrance)
     * 
     * @param lineToFind
     * @return
     * @throws IOException
     */
    public String findLine ( String lineToFind ) throws IOException {
        String lineContent = "";
        ReadFile file = new ReadFile( path );
        String[] arrayLines = file.OpenFile();

        if ( lineExists( lineToFind ) ) {
            for ( String line : arrayLines ) {
                char[] lineCharArray = line.toCharArray();
                int charNumber = 0;
                for ( char character : lineCharArray ) {
                    if ( character == '{' ) {
                        break;
                    }
                    charNumber++ ;
                }
                String gameTitle = line.substring( 0, charNumber );
                if ( lineToFind.trim().toLowerCase().equals( gameTitle.trim().toLowerCase() ) ) {
                    lineContent = line;
                    break;
                }
            }
        } else {
            System.out.println( "Could not find a line matching that string." );
        }
        return lineContent;
    }

}
