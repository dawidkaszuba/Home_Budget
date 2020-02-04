package pl.dawidkaszuba.homebudget.service;

import pl.dawidkaszuba.homebudget.model.Token;
import pl.dawidkaszuba.homebudget.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BackendServerService {

    @POST("/authenticate")
    Call<Token> authenticate(@Body User user);


}