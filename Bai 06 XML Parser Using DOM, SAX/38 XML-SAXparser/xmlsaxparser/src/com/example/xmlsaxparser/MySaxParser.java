package com.example.xmlsaxparser;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

public class MySaxParser {

	public List<Item> xmlParser(InputStream is)
	{
		List<Item> items=null;
		try{
			//tao xmlreader tu xmlparser
			XMLReader xmlreader=SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			//tao saxhandler
			MySaxHandler saxhandler=new MySaxHandler();
			//luu handler vao xmlreader
			xmlreader.setContentHandler(saxhandler);
			xmlreader.parse(new InputSource(is));
			//lay danh sach cac item bo vao items
			items=saxhandler.getItems();
		}
		catch(Exception e)
		{
			Log.d("loi","lay khong duoc "+ e.toString());
		}
		return items;
	}
}
