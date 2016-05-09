package com.perasia.fragmentdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.perasia.fragmentdemo.fragment.Fragment1;
import com.perasia.fragmentdemo.fragment.Fragment2;
import com.perasia.fragmentdemo.fragment.Fragment3;
import com.perasia.fragmentdemo.fragment.Fragment4;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Fragment[] mFragments;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    private FragmentManager mFragmentManager;

    private int btnIndex;
    private int currentBtnIndex = 0;

    @Bind({R.id.main_bottom1_rl, R.id.main_bottom2_rl, R.id.main_bottom3_rl, R.id.main_bottom4_rl})
    RelativeLayout[] mBottomLls = new RelativeLayout[4];

    @OnClick({R.id.main_bottom1_ll, R.id.main_bottom2_ll, R.id.main_bottom3_ll, R.id.main_bottom4_ll})
    void onBottomLayoutClick(View view) {
        switch (view.getId()) {
            case R.id.main_bottom1_ll:
                btnIndex = 0;
                break;
            case R.id.main_bottom2_ll:
                btnIndex = 1;
                break;
            case R.id.main_bottom3_ll:
                btnIndex = 2;
                break;
            case R.id.main_bottom4_ll:
                btnIndex = 3;
                break;
            default:
                break;
        }

        setClickData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            fragment1 = (Fragment1) mFragmentManager.findFragmentByTag("fragment1");
            fragment2 = (Fragment2) mFragmentManager.findFragmentByTag("fragment2");
            fragment3 = (Fragment3) mFragmentManager.findFragmentByTag("fragment3");
            fragment4 = (Fragment4) mFragmentManager.findFragmentByTag("fragment4");
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mBottomLls[0].setSelected(true);

        //test point
        mBottomLls[3].getChildAt(0).setVisibility(View.VISIBLE);

        fragment1 = Fragment1.getInstance(1);
        fragment2 = Fragment2.getInstance(2);
        fragment3 = Fragment3.getInstance(3);
        fragment4 = Fragment4.getInstance(4);
        mFragments = new Fragment[]{fragment1, fragment2, fragment3, fragment4};
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        transaction.add(R.id.main_fragment_container, fragment1, "fragment1")
                .add(R.id.main_fragment_container, fragment2, "fragment2")
                .add(R.id.main_fragment_container, fragment3, "fragment3")
                .add(R.id.main_fragment_container, fragment4, "fragment4")
                .show(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4);

        transaction.commitAllowingStateLoss();
    }

    private void setClickData() {
        if (currentBtnIndex != btnIndex) {
            FragmentTransaction trx = mFragmentManager.beginTransaction();
            trx.hide(mFragments[currentBtnIndex]);

            if (!mFragments[btnIndex].isAdded()) {
                trx.add(R.id.main_fragment_container, mFragments[btnIndex]);
            }
            trx.show(mFragments[btnIndex]);
            trx.commitAllowingStateLoss();
        }
        mBottomLls[currentBtnIndex].setSelected(false);
        mBottomLls[btnIndex].setSelected(true);
        currentBtnIndex = btnIndex;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
