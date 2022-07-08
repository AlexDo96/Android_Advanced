package project.mac.recycleviewdemo1;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Logo> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.recycleview);
        ds=new ArrayList<Logo>();
        taodanhsach();
        MyAdapter adapter=new MyAdapter(MainActivity.this,ds);
        //LinearLayoutManager lmanager=new LinearLayoutManager(MainActivity.this);
        GridLayoutManager lmanager=new GridLayoutManager(MainActivity.this,2);
        rv.setLayoutManager(lmanager);
        rv.setAdapter(adapter);

    }

    public void taodanhsach()
    {
        ds.add(new Logo("Android",R.drawable.android));
        ds.add(new Logo("apple",R.drawable.apple));
        ds.add(new Logo("chrome",R.drawable.chrome));
        ds.add(new Logo("dell",R.drawable.dell));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
