package com.example.administrator.demo1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.woozzu.android.util.StringMatcher;
import com.woozzu.android.widget.IndexableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mItems;
    private IndexableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (IndexableListView) findViewById(R.id.listview);


        mItems = new ArrayList<String>();
        mItems.add("Chao cac ban");
        mItems.add("nguyen van teo");
        mItems.add("Le Thi Ti");
        mItems.add("Death Comes to Pemberley");
        mItems.add("Diary of a Wimpy Kid 6: Cabin Fever");
        mItems.add("Steve Jobs");
        mItems.add("Inheritance (The Inheritance Cycle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Explosive Eighteen: A Stephanie Plum Novel");
        mItems.add("Catching Fire (The Second Book of the Hunger Games)");
        mItems.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        mItems.add("Death Comes to Pemberley");

        Collections.sort(mItems);
        ContentAdapter adapter = new ContentAdapter(this,
                android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);
    }

    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

        private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public ContentAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        public int getPositionForSection(int section) {
            // If there is no item for current section, previous section will be selected
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {
                        // For numeric section
                        for (int k = 0; k <= 9; k++) {
                            if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
                                return j;
                        }
                    } else {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
            return 0;
        }

        public int getSectionForPosition(int position) {
            return 0;
        }

        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i = 0; i < mSections.length(); i++)
                sections[i] = String.valueOf(mSections.charAt(i));
            return sections;
        }
    }
}
