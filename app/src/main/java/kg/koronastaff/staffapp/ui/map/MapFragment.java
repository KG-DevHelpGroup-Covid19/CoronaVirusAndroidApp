package kg.koronastaff.staffapp.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import kg.koronastaff.staffapp.R;


public class MapFragment extends Fragment {
    private RecyclerView recyclerViewMap;
    private MapViewModel mapViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);


        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        recyclerViewMap = rootView.findViewById(R.id.map_list_recycler);
        return rootView;
    }




}

