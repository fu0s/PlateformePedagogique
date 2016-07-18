package example.soufiane.plateformepedagogique;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.yangbingqiang.android.parallaxviewpager.Mode;
import cn.yangbingqiang.android.parallaxviewpager.ParallaxViewPager;

public class BD extends AppCompatActivity {

    private ParallaxViewPager mParallaxViewPager;

    @SuppressWarnings("SpellCheckingInspection")
    private String[] mImages = new String[]{
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-1.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-2.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-3.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-4.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-5.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-6.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-7.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-22.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-non.png",
            "http://intellcaplux-001-site3.ctempurl.com/Images/AirClimat/Image/IMG-8.png",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bd);
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.viewpager);
        initViewPager();
    }

    private void initViewPager() {
        PagerAdapter adapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(container.getContext(), R.layout.pager_item, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
                Glide.with(BD.this).load(mImages[position % mImages.length]).into(imageView);
                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                return view;
            }

            @Override
            public int getCount() {
                return 40;
            }
        };
        mParallaxViewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.left_overlay:
                mParallaxViewPager.setMode(Mode.LEFT_OVERLAY);
                break;
            case R.id.right_overlay:
                mParallaxViewPager.setMode(Mode.RIGHT_OVERLAY);
                break;
            case R.id.none:
                mParallaxViewPager.setMode(Mode.NONE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}