package com.example.jaryia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class About_app extends AppCompatActivity implements View.OnClickListener {
    ImageView whats, inst, share, tele;
    TextView jariya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        whats = findViewById(R.id.imageView2);
        inst = findViewById(R.id.imageView6);
        share = findViewById(R.id.imageView5);
        tele = findViewById(R.id.imageView3);

        whats.setOnClickListener(this);
        inst.setOnClickListener(this);
        share.setOnClickListener(this);
        tele.setOnClickListener(this);

        jariya = findViewById(R.id.text_number_about);
        jariya.setMovementMethod(LinkMovementMethod.getInstance());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView6:
                openApp("https://www.instagram.com/karasuu_reklam/");
                break;
            case R.id.imageView3:
                openApp("https://t.me/DilyaE");
                break;
            case R.id.imageView2:
                openApp("https://wa.link/hnzicj");
                break;
            case R.id.imageView5:
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

    private void openApp(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }
}