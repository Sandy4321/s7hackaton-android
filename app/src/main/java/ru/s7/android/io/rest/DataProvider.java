package ru.s7.android.io.rest;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.s7.android.io.rest.response.Achievements;
import ru.s7.android.io.rest.response.Adventures;
import ru.s7.android.model.GamerProfile;
import ru.s7.android.model.Task;

/**
 * File provider
 *
 * @author celikindv
 */
@Singleton
public class DataProvider {
    /**
     * The constant FILE.
     */
    public static final String FILE = "file";
    private MainApi mainApi;
    private Context context;

    /**
     * Instantiates a new File provider.
     *
     * @param context the context
     * @param mainApi the files api
     */
    @Inject
    public DataProvider(Context context, MainApi mainApi) {
        this.mainApi = mainApi;
        this.context = context;
    }

    public void getProfile(final AsyncData<GamerProfile> asyncData) {
        mainApi.getProfile().enqueue(new Callback<GamerProfile>() {
            @Override
            public void onResponse(Call<GamerProfile> call, Response<GamerProfile> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<GamerProfile> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

    public void getAchievements(final AsyncData<Achievements> asyncData) {
        mainApi.getAchievements().enqueue(new Callback<Achievements>() {
            @Override
            public void onResponse(Call<Achievements> call, Response<Achievements> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<Achievements> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

    public void getMyAchievements(final AsyncData<Achievements> asyncData) {
        mainApi.getMyAchievements().enqueue(new Callback<Achievements>() {
            @Override
            public void onResponse(Call<Achievements> call, Response<Achievements> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<Achievements> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

    public void getAdventures(final AsyncData<Adventures> asyncData) {
        mainApi.getAdventures().enqueue(new Callback<Adventures>() {
            @Override
            public void onResponse(Call<Adventures> call, Response<Adventures> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<Adventures> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

    public void getMyAdventures(final AsyncData<Adventures> asyncData) {
        mainApi.getMyAdventures().enqueue(new Callback<Adventures>() {
            @Override
            public void onResponse(Call<Adventures> call, Response<Adventures> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<Adventures> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

    public void getTasksById(int id, final AsyncData<List<Task>> asyncData) {
        mainApi.getTasksById(id).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful())
                    asyncData.onSuccess(response.body());
                else asyncData.onError();
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                asyncData.onError();
            }
        });
    }

}
