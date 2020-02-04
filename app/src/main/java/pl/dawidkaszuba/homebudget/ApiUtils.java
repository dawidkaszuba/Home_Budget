package pl.dawidkaszuba.homebudget;

import pl.dawidkaszuba.homebudget.activity.LoginActivity;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.1.25:8080/";

    public static LoginActivity getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(LoginActivity.class);
    }
}