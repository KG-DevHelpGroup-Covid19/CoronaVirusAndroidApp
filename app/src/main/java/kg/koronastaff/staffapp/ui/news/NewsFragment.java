package kg.koronastaff.staffapp.ui.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.FakeNewsAdapter;
import kg.koronastaff.staffapp.adapters.NewsAdapter;
import kg.koronastaff.staffapp.models.FakeNews;
import kg.koronastaff.staffapp.models.News;


public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        TextView t = rootView.findViewById(R.id.dead_num);
        t.setText("3");
        recyclerView = rootView.findViewById(R.id.news_list_recycler);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<News> list = new ArrayList<>();
        list.add(new News(1, "title", "", 1));
        mAdapter = new NewsAdapter(list, getActivity());
        recyclerView.setAdapter(mAdapter);

        return rootView;


    }




}

