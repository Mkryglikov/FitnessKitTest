package mkruglikov.fitnesskittest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import mkruglikov.fitnesskittest.adapters.TrainingsVPAdapter;
import mkruglikov.fitnesskittest.api.Api;
import mkruglikov.fitnesskittest.model.Training;
import mkruglikov.fitnesskittest.utils.ConnectionUtils;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String PREFS_TRAININGS_KEY = "PREFS_TRAININGS_KEY";

    private SwipeRefreshLayout srlMain;
    private TabLayout tlMain;
    private ViewPager vpMain;
    private ProgressBar pbMain;
    private TextView tvMainNoNetwork;

    private List<Training> trainings;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        srlMain = findViewById(R.id.srlMain);
        tlMain = findViewById(R.id.tlMain);
        vpMain = findViewById(R.id.vpMain);
        pbMain = findViewById(R.id.pbMain);
        tvMainNoNetwork = findViewById(R.id.tvMainNoNetwork);

        setSupportActionBar(findViewById(R.id.toolbarMain));

        srlMain.setOnRefreshListener(this);
        srlMain.setColorSchemeResources(R.color.colorAccent);

        List<Training> savedTrainings = new Gson().fromJson(sharedPreferences.getString(PREFS_TRAININGS_KEY, ""), new TypeToken<List<Training>>() {
        }.getType());

        if (savedTrainings != null && !savedTrainings.isEmpty()) {
            showTrainings(savedTrainings);
            setLoaded();
        } else {
            setLoading();
        }

        loadTrainings();
    }

    @Override
    public void onRefresh() {
        loadTrainings();
    }

    private void setLoading() {
        tlMain.setVisibility(View.GONE);
        vpMain.setVisibility(View.GONE);
        tvMainNoNetwork.setVisibility(View.GONE);
        pbMain.setVisibility(View.VISIBLE);
    }

    private void setLoaded() {
        pbMain.setVisibility(View.GONE);
        srlMain.setRefreshing(false);
        if (trainings != null && !trainings.isEmpty())
            tlMain.setVisibility(View.VISIBLE);
        vpMain.setVisibility(View.VISIBLE);
    }

    private void loadTrainings() {
        if (!ConnectionUtils.isNetworkConnected(getApplicationContext())) {
            if (trainings == null || trainings.isEmpty())
                tvMainNoNetwork.setVisibility(View.VISIBLE);
            else
                Toast.makeText(this, getResources().getString(R.string.no_network_message), Toast.LENGTH_SHORT).show();

            setLoaded();
            return;
        }
        if (trainings == null || trainings.isEmpty())
            setLoading();

        Api.getTrainings((trainings, exceptionMessage) -> {
            if (exceptionMessage != null) {
                Toast.makeText(MainActivity.this, exceptionMessage, Toast.LENGTH_LONG).show();
                setLoaded();
                return;
            }
            sharedPreferences.edit()
                    .putString(PREFS_TRAININGS_KEY, new Gson().toJson(trainings))
                    .apply();
            showTrainings(trainings);
        });
    }

    private void showTrainings(List<Training> trainings) {
        if (this.trainings != null && this.trainings.equals(trainings)) {
            setLoaded();
            return;
        }

        this.trainings = trainings;

        if (vpMain.getAdapter() == null) {
            vpMain.setAdapter(new TrainingsVPAdapter(getSupportFragmentManager(), trainings));
            vpMain.setOffscreenPageLimit(7);
            tlMain.setupWithViewPager(vpMain);
            tlMain.setTabTextColors(getResources().getColor(R.color.colorDarkGrey), getResources().getColor(R.color.colorAccent));
        } else {
            vpMain.getAdapter().notifyDataSetChanged();
        }
        setLoaded();
    }
}
