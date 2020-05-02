package com.example.kawalingkungan;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {
    private static String myTag = "InfoGempa";

    static final int POST_PROGRESS=1;

    public static void DownloadFromUrl(String URL, FileOutputStream fos){
        try {
            URL url = new URL(URL);
            long startTime=System.currentTimeMillis();
            Log.d(myTag, "download beginning");

            URLConnection urlConnection=url.openConnection();

            Log.i(myTag, "Opened Connection");
            InputStream is=urlConnection.getInputStream();
            BufferedInputStream bis=new BufferedInputStream(is);
            Log.i(myTag, "Got InputStream and BufferedInputStream");

            BufferedOutputStream bos=new BufferedOutputStream(fos);
            Log.i(myTag, "Got FOS and BOS");

            byte data[]=new byte[1024];

            int count;
            while ((count=bis.read(data))!= -1){
                bos.write(data, 0, count);
            }
            bos.flush();
            bos.close();

            Log.d(myTag, "download ready in "+
                    ((System.currentTimeMillis()-startTime)) +
                    "milisec");
        }catch (IOException e) {
            Log.d(myTag, "Error: " +e);
        }
    }
}
