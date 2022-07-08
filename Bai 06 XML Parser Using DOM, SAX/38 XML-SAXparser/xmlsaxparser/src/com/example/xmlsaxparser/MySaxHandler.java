package com.example.xmlsaxparser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class MySaxHandler extends DefaultHandler{

	ArrayList<Item> items;
	Item item_tam;
	String chuoi_tam;
	
	public MySaxHandler()
	{
		items=new ArrayList<Item>();
	}
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		chuoi_tam=new String(ch,start,length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equalsIgnoreCase("item"))
			items.add(item_tam);
		else if(qName.equalsIgnoreCase("id"))
			item_tam.id=chuoi_tam;
		else if(qName.equalsIgnoreCase("name"))
			item_tam.name=chuoi_tam;
		else if(qName.equalsIgnoreCase("cost"))
			item_tam.cost=chuoi_tam;
		else if(qName.equalsIgnoreCase("description"))
			item_tam.description=chuoi_tam;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		chuoi_tam="";
		if(qName.equalsIgnoreCase("item"))
			item_tam=new Item();
	}

}
