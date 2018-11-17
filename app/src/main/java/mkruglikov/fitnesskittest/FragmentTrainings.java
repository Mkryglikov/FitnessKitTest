package mkruglikov.fitnesskittest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import mkruglikov.fitnesskittest.adapters.TrainingsRVAdapter;
import mkruglikov.fitnesskittest.model.Training;

public class FragmentTrainings extends Fragment {

    public static final String TRAININGS_ARGUMENTS_KEY = "TRAININGS_ARGUMENTS_KEY";

    public FragmentTrainings() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trainings, container, false);

        List<Training> trainings = null;
        if (getArguments() != null)
            trainings = Parcels.unwrap(getArguments().getParcelable(TRAININGS_ARGUMENTS_KEY));

        RecyclerView rvTrainings = rootView.findViewById(R.id.rvTrainings);
        TextView tvNoTrainingsMessage = rootView.findViewById(R.id.tvNoTrainingsMessage);

        if (trainings == null || trainings.isEmpty()) {
            rvTrainings.setVisibility(View.GONE);
            tvNoTrainingsMessage.setVisibility(View.VISIBLE);
        } else {
            rvTrainings.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTrainings.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            rvTrainings.setHasFixedSize(true);
            rvTrainings.setAdapter(new TrainingsRVAdapter(trainings));
        }

        return rootView;
    }

}
