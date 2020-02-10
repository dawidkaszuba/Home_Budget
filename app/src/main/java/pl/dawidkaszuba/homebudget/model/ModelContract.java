package pl.dawidkaszuba.homebudget.model;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.Tag;

public interface ModelContract {

    Single<List<Tag>> getTagsFromHttp();
}
