package kg.koronastaff.staffapp.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.QuarantineAdapter;
import kg.koronastaff.staffapp.adapters.TipsAdapter;
import kg.koronastaff.staffapp.models.QuarantineSteps;
import kg.koronastaff.staffapp.models.Tips;


public class TipsFragment extends Fragment {

    private RecyclerView recyclerViewTips;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        recyclerViewTips = rootView.findViewById(R.id.tips_recycler);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTips.setLayoutManager(layoutManager);

        ArrayList<Tips> list = new ArrayList<>();
        list.add(new Tips("", "", ""));
        mAdapter = new TipsAdapter(list);
        recyclerViewTips.setAdapter(mAdapter);
        return rootView;
    }
}