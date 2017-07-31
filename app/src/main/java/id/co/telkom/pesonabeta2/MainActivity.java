package id.co.telkom.pesonabeta2;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity {

    private TextView mHorizScrollMeasure;
    private IOverScrollDecor mHorizOverScrollEffect;

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

        initHorizontalRecyclerView((RecyclerView) findViewById(R.id.horizontal_recycler_view));
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
