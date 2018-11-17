package mkruglikov.fitnesskittest.api;

import java.util.List;

import mkruglikov.fitnesskittest.model.Training;
import retrofit2.Call;
import retrofit2.http.GET;

interface RetrofitInterface {

    @GET(" ")
    Call<List<Training>> getTrainings();
}
