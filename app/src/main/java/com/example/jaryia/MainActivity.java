package com.example.jaryia;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabPagerAdapter tabPagerAdapter;
    TextView jariya, thisPage, infoPage;
    ImageView telegram, instagram, whatsapp, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (internetChecker()) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            tabLayout = findViewById(R.id.tab);
            drawerLayout = findViewById(R.id.drawer);
            navigationView = findViewById(R.id.nav_view);
            viewPager = findViewById(R.id.viewpager);
            tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(tabPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();

            View view = navigationView.inflateHeaderView(R.layout.navigation_layout);
            actionsInNav(view);

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        } else dialogPage();
    }

    private void actionsInNav(View view) {
        jariya = view.findViewById(R.id.text_number);
        thisPage = view.findViewById(R.id.jaryia);
        infoPage = view.findViewById(R.id.info);
        telegram = view.findViewById(R.id.telegram);
        whatsapp = view.findViewById(R.id.whatsapp);
        instagram = view.findViewById(R.id.instagram);
        share = view.findViewById(R.id.share);

        thisPage.setOnClickListener(this);
        infoPage.setOnClickListener(this);
        telegram.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        instagram.setOnClickListener(this);
        share.setOnClickListener(this);

        jariya.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void dialogPage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Интернетиниз очук!")
                .setCancelable(false)
                .setPositiveButton("Макул", (dialog, id) -> {
                    finish();
                    startActivity(getIntent());
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean internetChecker() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    private void setupTabIcons() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_baseline_home_24);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_baseline_corporate_fare_24);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_baseline_directions_car_24);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_baseline_grass_24);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(R.drawable.ic_baseline_shopping_basket_24);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(R.drawable.ic_baseline_work_24);
        Objects.requireNonNull(tabLayout.getTabAt(6)).setIcon(R.drawable.ic_baseline_desktop_technical_24);
        Objects.requireNonNull(tabLayout.getTabAt(7)).setIcon(R.drawable.ic_baseline_directions_bus_24);
    }

    private void openApp(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),"Таппадым", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            Toast.makeText(this, "Жаныланды", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jaryia:
                finish();
                startActivity(getIntent());
                break;
            case R.id.info:
                startActivity(new Intent(getApplicationContext(), About_app.class));
                break;
            case R.id.instagram:
                openApp("https://www.instagram.com/karasuu_reklam/");
                break;
            case R.id.telegram:
                openApp("https://t.me/DilyaE");
                break;
            case R.id.whatsapp:
                openApp("https://wa.link/hnzicj");
                break;
            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "\nПриложения\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Рекламы"));
                break;
        }
    }
}