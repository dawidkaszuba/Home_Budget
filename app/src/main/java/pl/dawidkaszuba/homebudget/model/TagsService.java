package pl.dawidkaszuba.homebudget.model;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TagsService {


    @GET("users/{userId}/tags")
    Single<List<Tag>> getTagsService(@Header("Authorization") String token,
                                     @Path("userId") Long userId);
}
