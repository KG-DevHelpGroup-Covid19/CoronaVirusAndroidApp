package kg.koronastaff.staffapp.ui.fakenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.FakeNewsAdapter;
import kg.koronastaff.staffapp.models.FakeNews;

public class FakenewsFragment extends Fragment {

    private FakenewsViewModel fakenewsViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fakenewsViewModel =
                new ViewModelProvider(this).get(FakenewsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fakenews, container, false);
        // body
        recyclerView = root.findViewById(R.id.fake_news_recycler);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<FakeNews> list = new ArrayList<FakeNews>();
        list.add(new FakeNews(1, "title", "", 1));
        mAdapter = new FakeNewsAdapter(list);
        recyclerView.setAdapter(mAdapter);
        return root;
    }
}
