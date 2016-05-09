package com.perasia.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perasia.fragmentdemo.R;


public class Fragment3 extends Fragment {
    private static final String TAG = Fragment3.class.getSimpleName();

    private static final String FRAGMENT_TAG = "fragmentTag";

    private int mTag;

    public static Fragment3 getInstance(int tag) {
        Fragment3 f = new Fragment3();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_TAG, tag);
        f.setArguments(bundle);
        return f;
    }

    public Fragment3() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mTag = args.getInt(FRAGMENT_TAG);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_tv);
        textView.setText("tag=" + mTag);

        return view;
    }

}
