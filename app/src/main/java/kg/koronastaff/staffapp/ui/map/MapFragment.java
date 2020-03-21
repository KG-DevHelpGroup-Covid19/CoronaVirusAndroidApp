package kg.koronastaff.staffapp.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.FakeNewsAdapter;
import kg.koronastaff.staffapp.adapters.MapsAdapter;
import kg.koronastaff.staffapp.models.FakeNews;
import kg.koronastaff.staffapp.models.StationMap;


public class MapFragment extends Fragment {
    private RecyclerView recyclerViewMap;
    private MapViewModel mapViewModel;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        recyclerViewMap = rootView.findViewById(R.id.map_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewMap.setLayoutManager(layoutManager);

        ArrayList<StationMap> list = new ArrayList<>();
        list.add(new StationMap(0, "биш", "0", "www", 1.1, 1.1));
        mAdapter = new MapsAdapter(list);
        recyclerViewMap.setAdapter(mAdapter);

        return rootView;

    }

}

