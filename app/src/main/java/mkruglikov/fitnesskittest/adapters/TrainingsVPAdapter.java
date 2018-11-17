package mkruglikov.fitnesskittest.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import mkruglikov.fitnesskittest.FragmentTrainings;
import mkruglikov.fitnesskittest.model.Training;

public class TrainingsVPAdapter extends FragmentPagerAdapter {

    private final List<List<Training>> trainingsByDay;

    public TrainingsVPAdapter(FragmentManager fm,
                              List<Training> trainings) {
        super(fm);

        trainingsByDay = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            trainingsByDay.add(new ArrayList<>());
        }

        for (Training training : trainings) {
            trainingsByDay.get(training.getWeekDay() - 1).add(training);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Пн";
            case 1:
                return "Вт";
            case 2:
                return "Ср";
            case 3:
                return "Чт";
            case 4:
                return "Пт";
            case 5:
                return "Сб";
            case 6:
                return "Вс";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTrainings fragment = new FragmentTrainings();
        Bundle args = new Bundle();
        args.putParcelable(FragmentTrainings.TRAININGS_ARGUMENTS_KEY, Parcels.wrap(trainingsByDay.get(position)));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
