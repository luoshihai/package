package com.qqq.asdas.cptools;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qqq.asdas.R;


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
            Log.e("test UiActivity ", data );
            com.qqq.asdas.cptools.WebActivity.skipWebMain(UiActivity.this, url, data);
            finish();
        }
    };
}
