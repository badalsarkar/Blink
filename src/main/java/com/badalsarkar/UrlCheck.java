package com.badalsarkar;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;


public class UrlCheck {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        try{
            File file = new File("/home/badal/Documents/codeProject/urlchecker/urls.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                urls.add(sc.nextLine());
            }
            long startTime = System.nanoTime();
            //List<Integer> status = getStatus(urls);
            //color
            int size = urls.size();
            for(int i=0; i<size; i++){
                int status= makeRequest(urls.get(i));
                AnsiFormat greenColor = new AnsiFormat(Attribute.GREEN_TEXT());
                AnsiFormat redColor = new AnsiFormat(Attribute.RED_TEXT());
                if(status==200){
                    System.out.println(greenColor.format("Status\t["+status+ "]\t"+urls.get(i)));
                }
                else if(status==400 || status == 404){
                    System.out.println(redColor.format("Status\t["+status+ "]\t"+urls.get(i)));
                }
                else{
                    System.out.println("Status\t["+status+ "]\t"+urls.get(i));
                }

            }
            System.out.println(ANSI_RESET);
            long endTime = System.nanoTime();
            int totalTime = (int)((endTime - startTime)/1000000000L);
            System.out.printf("Total time taken: %d second\n",totalTime );
            sc.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Sorry the file is not found in the location");
            System.exit(0);
        }
    }


    private static int makeRequest(String url){
        int response = 0; 
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            response= connection.getResponseCode();
            while (response==HttpURLConnection.HTTP_MOVED_PERM || response == HttpURLConnection.HTTP_MOVED_TEMP){
                response = makeRequest(connection.getHeaderField("Location"));
            }
        }
        catch(SocketTimeoutException ex){
            return -2;
        }
        catch(Exception ex){
            return -1;
        }
        return response;
    }
}
