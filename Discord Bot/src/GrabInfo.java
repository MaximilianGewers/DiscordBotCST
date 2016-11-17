

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ConnerChubey.WriteToFile.ReadFile;
import com.ConnerChubey.WriteToFile.WriteFile;

public class GrabInfo {

    public static void WriteToFile ( String title, String line ) throws Exception {
        String path = "E:/Game_List.txt"; // path to find file at
        char TPseparator = '{'; // Separator used for searching functions regarding to Title or Price
        char IDseparator = '}'; // Separator used for searching functions regarding to game ID or price
        ReadFile file = new ReadFile( path ); // Opens the file and reads it
        WriteFile data = new WriteFile( path, true ); // creates a WriteToFile variable

        if ( !file.exists() ) {
            file.createFile();
        }
        try {
            String[] arrayLines = file.openFile(); // Adds the lines to an array
            if ( arrayLines.length > 0 ) {
                if ( file.lineExists( title, TPseparator ) ) {
                    arrayLines[file.getLineNumber( title, TPseparator ) - 1] = line;
                    data.wipeFile(); // Clears all contents in the file (needed for changing particular lines)
                    data.writeArrayToFile( arrayLines ); // Writes array to the file
                } else {
                    data.writeToFile( line );
                }
            } else {
                data.writeToFile( line );
            }

        } catch ( IOException e ) {
            System.out.println( e.getMessage() + "\nCould not write to file." );
        }
    }

    public static String GetList ( String url, String gameId ) throws Exception {
        // Connection.Response form = Jsoup.connect(checkUrl) .method(Connection.Method.GET).execute();

        String titlePrice = null;
        Document document = Jsoup.connect( url )
                .userAgent(
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36" )
                .get();
        Elements titles = null, prices = null;
        titles = document.select( ".container h1" );
        prices = document.select( "tr [data-cc = ca] ~ td" );
        int i = 0;
        String gameTitle = null;
        String botText = null;
        for ( Element title : titles ) {
            botText = title.text() + ": " + prices.get( i ).text();
            System.out.println( botText );
            titlePrice = title.text() + " { " + prices.get( i ).text() + " } " + gameId;
            gameTitle = title.text();
            i++ ;
        }
        WriteToFile( gameTitle, titlePrice );
        System.out.println( titlePrice );
        return botText;
    }

    public static void main ( String[] args ) throws Exception {
       /* Scanner scanner = new Scanner( System.in );
        // System.setProperty("javax.net.ssl.trustStore", "C:/users/conne/desktop/ssl323671.cloudflaressl.com.jks");
        String url = "https://steamdb.info/app/";
        System.out.print( "Enter a steam game ID number: " );
        String input = scanner.nextLine();
        url += input + "/";
        GetList( url, input );*/
        /*
         * Elements obj =
         * Jsoup.connect("http://store.steampowered.com/app/289070/").get().select(".game_purchase_action_bg"); for
         * (Element objs : obj){ //checks if text contains $ if(objs.text().contains("$")){ // prints lines that contain
         * $ System.out.println(objs.text()); } }
         * 
         * 
         * snr:1_agecheck_agecheck__age-gate ageDay:30 ageMonth:November ageYear:1950
         * 
         * 
         * 
         * 
         */
    }

}
