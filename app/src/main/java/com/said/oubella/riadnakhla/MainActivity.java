package com.said.oubella.riadnakhla;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.said.oubella.riadnakhla.adapter.PhotosAdapter;
import com.said.oubella.riadnakhla.data.DataSource;
import com.said.oubella.riadnakhla.prefs.AppThemePrefs;

public class MainActivity extends AppCompatActivity {

    private static final long DELAY_DURATION = 5000L;

    private ViewPager2 viewPager;
    private View[] dots = new View[3];

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % DataSource.IMAGES_IDS.length);
            handler.postDelayed(this, DELAY_DURATION);
        }
    };

    private OnPageChangeCallback pagerCallback = new OnPageChangeCallback() {

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager2.SCROLL_STATE_DRAGGING:
                case ViewPager2.SCROLL_STATE_SETTLING:
                    stopAutoScrolling();
                    break;
                case ViewPager2.SCROLL_STATE_IDLE:
                    startAutoScrolling();
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < dots.length; i++) {
                dots[i].setBackgroundResource(
                        position == i ? R.drawable.selected_dot : R.drawable.unselected_dot
                );
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dots[0] = findViewById(R.id.dot1);
        dots[1] = findViewById(R.id.dot2);
        dots[2] = findViewById(R.id.dot3);

        AppCompatImageView themeToggle = findViewById(R.id.theme_toggle);

        themeToggle.setImageResource(
                AppThemePrefs.getInstance(this).isDark() ?
                        R.drawable.ic_bright_theme_24dp :
                        R.drawable.ic_dark_theme_24dp
        );

        themeToggle.setOnClickListener((view) -> {
            AppThemePrefs.getInstance(this).toggleTheme();
            AppThemePrefs.applyTheme(this);
        });

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(new PhotosAdapter());
    }

    private void startAutoScrolling() {
        handler.postDelayed(runnable, DELAY_DURATION);
    }

    private void stopAutoScrolling() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.registerOnPageChangeCallback(pagerCallback);
        startAutoScrolling();
    }

    @Override
    protected void onPause() {
        viewPager.unregisterOnPageChangeCallback(pagerCallback);
        stopAutoScrolling();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        runnable = null;
        handler = null;
        viewPager = null;
        pagerCallback = null;
        super.onDestroy();
    }
}
