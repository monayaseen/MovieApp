package Api;

import Model.Movie;
import Model.MovieResponce;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface Service {

@GET("movie/popular")


    Call<MovieResponce> getPopularMovie(@Query("43b896ce4b9a2c203687a1f410fbbd8b") String apikey);




    @GET("movie/top_rated")
    Call<MovieResponce> getTopRatedMovies(@Query("43b896ce4b9a2c203687a1f410fbbd8b") String apikey);
}
