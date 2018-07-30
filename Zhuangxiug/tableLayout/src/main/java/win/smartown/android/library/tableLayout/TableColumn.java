package win.smartown.android.library.tableLayout;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Smartown on 2017/7/19.
 */
public class TableColumn extends LinearLayout {

    private String[] content;
    private Callback callback;
    private float maxTextViewWidth,maxHeight;
    private ItemViewIntercept itemViewIntercept;
    ArrayList<TextView> textViews   = new ArrayList<>();
    public TableColumn(Context context, String[] content, Callback callback) {
        super(context);
        this.content = content;
        this.callback = callback;
        init();
    }

    public TableColumn(Context context, String[] content, Callback callback, ItemViewIntercept itemViewIntercept) {
        super(context);
        this.content = content;
        this.callback = callback;
        this.itemViewIntercept = itemViewIntercept;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //修改为使用默认的测量子视图的宽高
        //setMeasuredDimension((int) (callback.getTableLayout().getTableColumnPadding() * 2 + maxTextViewWidth), (int) maxHeight);
    }

    private void init() {
        setOrientation(VERTICAL);
        initContent();
    }

    private void initContent() {
        int padding = callback.getTableLayout().getTableColumnPadding();
        maxTextViewWidth = 0;
        textViews.clear();
        for (String text : content) {
            if (TextUtils.isEmpty(text)) {
                text = "";
            }
            TextView textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, callback.getTableLayout().getTableTextSize());
            textView.setTextColor(callback.getTableLayout().getTableTextColor());
            maxTextViewWidth = Math.max(maxTextViewWidth, Util.measureTextViewWidth(textView, text));
            textView.setGravity(getTextGravity(callback.getTableLayout().getTableTextGravity()));
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(text);
            textViews.add(textView);

        }
               int row = 0;
        maxHeight=0;
        for (TextView textView : textViews) {
            LayoutParams layoutParams = new LayoutParams((int) (padding * 2 + maxTextViewWidth), callback.getTableLayout().getTableRowHeight());

            addView(textView, layoutParams);
            if (itemViewIntercept != null) {
                String txt=textView.getText().toString();
                itemViewIntercept.onInterceptItemView(textView, row);
                int width = (int) Math.max(layoutParams.width,maxTextViewWidth);
                maxHeight+=layoutParams.height;
                textView.setWidth(width);
                textView.setHeight(layoutParams.height);
                maxTextViewWidth = Math.max(maxTextViewWidth, width);
            }
            row++;
        }
        if(maxHeight<=0){
            maxHeight=callback.getTableLayout().getTableRowHeight() * getChildCount();
        }
    }

    public int getItemHeight(int index ){

        TextView textView = textViews.get(index);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();

        return layoutParams.height;
    }

    private int getTextGravity(int tableTextGravity) {
        switch (tableTextGravity) {
            case 1:
                return Gravity.CENTER_VERTICAL;
            case 2:
                return Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        }
        return Gravity.CENTER;
    }

    public void setItemViewIntercept(ItemViewIntercept itemViewIntercept) {
        this.itemViewIntercept = itemViewIntercept;
    }

    public void onClick(float y) {
        if(true)return;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) getChildAt(i);
            if (textView.getBottom() >= y) {
                if (i == 0) {
                    return;
                }
                textView.setSelected(!textView.isSelected());
                textView.setBackgroundColor(textView.isSelected() ? callback.getTableLayout().getBackgroundColorSelected() : Color.TRANSPARENT);
                textView.setTextColor(textView.isSelected() ? callback.getTableLayout().getTableTextColorSelected() : callback.getTableLayout().getTableTextColor());
                return;
            }
        }
    }

    public interface Callback {
        TableLayout getTableLayout();
    }

    public interface ItemViewIntercept {
        void onInterceptItemView(TextView item, int row);
    }

}
