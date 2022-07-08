package luffy.khoa.anh.layouttonghop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuly_onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.bt_1:
                intent = new Intent(MainActivity.this, Loai1Activity.class);
                break;
            case R.id.bt_2:
                intent = new Intent(MainActivity.this, Loai2Activity.class);
                break;
            case R.id.bt_3:
                intent = new Intent(MainActivity.this, Loai3Activity.class);
                break;
            case R.id.bt_4:
                intent = new Intent(MainActivity.this, Loai4Activity.class);
                break;
            case R.id.bt_5:
                intent = new Intent(MainActivity.this, Loai5Activity.class);
                break;
            default:
                intent = new Intent(MainActivity.this, Loai6Activity.class);
                break;
        }
        startActivity(intent);
    }
}
