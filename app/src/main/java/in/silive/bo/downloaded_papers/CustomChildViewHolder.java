package in.silive.bo.downloaded_papers;

import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import in.silive.bo.R;

/**
 * Created by kartikey on 25/02/16.
 */
public class CustomChildViewHolder extends ChildViewHolder {

    public ImageView deleteView,openView;

    /**
     * Public constructor for the custom child ViewHolder
     *
     * @param itemView the child ViewHolder's view
     */
    public CustomChildViewHolder(View itemView) {
        super(itemView);
       /* deleteView=itemView.findViewById(R.id.);
        openView=itemView.findViewById(R.id.);*/
    }
}