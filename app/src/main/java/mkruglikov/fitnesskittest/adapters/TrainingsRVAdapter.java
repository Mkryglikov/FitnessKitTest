package mkruglikov.fitnesskittest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mkruglikov.fitnesskittest.R;
import mkruglikov.fitnesskittest.model.Training;
public class TrainingsRVAdapter extends RecyclerView.Adapter<TrainingsRVAdapter.ViewHolder> {

    private final List<Training> trainings;

    public TrainingsRVAdapter(List<Training> trainings) {
        this.trainings = trainings;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTrainingItemStartTime, tvTrainingItemEndTime, tvTrainingItemName, tvTrainingItemTeacherName;

        ViewHolder(View itemView) {
            super(itemView);
            tvTrainingItemStartTime = itemView.findViewById(R.id.tvTrainingItemStartTime);
            tvTrainingItemEndTime = itemView.findViewById(R.id.tvTrainingItemEndTime);
            tvTrainingItemName = itemView.findViewById(R.id.tvTrainingItemName);
            tvTrainingItemTeacherName = itemView.findViewById(R.id.tvTrainingItemTeacherName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Training training = trainings.get(position);

        holder.tvTrainingItemStartTime.setText(training.getStartTime());
        holder.tvTrainingItemEndTime.setText(training.getEndTime());
        holder.tvTrainingItemName.setText(training.getName());
        holder.tvTrainingItemTeacherName.setText(training.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }
}
