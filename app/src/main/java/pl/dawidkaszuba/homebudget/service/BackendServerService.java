package pl.dawidkaszuba.homebudget.service;

import java.time.LocalDate;
import java.util.List;

import okhttp3.ResponseBody;
import pl.dawidkaszuba.homebudget.pojo.Balance;
import pl.dawidkaszuba.homebudget.pojo.Expenditure;
import pl.dawidkaszuba.homebudget.pojo.LoggedUser;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.User;
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

    @POST("/users/{userId}/expenditures")
    Call<ResponseBody> addExpenditure(@Header("Authorization") String token,
                                      @Body Expenditure expenditure,
                                      @Path("userId") Long userId);


}