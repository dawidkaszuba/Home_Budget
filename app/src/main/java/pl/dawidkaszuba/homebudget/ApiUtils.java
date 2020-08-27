package pl.dawidkaszuba.homebudget;

import pl.dawidkaszuba.homebudget.view.LoginActivity;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.1.14:8080/";

    public static LoginActivity getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(LoginActivity.class);
    }
}