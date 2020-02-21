package pl.dawidkaszuba.homebudget.view;

import android.content.Intent;

import java.util.List;

import pl.dawidkaszuba.homebudget.pojo.Tag;

public interface ViewExpenditureContract {

    void fillTagsSpinner(List<Tag> tags);
    void errorMessage(String msg);
    void navigateToHomeScreen(Intent intent);

}
