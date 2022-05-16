package ma.enset.tp3.service;
import ma.enset.tp3.model.ListNewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface RestServiceAPI {
    @GET("everything")
    Call<ListNewsResponse> listNewsByKey(@Query("q") String key,
                                         @Query("from") String date,
                                         @Query("apikey") String api);

}
