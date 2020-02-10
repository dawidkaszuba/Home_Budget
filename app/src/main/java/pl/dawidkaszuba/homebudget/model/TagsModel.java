package pl.dawidkaszuba.homebudget.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagsModel implements ModelContract {

    private Context context;

    public TagsModel(Context context) {
        this.context=context;
    }

    @Override
    public Single<List<Tag>> getTagsFromHttp() {

        TagsServiceManager tagsServiceManager = new TagsServiceManager();

        MyPreferences myPreferences = MyPreferences.getInstance(context);
        Token token = new Token(myPreferences.getPreference("TOKEN"));
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));


        return tagsServiceManager.getTagService(createRetrofit()).getTagsService(token.getToken(),userId);
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.21:8080/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Gson createGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
