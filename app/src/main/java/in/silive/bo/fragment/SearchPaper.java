package in.silive.bo.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import in.silive.bo.R;
import in.silive.bo.activity.NavigationMain;
import in.silive.bo.adapter.SearchPaperAdapter;
import in.silive.bo.listener.SearchViewListener;
import in.silive.bo.model.PaperModel;
import in.silive.bo.network.PaperRequest;

/**
 * Created by kartikey on 22/02/16.
 */
public class SearchPaper extends Fragment implements SearchViewListener{
    private View rootView;
    ImageView button;
    PaperRequest paperRequest;
    public static SpiceManager spiceManager;
    private EditText paperName;
    public RecyclerView papersRecyclerView;
    boolean editTextEnabled = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_paper, container, false);

       /*button = (ImageView) rootView.findViewById(R.id.searchPaperButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PaperRequest",paperName.getText().toString());
                paperRequest = new PaperRequest(paperName.getText().toString());
                NavigationMain.spiceManager.execute(paperRequest, "Paper",DurationInMillis.ONE_SECOND, new ExecutingPaperRequest());
            }
        });
        paperName = (EditText) rootView.findViewById(R.id.search_txt);
*/        papersRecyclerView = (RecyclerView) rootView.findViewById(R.id.papers_list);
        return rootView;
    }

    public static void setSpiceManager(SpiceManager spiceManager) {
        SearchPaper.spiceManager = spiceManager;
    }

    public class ExecutingPaperRequest implements RequestListener<PaperModel.PapersList> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onRequestSuccess(PaperModel.PapersList paperModels) {
            Toast.makeText(getActivity(), "Request Succes with size " + paperModels.get(0).Title, Toast.LENGTH_SHORT).show();
            Context context = getContext();
            papersRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            SearchPaperAdapter adapter = new SearchPaperAdapter(context,paperModels);
            papersRecyclerView.setAdapter(adapter);
        }
    }
    @Override
    public void searchTextChanged(String newText){

    }

    @Override
    public void searchTextSubmit(String text) {
        paperRequest = new PaperRequest(text);
        NavigationMain.spiceManager.execute(paperRequest, "Paper",DurationInMillis.ONE_SECOND, new ExecutingPaperRequest());
    }
}
