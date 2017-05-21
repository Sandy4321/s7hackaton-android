package ru.s7.android.io.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.s7.android.io.rest.response.Achievements;
import ru.s7.android.io.rest.response.Adventures;
import ru.s7.android.model.GamerProfile;
import ru.s7.android.model.Task;

/**
 * Main API
 *
 * @author celikindv
 */
public interface MainApi {

    @GET("profile/1")
    Call<GamerProfile> getProfile();

    @GET("achievements/all")
    Call<Achievements> getAchievements();

    @GET("achievements/my")
    Call<Achievements> getMyAchievements();

    @GET("adventures/all")
    Call<Adventures> getAdventures();

    @GET("adventures/my")
    Call<Adventures> getMyAdventures();

    @GET("adventures/{id}/tasks")
    Call<List<Task>> getTasksById(@Path("id") int id);

}
