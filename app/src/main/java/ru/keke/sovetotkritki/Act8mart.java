package ru.keke.sovetotkritki;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Act8mart extends AppCompatActivity {

    private ViewPager pager;
    private ImageView Imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act8mart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pager = (ViewPager) findViewById(R.id.view_pager);

        final Integer[] imageUrls={
                R.drawable.s8m1 ,
                R.drawable.s8m2 ,
                R.drawable.s8m3 ,
                R.drawable.s8m4 ,
                R.drawable.s8m5 ,
                R.drawable.s8m6 ,
                R.drawable.s8m7 ,
                R.drawable.s8m9,
                R.drawable.s8m10,
                R.drawable.s8m11,
                R.drawable.s8m12,
                R.drawable.s8m13,
                R.drawable.s8m14,
                R.drawable.s8m15,
                R.drawable.s8m16,
        };
        pager.setAdapter(new Act8mart.MyAdapter(imageUrls));


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
