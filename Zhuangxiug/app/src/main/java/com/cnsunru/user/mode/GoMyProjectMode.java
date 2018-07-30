package com.cnsunru.user.mode;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;

/**
 * Created by WQ on 2017/9/15.
 */

public class GoMyProjectMode {
    View goMyProject;
    StartIntent startIntent= IntentPoxy.getProxyInstance();
    public GoMyProjectMode(final View goMyProject) {
        this.goMyProject = goMyProject;
        this.goMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startMyProjectActivity((Activity) goMyProject.getContext());
            }
        });
    }
}
