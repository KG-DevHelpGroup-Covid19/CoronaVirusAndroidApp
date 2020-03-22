package kg.koronastaff.staffapp.ui.article;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import kg.koronastaff.staffapp.App;
import kg.koronastaff.staffapp.R;

public class ArticleFragment extends Fragment {
    private int articleId = 0;
    private ArticleViewModel mViewModel;

    TextView articleTitle;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.article_fragment, container, false);
        articleTitle= root.findViewById(R.id.article_label);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        // TODO: Use the ViewModel
        App app = (App) getActivity().getApplication();
        articleTitle.setText(app.getData("article"));
        articleTitle.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.menu_news));
    }
}
