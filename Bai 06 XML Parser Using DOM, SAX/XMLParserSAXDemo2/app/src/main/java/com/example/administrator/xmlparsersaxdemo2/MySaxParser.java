package com.example.administrator.xmlparsersaxdemo2;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 4/22/2017.
 */
public class MySaxParser {

    public ArrayList<Item> xmlParser(InputStream is)
    {
        ArrayList<Item> ds=null;
        try{
            XMLReader xmlReader= SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            MySaxHandler saxHandler=new MySaxHandler();
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            ds=saxHandler.getItems();
        }
        catch (Exception ex)
        {

        }

        return ds;
    }
}
