package kg.koronastaff.staffapp.ui.fakenews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FakenewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FakenewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}