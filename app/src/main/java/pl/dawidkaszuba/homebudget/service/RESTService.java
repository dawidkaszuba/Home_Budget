package pl.dawidkaszuba.homebudget.service;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.PlannedCashFlow;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RESTService {


    @GET("users/{userId}/tags")
    Single<List<Tag>> getTagsService(@Header("Authorization") String token,
                                     @Path("userId") Long userId,
                                     @Query(value = "kind") String kind);

    @GET("users/{userId}/plannedCashFlows")
    Single<List<PlannedCashFlow>> getPlannedCashFlow(@Header("Authorization") String token,
                                                     @Path("userId") Long userId);
}
