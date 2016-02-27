package in.silive.bo.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import in.silive.bo.R;
import in.silive.bo.adapter.DownloadedPaperAdapter;
import in.silive.bo.listener.SearchViewListener;

/**
 * Created by kartikey on 22/02/16.
 */
public class DownloadedPapers extends Fragment implements SearchViewListener{
    View rootView;
    private SwipeMenuListView listView;
    private ArrayList<String> dataItemsList;
    private DownloadedPaperAdapter downloadedPaperAdapter;
     Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.downloaded_papers,container,false);
        listView= (SwipeMenuListView) rootView.findViewById(R.id.downloadedPapersView);
        dataItemsList=new ArrayList<>();
        context=getActivity();
        for(int i=0;i<100;i++){
            dataItemsList.add(i+"th element");
        }
        downloadedPaperAdapter=new DownloadedPaperAdapter(getActivity(),dataItemsList);
        listView.setAdapter(downloadedPaperAdapter);
        listView.setTextFilterEnabled(true);
        setSwipeMenuCreator();
        return rootView;
    }
    public void setSwipeMenuCreator(){
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;
                    case 2:
                        createMenu3(menu);
                        break;
                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        context);
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                        0x5E)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.cross);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        context);
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.drawable.cross);
                menu.addMenuItem(item2);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        context);
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                        0x3F)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.bytepad);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        context);
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.drawable.bytepad);
                menu.addMenuItem(item2);
            }

            private void createMenu3(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        context);
                item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.cross);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        context);
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.drawable.cross);
                menu.addMenuItem(item2);
            }
        };
        // set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getActivity(), "Clicked first element", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Clicked second element", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
    public static int convertDpToPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int)px;
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void searchTextChanged(String newText) {
        if (!TextUtils.isEmpty(newText) && listView!=null)
             listView.setFilterText(newText);
    }

    @Override
    public void searchTextSubmit(String text) {

    }
}
