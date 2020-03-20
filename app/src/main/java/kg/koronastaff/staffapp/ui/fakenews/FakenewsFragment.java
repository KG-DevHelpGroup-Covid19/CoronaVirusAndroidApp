package kg.koronastaff.staffapp.ui.fakenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import kg.koronastaff.staffapp.R;

public class FakenewsFragment extends Fragment {

    private FakenewsViewModel fakenewsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fakenewsViewModel =
                new ViewModelProvider(this).get(FakenewsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fakenews, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        fakenewsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}
