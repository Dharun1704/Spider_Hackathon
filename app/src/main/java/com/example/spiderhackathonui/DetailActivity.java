package com.example.spiderhackathonui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    RelativeLayout topPagerLayout, spinnerLayout;
    ArrayList<String> heading;
    Spinner spinner1, spinner2;
    Adapter adapter;
    BottomAdapter bottomAdapter;
    ViewPager2 topPager, bottomPager;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    TextView title;
    LinearLayout linearLayout;

    int pagerNo = 0;
    ArrayList<String> spinner1Text = new ArrayList<>();
    ArrayList<String> spinner2Text = new ArrayList<>();
    private boolean hideToolbar = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        pagerNo = intent.getIntExtra("headingPosition", 0);
        initTopPager();
        initBottomPager();

        linearLayout = findViewById(R.id.title_appbar_layout);
        appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(this);
        topPagerLayout = findViewById(R.id.topPagerLayout);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        toolbar = findViewById(R.id.toolbarAppLayout);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setVisibility(View.GONE);
        title = findViewById(R.id.title_on_appbar);
        if (pagerNo == 0) {
            title.setText("Highlights");
        }
        if (pagerNo == 1) {
            title.setText("Science");
        }
        if (pagerNo == 2) {
            title.setText("Gaming");
        }
        spinnerLayout = findViewById(R.id.spinnerLayout);

        spinner1Text.add("POPULAR");
        spinner1Text.add("TRENDING");
        spinner1Text.add("LATEST");
        spinner2Text.add("NOW");
        spinner2Text.add("TODAY");
        spinner2Text.add("THIS WEEK");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, spinner1Text);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, spinner2Text);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        heading = new ArrayList<>();
        heading.add("Highlights");
        heading.add("Science");
        heading.add("Gaming");
        heading.add("Movies");

        adapter = new Adapter(this, heading);
        bottomAdapter = new BottomAdapter(this);
        topPager.setAdapter(adapter);
        bottomPager.setAdapter(bottomAdapter);
        topPager.setCurrentItem(pagerNo, false);
        bottomPager.setCurrentItem(pagerNo, false);

        topPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            private int scrollState = ViewPager2.SCROLL_STATE_IDLE;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (scrollState == ViewPager2.SCROLL_STATE_IDLE) {
                    return;
                }
                topPager.scrollTo(bottomPager.getScrollX()*
                        topPager.getWidth()/
                        bottomPager.getWidth(), 0);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                scrollState = state;
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    topPager.setCurrentItem(bottomPager.getCurrentItem(), false);
                }
            }
        });

        setSwipe();
    }

    private void initTopPager() {
        topPager = findViewById(R.id.topPager);
        topPager.setClipToPadding(false);
        topPager.setClipChildren(false);
        topPager.setOffscreenPageLimit(3);
        topPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        topPager.setPageTransformer(compositePageTransformer);

        topPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                bottomPager.fakeDragBy(positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    pagerNo = position;
                    topPagerLayout.setBackground(ContextCompat.getDrawable(DetailActivity.this, R.drawable.bg_highlights));
                }
                if (position == 1) {
                    pagerNo = position;
                    topPagerLayout.setBackground(ContextCompat.getDrawable(DetailActivity.this, R.drawable.bg_science));
                }
                if (position == 2) {
                    pagerNo = position;
                    topPagerLayout.setBackground(ContextCompat.getDrawable(DetailActivity.this, R.drawable.bg_gaming));
                }
                if (position == 3) {
                    pagerNo = position;
                    topPagerLayout.setBackground(ContextCompat.getDrawable(DetailActivity.this, R.drawable.bg_movies));
                }
                bottomPager.endFakeDrag();
                bottomPager.setCurrentItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_INDICATOR_START) {
                    bottomPager.beginFakeDrag();
                }
            }
        });
    }

    private void initBottomPager() {
        bottomPager = findViewById(R.id.viewPagerBottom);
        bottomPager.setClipToPadding(false);
        bottomPager.setClipChildren(false);
        bottomPager.setOffscreenPageLimit(3);
        bottomPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        bottomPager.setPageTransformer(compositePageTransformer);

        bottomPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                topPager.fakeDragBy(positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                pagerNo = position;
                topPager.endFakeDrag();
                topPager.setCurrentItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_INDICATOR_START) {
                    topPager.beginFakeDrag();
                }
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 0.5f && hideToolbar) {
            linearLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            topPager.setVisibility(View.GONE);
            hideToolbar = !hideToolbar;
        }

        else if (percentage < 0.5f && hideToolbar) {
            linearLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            topPager.setVisibility(View.VISIBLE);
            hideToolbar = !hideToolbar;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setSwipe() {
        final GestureDetector gd = new GestureDetector(DetailActivity.this, new GestureDetector.OnGestureListener() {
            boolean result = false;
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffY) > Math.abs(diffX)) {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_out_up, R.anim.slide_in_down);
                            }
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        });
        spinnerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
    }
}
