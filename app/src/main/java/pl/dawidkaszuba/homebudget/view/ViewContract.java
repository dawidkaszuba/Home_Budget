package pl.dawidkaszuba.homebudget.view;

import java.util.List;

import pl.dawidkaszuba.homebudget.pojo.Tag;

public interface ViewContract {

    void fillTagsSpinner(List<Tag> tags);

    void errorMessage(String msg);
}
