package com.hhhh.pailiesan;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by QYQ on 2017/9/19.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.hhhh.pailiesan.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
