package com.railindia.indianrailway;
//com.railindia.indianrailway

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity {


    ImageSlider imageSlider;
    ExpandableHeightGridView mainGrid;
    Intent targetActivity;
    public static StartAppAd startAppAd;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startAppAd = new StartAppAd(this);
        dialog = new ProgressDialog(Home.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        loadInterstitialAd(dialog);

        imageSlider = findViewById(R.id.image_slider);
        mainGrid = findViewById(R.id.mainGrid);
        createSlider();
        MakeVideoList.createMyAlbums();

        MyAdapter adapter = new MyAdapter(mHashMap -> {

            startAppAd.showAd();
            loadInterstitialAd();
            String catName = mHashMap.get("category_name");
            String url = mHashMap.get("url");


            if (url != null && url.length() > 5) {
                WebBrowser.WEBSITE_LINK = url;
                WebBrowser.WEBSITE_TITLE = catName;
                targetActivity = new Intent(Home.this, WebBrowser.class);
                startActivity(targetActivity);
            }

        });
        mainGrid.setExpanded(true);
        mainGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            askNotificationPermission();
        }

    }


    private void createSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();
        //imageList.add(new SlideModel(R.drawable.slide_1, null));
        imageList.add(new SlideModel(R.drawable.ticket, "Hello from Jubayer", null));
        imageList.add(new SlideModel(R.drawable.ticket, "Never Give Up", null));
        imageList.add(new SlideModel(R.drawable.booking, "Unlimited Funny Videos", null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);


        /*
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {

                if (position==0){
                    Toast.makeText(getBaseContext(), "Image 1: Do something", Toast.LENGTH_SHORT).show();
                }

                if (position==1){
                    Toast.makeText(getBaseContext(), "Image 2: Do something", Toast.LENGTH_SHORT).show();
                }
            }
        });

         */


    }

    public interface Onclick {
        void onclick(HashMap<String, String> mHashMap);
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        Onclick onclick;


        public MyAdapter(Onclick click) {
            this.inflater = (LayoutInflater) Home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.onclick = click;
        }

        @Override
        public int getCount() {
            return MakeVideoList.catArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);


            ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            LinearLayout layItem = convertView.findViewById(R.id.layItem);

            HashMap<String, String> mHashMap = MakeVideoList.catArrayList.get(position);
            String catName = mHashMap.get("category_name");
            String img = mHashMap.get("img");

            if (tvTitle != null) tvTitle.setText(catName);
            if (imgIcon != null && img != null) {
                int drawable = Integer.parseInt(img);
                imgIcon.setImageResource(drawable);
            }

            Animation animation = AnimationUtils.loadAnimation(Home.this, R.anim.anim_grid);
            animation.setStartOffset(position * 300);
            convertView.startAnimation(animation);


            if (layItem != null) {

                layItem.setOnClickListener(v -> onclick.onclick(mHashMap));
            }


            return convertView;
        }
    }


    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            Toast.makeText(getBaseContext(), "Press again to exit!",
                    Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();


    }

    public static void loadInterstitialAd(ProgressDialog dialog) {
        dialog.show();
        startAppAd.loadAd(StartAppAd.AdMode.AUTOMATIC, new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull Ad ad) {
                dialog.dismiss();
                Log.d("MainActivity", "Interstitial ad received");
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                dialog.dismiss();
                Log.d("MainActivity", "Failed to receive ad");
            }
        });
    }
    public static void loadInterstitialAd() {
        startAppAd.loadAd(StartAppAd.AdMode.AUTOMATIC, new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull Ad ad) {
                Log.d("MainActivity", "Interstitial ad received");
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                Log.d("MainActivity", "Failed to receive ad");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load ad again when activity resumes
        loadInterstitialAd(dialog);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause ad when activity pauses
        startAppAd.onPause();
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void askNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }
}