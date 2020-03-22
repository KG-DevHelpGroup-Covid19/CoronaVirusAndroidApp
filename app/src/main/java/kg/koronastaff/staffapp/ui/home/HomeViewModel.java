package kg.koronastaff.staffapp.ui.home;

import androidx.lifecycle.ViewModel;

import java.util.Observable;

public class HomeViewModel extends ViewModel {

    private Observable mText;

    public HomeViewModel() {
        mText = new Observable();
    }

    public Observable getText() {
        return mText;
    }
}