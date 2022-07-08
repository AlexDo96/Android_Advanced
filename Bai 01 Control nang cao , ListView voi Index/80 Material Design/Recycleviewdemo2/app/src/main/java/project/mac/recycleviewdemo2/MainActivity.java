package project.mac.recycleviewdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    EditText et;
    Button bt;
    MyAdapter adapter;
    ArrayList<Data> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.recycler);
        et=(EditText)findViewById(R.id.editText);
        bt=(Button)findViewById(R.id.button);

        ds=new ArrayList<Data>();
        ds.add(new Data("hello"));
        ds.add(new Data("teo"));
        LinearLayoutManager lmanager=new LinearLayoutManager(this);
        adapter=new MyAdapter(ds);
        rv.setLayoutManager(lmanager);
        rv.setAdapter(adapter);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data d=new Data(et.getText().toString());
                adapter.addItem(ds.size(),d);
            }
        });
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
