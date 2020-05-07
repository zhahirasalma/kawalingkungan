package com.example.kawalingkungan;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InfoXmlPullParser {
    static final String KEY_SITE = "gempa";
    static final String KEY_DATE = "tanggal";
    static final String KEY_TIME = "jam";
    static final String KEY_MAG = "magnitude";
    static final String KEY_LIN = "lintang";
    static final String KEY_BU = "bujur";
    static final String KEY_KED = "kedalaman";
    static final String KEY_LOC = "wilayah";
    static final String KEY_SYM = "_symbol";

    public static List<ModelInfo> getStackFromFile(Context context){
        List<ModelInfo> infoList;
        infoList=new ArrayList<ModelInfo>();

        ModelInfo curModelInfo=null;
        String curText="";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            FileInputStream fis= context.openFileInput("GempaTerkini.xml");
            BufferedReader reader=new BufferedReader(new InputStreamReader(fis));

            xpp.setInput(reader);

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagname=xpp.getName();

                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if(tagname.equalsIgnoreCase(KEY_SITE)){
                            curModelInfo=new ModelInfo();
                        }break;
                    case XmlPullParser.TEXT:
                        curText=xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(tagname.equalsIgnoreCase(KEY_SITE)){
                            infoList.add(curModelInfo);
                        }else if(tagname.equalsIgnoreCase(KEY_DATE)){
                            curModelInfo.setTanggal(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_TIME)){
                            curModelInfo.setJam(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_MAG)){
                            curModelInfo.setMagnitude(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_LIN)){
                            curModelInfo.setLintang(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_BU)){
                            curModelInfo.setBujur(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_KED)){
                            curModelInfo.setKedalaman(curText);
                        }else if(tagname.equalsIgnoreCase(KEY_LOC)){
                            curModelInfo.setWilayah(curText);
                        }
//                        else if(tagname.equalsIgnoreCase(KEY_SYM)){
//                            curModelInfo.set_symbol(curText);
//                        }
                        break;
                    default:
                        break;
                }
                eventType=xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return infoList;
    }
}
