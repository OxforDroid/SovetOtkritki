package ru.keke.sovetotkritki;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager pager;
    private ImageView Imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pager = (ViewPager) findViewById(R.id.view_pager);

        final Integer[] imageUrls={
                R.drawable.pozdr1 ,
                R.drawable.pozdr2 ,
                R.drawable.pozdr3 ,
                R.drawable.pozdr4 ,
                R.drawable.pozdr5 ,
                R.drawable.pozdr6 ,
                R.drawable.pozdr7 ,
                R.drawable.pozdr9,
        };
        pager.setAdapter(new MainActivity.MyAdapter(imageUrls));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView Imgv = (ImageView)pager.findViewWithTag(pager.getCurrentItem());/////////////////
                Imgv.setDrawingCacheEnabled(true);////
                Bitmap icon = Imgv.getDrawingCache();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
                try {
                    file.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("image/jpeg");
                Uri uri = Uri.parse("file:///sdcard/temporary_file.jpg");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
                startActivity(shareIntent);
            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_1mai) {
            Intent intent = new Intent(MainActivity.this, Act1mai.class);
            startActivity(intent);
        } else if (id == R.id.nav_1sent) {
            Intent intent = new Intent(MainActivity.this, Act1sent.class);
            startActivity(intent);
        } else if (id == R.id.nav_8mart) {
            Intent intent = new Intent(MainActivity.this, Act8mart.class);
            startActivity(intent);

        } else if (id == R.id.nav_dr) {
            Intent intent = new Intent(MainActivity.this, Act_dr.class);
            startActivity(intent);
        } else if (id == R.id.nav_ng) {
            Intent intent = new Intent(MainActivity.this, Act_ng.class);
            startActivity(intent);
        } else if (id == R.id.nav_23fev) {
            Intent intent = new Intent(MainActivity.this, Act23f.class);
            startActivity(intent);
        } else if (id == R.id.nav_9mai) {
            Intent intent = new Intent(MainActivity.this, Act9mai.class);
            startActivity(intent);

        } else if (id == R.id.info) {
            Intent intent = new Intent(MainActivity.this, Razrab.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private Integer[] fimg;

        public MyAdapter(Integer[] fimg ) {
            this.fimg=fimg;
            inflater = getLayoutInflater();
        }

        @Override
        public Object instantiateItem(View collection,  int position  ) {
            //View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)


            final View imageLayout = inflater.inflate(R.layout.pager_item, null);

            Imgv =  (ImageView)imageLayout.findViewById(R.id.imageView);

            Imgv.setBackgroundResource(fimg[position]);////
            Imgv.setTag(position);////////////////////////////

            ((ViewPager) collection).addView(imageLayout ,0);
            return imageLayout;

        }
        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }
        @Override
        public int getCount() {
            return fimg.length;
        }
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            // TODO Auto-generated method stub
            return view==((View)object);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader loader) {}

        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void startUpdate(View container) {
        }
        @Override
        public void finishUpdate(View container) {

        }
    }
}


