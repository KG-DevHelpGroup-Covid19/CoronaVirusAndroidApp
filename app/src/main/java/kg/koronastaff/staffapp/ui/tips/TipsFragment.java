package kg.koronastaff.staffapp.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import kg.koronastaff.staffapp.R;


public class TipsFragment extends Fragment {

    private RecyclerView recyclerViewTips;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        recyclerViewTips = rootView.findViewById(R.id.tips_list_recycler);
        return rootView;
    }
}