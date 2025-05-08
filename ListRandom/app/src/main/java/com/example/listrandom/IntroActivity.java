package com.example.listrandom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "IntroPrefs";
    private static final String KEY_INTRO_SHOWN = "introShown";

    private ViewPager2 viewPagerIntro;
    private IntroSliderAdapter introSliderAdapter;
    private LinearLayout layoutDots;
    private Button buttonNext;
    private Button buttonSkip;
    private List<IntroSlide> slides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.getBoolean(KEY_INTRO_SHOWN, false)) {
            launchMainActivity();
            return;
        }

        setContentView(R.layout.activity_intro);

        viewPagerIntro = findViewById(R.id.viewPagerIntro);
        layoutDots = findViewById(R.id.layoutDots);
        buttonNext = findViewById(R.id.buttonNext);
        buttonSkip = findViewById(R.id.buttonSkip);

        setupSlides();
        introSliderAdapter = new IntroSliderAdapter(slides);
        viewPagerIntro.setAdapter(introSliderAdapter);

        setupDots();
        setCurrentDot(0);

        viewPagerIntro.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentDot(position);
                if (position == slides.size() - 1) {
                    buttonNext.setText(getString(R.string.done));
                } else {
                    buttonNext.setText(getString(R.string.next));
                }
            }
        });

        buttonNext.setOnClickListener(v -> {
            if (viewPagerIntro.getCurrentItem() < slides.size() - 1) {
                viewPagerIntro.setCurrentItem(viewPagerIntro.getCurrentItem() + 1);
            } else {
                markIntroAsShownAndProceed();
            }
        });

        buttonSkip.setOnClickListener(v -> markIntroAsShownAndProceed());
    }

    private void setupSlides() {
        slides = new ArrayList<>();
        slides.add(new IntroSlide(
                getString(R.string.slide_1_title),
                getString(R.string.slide_1_desc),
                R.drawable.ic_slide_welcome
        ));
        slides.add(new IntroSlide(
                getString(R.string.slide_2_title),
                getString(R.string.slide_2_desc),
                R.drawable.ic_slide_features
        ));
        slides.add(new IntroSlide(
                getString(R.string.slide_3_title),
                getString(R.string.slide_3_desc),
                R.drawable.ic_slide_get_started
        ));
    }

    private void setupDots() {
        ImageView[] dots = new ImageView[slides.size()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dot_inactive));
            dots[i].setLayoutParams(params);
            layoutDots.addView(dots[i]);
        }
    }

    private void setCurrentDot(int index) {
        int childCount = layoutDots.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutDots.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dot_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dot_inactive));
            }
        }
    }

    private void markIntroAsShownAndProceed() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_INTRO_SHOWN, true);
        editor.apply();
        launchMainActivity();
    }

    private void launchMainActivity() {
        startActivity(new Intent(IntroActivity.this, MainActivity.class));
        finish();
    }
}
