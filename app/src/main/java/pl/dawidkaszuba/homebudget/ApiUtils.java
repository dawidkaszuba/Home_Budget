package pl.dawidkaszuba.homebudget;

import pl.dawidkaszuba.homebudget.view.LoginActivity;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:8080/";

    public static LoginActivity getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(LoginActivity.class);
    }
}