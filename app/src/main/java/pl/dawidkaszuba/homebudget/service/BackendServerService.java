package pl.dawidkaszuba.homebudget.service;

import java.time.LocalDate;

import pl.dawidkaszuba.homebudget.model.Balance;
import pl.dawidkaszuba.homebudget.model.LoggedUser;
import pl.dawidkaszuba.homebudget.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BackendServerService {

    @POST("/authenticate")
    Call<LoggedUser> authenticate(@Body User user);


    @GET("/users/{userId}/balance")
    Call<Balance> getBalance(@Header("Authorization") String token,
                             @Path("userId") Long userId,
                             @Query(value = "from") LocalDate from,
                             @Query(value = "to") LocalDate to);
}