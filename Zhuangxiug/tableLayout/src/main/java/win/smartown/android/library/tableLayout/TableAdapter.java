package win.smartown.android.library.tableLayout;

import android.widget.TextView;

/**
 * Created by Smartown on 2017/7/19.
 */
public interface TableAdapter {

    int getColumnCount();

    String[] getColumnContent(int position);

    void onInterceptItemView(TextView itemView,int row,int col );

}
