package pl.dawidkaszuba.homebudget.service;

import retrofit2.Retrofit;

public class RESTServiceManager {

    public RESTService getService(Retrofit retrofit) {
        return retrofit.create(RESTService.class);
    }
}
