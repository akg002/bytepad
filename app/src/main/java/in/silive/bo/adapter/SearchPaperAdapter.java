package in.silive.bo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.silive.bo.R;
import in.silive.bo.fragment.SearchPaper;
import in.silive.bo.model.PaperModel;

/**
 * Created by AKG002 on 25-02-2016.
 */
public class SearchPaperAdapter extends RecyclerView.Adapter<SearchPaperAdapter.ViewHolder> {
    Context context;
    PaperModel.PapersList list ;
    public SearchPaperAdapter(Context context,PaperModel.PapersList list){
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchPaperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_paper_adapter_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchPaperAdapter.ViewHolder holder, int position) {
            holder.paperTitle.setText(((PaperModel)list.get(position)).Title);
        holder.paperSize.setText(((PaperModel)list.get(position)).Size);
        holder.paperCategory.setText(((PaperModel)list.get(position)).PaperCategory);
        Log.d("SearchPaperDapter","item added : "+list.get(position).Title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView paperCardView;
        TextView paperTitle,paperSize,paperCategory;
        public ViewHolder(View itemView) {
            super(itemView);
            this.paperCardView = (CardView) itemView.findViewById(R.id.paper_cardview);
            this.paperTitle = (TextView) itemView.findViewById(R.id.paper_title);
            this.paperSize = (TextView) itemView.findViewById(R.id.paper_size);
            this.paperCategory = (TextView) itemView.findViewById(R.id.paper_type);
        }
    }
}
