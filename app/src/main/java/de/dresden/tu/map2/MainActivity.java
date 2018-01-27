package de.dresden.tu.map2;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import de.dresden.tu.map2.fragments.AlteMensaFragment;
import de.dresden.tu.map2.fragments.InfoFragment;
import de.dresden.tu.map2.fragments.MapFragment;
import de.dresden.tu.map2.fragments.MuenchenFragment;
import de.dresden.tu.map2.fragments.NeueMensaFragment;
import de.dresden.tu.map2.fragments.NuerenbergFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
    private GoogleMap mMap;
    private ArrayList<MarkerOptions>  mPositions = new ArrayList<>();
    private ArrayList<Marker> markers = new ArrayList<>();
    private FloatingActionButton floatingActionButton1,floatingActionButton2,floatingActionButton3,floatingActionButton4;
    private MuenchenFragment muenchenFragment;
    private NuerenbergFragment nuerenbergFragment;
    private AlteMensaFragment alteMensaFragment;
    private NeueMensaFragment neueMensaFragment;
    private InfoFragment infoFragment;





    @SuppressWarnings("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton1 =  findViewById(R.id.fab);
        floatingActionButton2 =  findViewById(R.id.fab2);
        floatingActionButton3 =  findViewById(R.id.fab3);
        floatingActionButton4 =  findViewById(R.id.fab4);

        muenchenFragment = new MuenchenFragment();
        nuerenbergFragment = new NuerenbergFragment();
        alteMensaFragment = new AlteMensaFragment();
        neueMensaFragment = new NeueMensaFragment();
        infoFragment = new InfoFragment();

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (floatingActionButton2.getVisibility()==View.INVISIBLE){
                    floatingActionButton2.setVisibility(View.VISIBLE);
                    floatingActionButton3.setVisibility(View.VISIBLE);
                    floatingActionButton4.setVisibility(View.VISIBLE);
                }else{
                    floatingActionButton2.setVisibility(View.INVISIBLE);
                    floatingActionButton3.setVisibility(View.INVISIBLE);
                    floatingActionButton4.setVisibility(View.INVISIBLE);
                }

            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Die Entfernung ist jetzt auf 10 Meter gesetzt",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();
                floatingActionButton2.setVisibility(View.INVISIBLE);
                floatingActionButton3.setVisibility(View.INVISIBLE);
                floatingActionButton4.setVisibility(View.INVISIBLE);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Die Entfernung ist jetzt auf 30 Meter gesetzt",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();
                floatingActionButton2.setVisibility(View.INVISIBLE);
                floatingActionButton3.setVisibility(View.INVISIBLE);
                floatingActionButton4.setVisibility(View.INVISIBLE);
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Die Entfernung ist jetzt auf 50 Meter gesetzt",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new MapFragment()).commit();

        supportMapFragment.getMapAsync(this);

        if ( ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }

        }
        mPositions.add(new MarkerOptions().position(new LatLng(51.027025, 13.726465)).title("Alte Mensa"));
        mPositions.add(new MarkerOptions().position(new LatLng(51.029000, 13.731460)).title("Neue Mensa"));
        mPositions.add(new MarkerOptions().position(new LatLng(51.025534, 13.723273)).title("Fakultät Informatik"));
        mPositions.add(new MarkerOptions().position(new LatLng(51.031778, 13.727063)).title("Nürenberger Platz"));
        mPositions.add(new MarkerOptions().position(new LatLng(51.029871, 13.721756)).title("Münchener Platz"));



    }

    @SuppressWarnings("MissingPermission")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();

        int id = item.getItemId();

        if (supportMapFragment.isAdded()) {
            sFm.beginTransaction().hide(supportMapFragment).commit();
        }

        if (id == R.id.nav_camera) {
            floatingActionButton1.setVisibility(View.VISIBLE);
            if (!supportMapFragment.isAdded()){
                sFm.beginTransaction().add(R.id.map, supportMapFragment).commit();
            } else{
                sFm.beginTransaction().show(supportMapFragment).commit();
            }
        } else if (id == R.id.alte_Mensa) {

            floatingActionButton1.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame, alteMensaFragment).commit();



        } else if (id == R.id.neue_Mensa) {

            floatingActionButton1.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame,neueMensaFragment).commit();

        } else if (id == R.id.nürenberg) {

            floatingActionButton1.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame, nuerenbergFragment).commit();

        } else if (id == R.id.münchen) {

            fm.beginTransaction().replace(R.id.content_frame, muenchenFragment).commit();
            floatingActionButton1.setVisibility(View.INVISIBLE);

        } else if (id == R.id.adb) {

            floatingActionButton1.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame,infoFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera

        for (MarkerOptions m : mPositions){
            mMap.addMarker(m);
        }
        mMap.setOnMarkerClickListener(this);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(alteMensa));


    }
    @Override
    public boolean onMarkerClick(final Marker marker){
        FragmentManager fm = getFragmentManager();
        String title = marker.getTitle();

        if (title.equals("Neue Mensa")){
            startActivity(new Intent(this,NeueMensaActivity.class));


        }else if(title.equals("Alte Mensa")){
            startActivity(new Intent(this,AlteMensaActivity.class));
        }else if(title.equals("Münchener Platz")){
            startActivity(new Intent(this,MuenchenerPlatzActivity.class));
        }

        return false;
    }
}
