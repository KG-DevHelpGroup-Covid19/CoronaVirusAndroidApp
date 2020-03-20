package kg.koronastaff.staffapp.ui.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import kg.koronastaff.staffapp.R;


public class NewsFragment extends Fragment {

    private RecyclerView recyclerViewNews;
    private TextView TNewsDate;
    private NewsViewModel newsViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerViewNews = rootView.findViewById(R.id.news_list_recycler);
        TNewsDate = rootView.findViewById(R.id.t_news_date);


        return rootView;


    }




}

