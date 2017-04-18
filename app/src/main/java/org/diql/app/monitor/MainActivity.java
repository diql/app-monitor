package org.diql.app.monitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    protected TextView mTvMsg;
    protected ListView mLlApp;
    private ListAdapter mAdapter;

    private List<AppBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        mList = AppUtils.getAllApk(this);

        mTvMsg.setText(mList.toString());

        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public AppBean getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final View view;
                if (convertView == null) {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_app, parent, false);
                } else {
                    view = convertView;
                }
//                Log.d(TAG, "getView: position:" + position + ".conventId:" + convertView.hashCode());
                AppBean appBean = getItem(position);
                if (appBean != null) {
                    ImageView ivApp = (ImageView) view.findViewById(R.id.iv_app);
                    ivApp.setImageDrawable(appBean.getAppIcon());
                    TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                    TextView tvPackage = (TextView) view.findViewById(R.id.tv_package);
                    tvTitle.setText(appBean.getAppName());
                    tvPackage.setText(appBean.getAppPackageName());
                }

                return view;
            }
        };
        mLlApp.setAdapter(mAdapter);
    }

    private void initView() {
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mLlApp = (ListView) findViewById(R.id.ll_app);
    }
}
