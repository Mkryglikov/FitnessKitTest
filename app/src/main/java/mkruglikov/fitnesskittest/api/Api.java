package mkruglikov.fitnesskittest.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mkruglikov.fitnesskittest.BuildConfig;
import mkruglikov.fitnesskittest.model.Training;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static RetrofitInterface api;

    private static void initApi() {
        if (api == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            api = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(RetrofitInterface.class);
        }
    }

    public static void getTrainings(OnGetTrainingsListener listener) {
        initApi();

        api.getTrainings().enqueue(new Callback<List<Training>>() {
            @Override
            public void onResponse(Call<List<Training>> call, Response<List<Training>> response) {
                if (response.code() == 200)
                    listener.onGetTrainings(response.body(), null);
                else
                    listener.onGetTrainings(null, "Ошибка при загрузке тренировок");
            }

            @Override
            public void onFailure(Call<List<Training>> call, Throwable t) {
                listener.onGetTrainings(null, "Ошибка при загрузке тренировок");
            }
        });
    }

    public interface OnGetTrainingsListener {
        void onGetTrainings(List<Training> trainings, String exceptionMessage);
    }
}
