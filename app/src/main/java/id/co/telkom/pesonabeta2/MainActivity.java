package id.co.telkom.pesonabeta2;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollStateListener;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import id.co.telkom.pesonabeta2.R;
import id.co.telkom.pesonabeta2.videopilihan.DemoContentHelper;


import static me.everything.android.ui.overscroll.IOverScrollState.STATE_BOUNCE_BACK;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_END_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;

import id.co.telkom.pesonabeta2.fragment.destinasiFragment;
import id.co.telkom.pesonabeta2.fragment.dealsFragment;
import id.co.telkom.pesonabeta2.fragment.aktifitasFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mHorizScrollMeasure;
    private IOverScrollDecor mHorizOverScrollEffect;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    /*ArrayList<String> alName;*/
    ArrayList<Integer> alImage;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    ViewPagerAdapter adapter;

    //Fragments

    destinasiFragment destinasiFragment;
    dealsFragment dealsFragment;
    aktifitasFragment aktifitasFragment;


    String[] tabTitle={"DESTINASI","DEALS","AKTIFITAS & LIFESTYLE"};
    int[] unreadCount={0,5,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextView1 = (TextView) findViewById(R.id.judulBesar);
        TextView mTextView2 = (TextView) findViewById(R.id.judulKecil);
        mHorizScrollMeasure = (TextView) findViewById(R.id.horizontal_scroll_measure);
        final Button buttonBali = (Button) findViewById(R.id.push_bali);
        final Button buttonMalang = (Button) findViewById(R.id.push_malang);
        final Button buttonLombok = (Button) findViewById(R.id.push_lombok);
        final Button buttonPapua = (Button) findViewById(R.id.push_papua);
        final Button buttonNTT = (Button) findViewById(R.id.push_ntt);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        try
        {
            setupTabIcons();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        initHorizontalRecyclerView((RecyclerView) findViewById(R.id.horizontal_recycler_view));


        /*alName = new ArrayList<>(Arrays.asList("Cheesy...", "Crispy... ", "Fizzy...", "Cool...", "Softy...", "Fruity...", "Fresh...", "Sticky..."));*/
        alImage = new ArrayList<>(Arrays.asList(R.drawable.promo1, R.drawable.promo2, R.drawable.promo3, R.drawable.promo4, R.drawable.promo5, R.drawable.promo6, R.drawable.promo7, R.drawable.promo8, R.drawable.promo9, R.drawable.promo10, R.drawable.promo11, R.drawable.promo12));

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter(MainActivity.this, alImage);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        destinasiFragment=new destinasiFragment();
        dealsFragment = new dealsFragment();
        aktifitasFragment = new aktifitasFragment();
        adapter.addFragment(destinasiFragment,"DESTINASI");
        adapter.addFragment(dealsFragment,"DEALS");
        adapter.addFragment(aktifitasFragment,"LIFESTYLE & AKTIFITAS");
        viewPager.setAdapter(adapter);
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.activity_main,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_title.setText(tabTitle[pos]);
        if(unreadCount[pos]>0)
        {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(""+unreadCount[pos]);
        }
        else
            tv_count.setVisibility(View.GONE);


        return view;
    }
    private void setupTabIcons()
    {

        for(int i=0;i<tabTitle.length;i++)
        {
            /*TabLayout.Tab tabitem = tabLayout.newTab();
            tabitem.setCustomView(prepareTabView(i));
            tabLayout.addTab(tabitem);*/

            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }


    }

    private void initHorizontalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getApplicationContext());
        RecyclerView.Adapter adapter = new DemoRecyclerAdapterHorizontal(DemoContentHelper.getSpectrumItems(getResources()), appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Apply over-scroll in 'standard form' - i.e. using the helper.
        mHorizOverScrollEffect = OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        // Over-scroll listeners can be applied in standard form as well.
        mHorizOverScrollEffect.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                mHorizScrollMeasure.setText(String.valueOf((int) offset));
            }
        });

        mHorizOverScrollEffect.setOverScrollStateListener(new IOverScrollStateListener() {

            private final int mDragColorLeft = getResources().getColor(android.R.color.holo_purple);
            private final int mBounceBackColorLeft = getResources().getColor(android.R.color.holo_blue_light);
            private final int mDragColorRight = getResources().getColor(android.R.color.holo_red_light);
            private final int mBounceBackColorRight = getResources().getColor(android.R.color.holo_orange_dark);
            private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();

            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                if (newState == STATE_DRAG_START_SIDE) {
                    mHorizScrollMeasure.setTextColor(mDragColorLeft);
                } else if (newState == STATE_DRAG_END_SIDE) {
                    mHorizScrollMeasure.setTextColor(mDragColorRight);
                } else if (newState == STATE_BOUNCE_BACK) {
                    mHorizScrollMeasure.setTextColor((oldState == STATE_DRAG_START_SIDE) ? mBounceBackColorLeft : mBounceBackColorRight);
                } else {
                    mHorizScrollMeasure.setTextColor(mClearColor);
                }
            }
        });

    }
}
