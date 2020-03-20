package kg.koronastaff.staffapp.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import kg.koronastaff.staffapp.R;

public class FAQFragment extends Fragment {

    private FAQViewModel faqViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        faqViewModel = new ViewModelProvider(this).get(FAQViewModel.class);

        View root = inflater.inflate(R.layout.fragment_faq, container, false);

        final TextView textView = root.findViewById(R.id.text_slideshow);

        faqViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

}
