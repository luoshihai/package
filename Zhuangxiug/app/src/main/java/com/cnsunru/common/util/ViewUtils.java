package com.cnsunru.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/8.
 * 功能定位：主要用来协助处理界面的帮助类
 */
public class ViewUtils {

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @return 1:MIUUI 2:Flyme 3:android6.0
     */
    public static int StatusBarLightMode(Activity activity, boolean isDrak) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (MIUISetStatusBarLightMode(activity.getWindow(), isDrak)) {
                result = 1;
            } else if (FlymeSetStatusBarLightMode(activity.getWindow(), isDrak)) {
                result = 2;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                result = 3;
            }
        }
        return result;
    }

    public static int StatusBarLightMode(Activity activity) {
        return StatusBarLightMode(activity, true);
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */

    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 给ViewGroup绑定第一级孩子的点击事件
     *
     * @param parent
     * @param listener
     */
    public static void bindViewGroupChildClickListener(final ViewGroup parent, final OnGroupChildClickListener listener) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            final int position = i;
            View child = parent.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onChildClick(parent, v, position);
                }
            });
        }
    }

    /**
     * 多状态切换监听
     *
     * @param stateCount
     * @param beginState
     * @param onStateSwitchListener
     * @param views
     */
    public static void multiStateClickListener(final int stateCount, final int beginState, final OnStateSwitchListener onStateSwitchListener, View... views) {
        for (View view : views) {
            MultiStateClickListener listener = new MultiStateClickListener(stateCount, beginState) {
                @Override
                public void onClick(View v) {
                    currentState = (currentState + 1) % stateCounts;//当前状态值
                    onStateSwitchListener.onStateSwitch(v, currentState);
                }
            };
            onStateSwitchListener.onStateSwitch(view, beginState);
            view.setOnClickListener(listener);
            view.setTag(listener);
        }
    }

    public static void clearMultiState(View ingoreView, View... views) {
        for (View view : views) {
            if (view != ingoreView && view.getTag() instanceof MultiStateClickListener) {
                ((MultiStateClickListener) view.getTag()).setCurrentState(0);
            }
        }
    }

    public static abstract class MultiStateClickListener implements View.OnClickListener {
        int stateCounts = 0;
        int currentState = 0;

        public void setCurrentState(int currentState) {
            this.currentState = currentState;
        }

        public MultiStateClickListener(int stateCounts, int currentState) {
            this.stateCounts = stateCounts;
            this.currentState = currentState;
        }

    }

    /**
     * d单选切换监听
     *
     * @param beginIndex
     * @param onRadioSwitchListener
     * @param views
     */
    public static void radioSwitchListener(final int beginIndex, final OnRadioSwitchListener onRadioSwitchListener, final View... views) {
        for (View view : views) {
            View.OnClickListener listener = new View.OnClickListener() {
                int selectIndex = beginIndex;

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < views.length; i++) {
                        if (v == views[i]) {
                            selectIndex = i;
                            break;
                        }
                    }
                    onRadioSwitchListener.onRadioSwitch(views, selectIndex);
                }
            };
            if (beginIndex != -1) {
                onRadioSwitchListener.onRadioSwitch(views, beginIndex);
            }
            view.setOnClickListener(listener);
        }
    }


    public interface OnStateSwitchListener {
        void onStateSwitch(View view, int currentState);
    }

    public interface OnGroupChildClickListener {
        void onChildClick(ViewGroup viewGroup, View child, int position);
    }

    public interface OnRadioSwitchListener {
        void onRadioSwitch(View views[], int selectIndex);
    }

    public class SafeWatcher implements TextWatcher {
        EditText editText;

        public SafeWatcher(EditText editText) {
            this.editText = editText;
            this.editText.addTextChangedListener(this);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            editText.removeTextChangedListener(this);
            before(s, start, count, after);
            editText.addTextChangedListener(this);
        }

        public void before(CharSequence s, int start, int count, int after) {
        }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) {
            editText.removeTextChangedListener(this);
            onChanged(s, start, before, count);
            editText.addTextChangedListener(this);

        }

        public void onChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        final public void afterTextChanged(Editable s) {
            editText.removeTextChangedListener(this);
            after(s);
            editText.addTextChangedListener(this);
        }

        public void after(Editable s) {
        }
    }
}
