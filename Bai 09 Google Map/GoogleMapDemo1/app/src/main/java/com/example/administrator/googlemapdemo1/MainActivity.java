package com.example.administrator.googlemapdemo1;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GoogleMap map;
    LatLng toado = new LatLng(10.815887,106.651884);
    ArrayList<Marker> markers=new ArrayList<Marker>();
    Dialog dialog;
    Marker mym;
    Marker marker_b;
    EditText et_location;
    Button bt_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_location=(EditText)findViewById(R.id.et_location);
        bt_find=(Button)findViewById(R.id.btn_find);

        map= ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        UiSettings uisetting=map.getUiSettings();

        uisetting.setCompassEnabled(true);
        uisetting.setZoomControlsEnabled(true);
        uisetting.setScrollGesturesEnabled(true);
        uisetting.setTiltGesturesEnabled(true);
        uisetting.setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);

/*        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, true);
        Location location;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }*/




        mym = map.addMarker(
                new MarkerOptions()
                        .position(toado)
                        .title("tieu de")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_ROSE)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(toado, 15));

        //cham vao ban do, ve marker
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Marker mym2 = map.addMarker(
                        new MarkerOptions()
                                .position(arg0)
                                .title("dia diem")
                                .snippet(arg0.latitude + "," + arg0.longitude)
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_ROSE)));
                markers.add(mym2);
            }
        });


        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public View getInfoContents(Marker arg0) {
                // TODO Auto-generated method stub
                View info = getLayoutInflater().inflate(R.layout.markerinfo, null);
                TextView tv1 = ((TextView) info.findViewById(R.id.textView1));
                tv1.setText(arg0.getTitle());
                TextView tv2 = ((TextView) info.findViewById(R.id.textView2));
                tv2.setText(arg0.getSnippet());
                return info;
            }
        });


        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
                marker_b = arg0; //gan truoc de can thi ve duong di
                dialog = new Dialog(MainActivity.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
// layout to display
                dialog.setContentView(R.layout.markerdetail);
                Button b = (Button) dialog.findViewById(R.id.button1);

                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                        veduongdixml();


                        Toast.makeText(MainActivity.this, "nhan nut", Toast.LENGTH_SHORT).show();
                    }
                });
                // set color transpartent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        bt_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geocoder geocoder=new Geocoder(MainActivity.this);
                List<Address> ds_diachi=null;

                try {
                    ds_diachi=geocoder.getFromLocationName(et_location.getText().toString(), 30);
                    Toast.makeText(getApplicationContext(), "co:"+ds_diachi.size(), Toast.LENGTH_LONG).show();
                    for(int i=0;i<ds_diachi.size();i++)
                    {
                        Address dc=(Address)ds_diachi.get(i);
                        LatLng vt = new LatLng(dc.getLatitude(),dc.getLongitude());
                        Marker mar = map.addMarker(
                                new MarkerOptions()
                                        .position(vt)
                                        .title(dc.getLocality())
                                        .snippet(dc.getFeatureName())
                                        .icon(BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_ROSE)));
                        markers.add(mar);//them vao list de de xoa

                        ///di chuyen camera chua toan bo marker
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        for(Marker m : markers) {
                            builder.include(m.getPosition());
                        }
                        LatLngBounds bounds = builder.build();
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 5);
                        //map.moveCamera(cu);
                        map.animateCamera(cu);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "loi roi", Toast.LENGTH_LONG).show();
                    Log.d("err", e.toString());
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.normal:
                map.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.hybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.satellite:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.terrain:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.clearmarker:
                //map.clear();
                for (Marker marker: markers) {
                    marker.remove();
                }
                markers.clear();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    void veduongdixml()
    {

        Toast.makeText(MainActivity.this, "nhan nut",Toast.LENGTH_SHORT).show();
        veduongdixml a=new veduongdixml();
        a.execute(mym.getPosition().latitude,
                mym.getPosition().longitude,
                marker_b.getPosition().latitude,
                marker_b.getPosition().longitude);

    }

    public class veduongdixml extends AsyncTask<Double, Void, Void>
    {
        ArrayList<LatLng> mangtoado;
        @Override
        protected Void doInBackground(Double... params) {
            // TODO Auto-generated method stub
            //Log.d("json", params[0]+","+params[1]+"..."+params[2]+","+params[3]);
            Direction md = new Direction();
            LatLng x=new LatLng(params[0], params[1]);
            LatLng y=new LatLng(params[2],params[3]);
            Document doc = md.getDocument(x, y, Direction.MODE_DRIVING);
            mangtoado = md.getDirection(doc);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED); // Màu và độ rộng

            for(int i = 0 ; i < mangtoado.size() ; i++) {
                rectLine.add(mangtoado.get(i));
            }
            map.addPolyline(rectLine);
        }
    }
}
