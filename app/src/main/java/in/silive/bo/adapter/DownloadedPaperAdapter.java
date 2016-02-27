package in.silive.bo.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.silive.bo.R;

/**
 * Created by kartikey on 25/02/16.
 */
public class DownloadedPaperAdapter extends BaseAdapter{
   ArrayList<String> titles;
    private Context context;
    public DownloadedPaperAdapter(Context context,ArrayList<String> titles){this.context=context;
        this.titles=titles;}
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.downloaded_paper_adapter, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_name.setText(titles.get(position));
        return convertView;
    }
    class ViewHolder {
        TextView tv_name;

        public ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.paper_title);
            view.setTag(this);
        }
    }
}
