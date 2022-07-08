package com.example.docsach;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	private ArrayList<RowData> contentDetails;
	public static final String BOOK_NAME = "con_gai_phat.epub";
	ArrayList<String> simpletitle=new ArrayList<String>();
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView)findViewById(R.id.listView1);
		contentDetails = new ArrayList<RowData>();
		AssetManager assetManager = getAssets();
		try {
			InputStream epubInputStream = assetManager.open(BOOK_NAME);
			Book book = (new EpubReader()).readEpub(epubInputStream);
			logContentsTable(book.getTableOfContents().getTocReferences(), 0);
		} catch (IOException e) {
			Log.e("epublib", e.getMessage());
		}
		for(int i=0;i<contentDetails.size();i++)
		{
			simpletitle.add(contentDetails.get(i).getTitle().trim());
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
				MainActivity.this,
				android.R.layout.simple_list_item_1,
				simpletitle);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				RowData rowData=contentDetails.get(position);
				try {
					String noidungchuong=new String(rowData.getResource().getData());
					Intent i=new Intent(MainActivity.this, ChuongActivity.class);
					i.putExtra("display", noidungchuong);
					startActivity(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	private void logContentsTable(List<TOCReference> tocReferences, int depth) {
		if (tocReferences == null) {
			return;
		}
		for (TOCReference tocReference : tocReferences) {
			StringBuilder tocString = new StringBuilder();
			for (int i = 0; i < depth; i++) {
				tocString.append("\t");
			}
			tocString.append(tocReference.getTitle());
			RowData row = new RowData();
			row.setTitle(tocString.toString());
			row.setResource(tocReference.getResource());
			contentDetails.add(row);
			logContentsTable(tocReference.getChildren(), depth + 1);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
