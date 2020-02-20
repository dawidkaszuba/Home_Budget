package pl.dawidkaszuba.homebudget.model;

import retrofit2.Retrofit;

public class RESTServiceManager {

    RESTService getTagService(Retrofit retrofit) {
        return retrofit.create(RESTService.class);
    }
}
