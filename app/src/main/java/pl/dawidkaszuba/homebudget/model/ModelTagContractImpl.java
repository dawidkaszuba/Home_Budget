package pl.dawidkaszuba.homebudget.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.service.RESTServiceManager;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelTagContractImpl implements ModelTagContract {

    private Context context;

    public ModelTagContractImpl(Context context) {
        this.context=context;
    }

    @Override
    public Single<List<Tag>> getTagsFromHttp(String kind) {

        RESTServiceManager RESTServiceManager = new RESTServiceManager();

        MyPreferences myPreferences = MyPreferences.getInstance(context);
        Token token = new Token(myPreferences.getPreference("TOKEN"));
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));


        return RESTServiceManager.getService(createRetrofit()).getTagsService(token.getToken(),userId,kind);
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.14:8080/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Gson createGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
