package com.example.administrator.xmlparsersaxdemo2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/22/2017.
 */
public class MySaxHandler extends DefaultHandler {

    ArrayList<Item> ds=new ArrayList<Item>();
    Item item_tam;
    String chuoi_tam;
    public  ArrayList<Item> getItems()
    {
        return ds;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        chuoi_tam=new String(ch,start,length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        chuoi_tam="";
        if(qName.equalsIgnoreCase("item"))
            item_tam=new Item();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(qName.equalsIgnoreCase("id"))
            item_tam.id=chuoi_tam;
        if(qName.equalsIgnoreCase("name"))
            item_tam.name=chuoi_tam;
        if(qName.equalsIgnoreCase("cost"))
            item_tam.cost=chuoi_tam;
        if(qName.equalsIgnoreCase("description"))
            item_tam.description=chuoi_tam;


        if(qName.equalsIgnoreCase("item"))
            ds.add(item_tam);
    }
}
