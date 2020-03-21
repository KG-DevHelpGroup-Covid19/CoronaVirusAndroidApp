package kg.koronastaff.staffapp.ui.quarantine;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.FakeNewsAdapter;
import kg.koronastaff.staffapp.adapters.QuarantineAdapter;
import kg.koronastaff.staffapp.models.FakeNews;
import kg.koronastaff.staffapp.models.QuarantineSteps;

public class QuarantineFragment extends Fragment {

    private QuarantineViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static QuarantineFragment newInstance() {
        return new QuarantineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quarantine, container, false);
        recyclerView = root.findViewById(R.id.fake_news_recycler);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<QuarantineSteps> list = new ArrayList<>();
        list.add(new QuarantineSteps(0, "шаг 1", ""));
        mAdapter = new QuarantineAdapter(list);
        recyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuarantineViewModel.class);
        // TODO: Use the ViewModel
    }

}
