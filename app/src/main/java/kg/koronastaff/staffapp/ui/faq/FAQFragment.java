package kg.koronastaff.staffapp.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.adapters.FAQAdapter;
import kg.koronastaff.staffapp.adapters.QuarantineAdapter;
import kg.koronastaff.staffapp.models.FAQ;
import kg.koronastaff.staffapp.models.QuarantineSteps;

public class FAQFragment extends Fragment {

    private FAQViewModel faqViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        faqViewModel = new ViewModelProvider(this).get(FAQViewModel.class);

        View root = inflater.inflate(R.layout.fragment_faq, container, false);

        // recycler view
        recyclerView = root.findViewById(R.id.faq_news_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // hard coded list
        ArrayList<FAQ> list = new ArrayList<>();
        list.add(new FAQ("question?", "answer"));
        // adapter
        mAdapter = new FAQAdapter(list);
        recyclerView.setAdapter(mAdapter);

        Button b = root.findViewById(R.id.go_to_test_action);

        b.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.tests_fragment);
        });

        return root;
    }

}
