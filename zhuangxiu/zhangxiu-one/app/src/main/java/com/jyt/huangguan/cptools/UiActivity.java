package com.jyt.huangguan.cptools;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.jyt.huangguan.R;


public class UiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        new Handler().postDelayed(aaa,1500);
    }

    Runnable aaa =new Runnable() {
        @Override
        public void run() {
            SpUtils spUtils = new SpUtils(getApplication());
            String url = spUtils.getString("URL", "");
            String data = spUtils.getString("DATA", "");
            WebActivity.skipWebMain(UiActivity.this, url, data);
            finish();
        }
    };
}
