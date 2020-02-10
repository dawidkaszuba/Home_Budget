package pl.dawidkaszuba.homebudget.model;

import retrofit2.Retrofit;

public class TagsServiceManager {

    TagsService getTagService(Retrofit retrofit) {
        return retrofit.create(TagsService.class);
    }
}
