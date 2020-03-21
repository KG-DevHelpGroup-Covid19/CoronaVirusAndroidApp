package kg.koronastaff.staffapp.ui.quarantine;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.koronastaff.staffapp.R;

public class QuarantineFragment extends Fragment {

    private QuarantineViewModel mViewModel;

    public static QuarantineFragment newInstance() {
        return new QuarantineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quarantine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuarantineViewModel.class);
        // TODO: Use the ViewModel
    }

}
