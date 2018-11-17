package mkruglikov.fitnesskittest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Training {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("teacher")
    @Expose
    private String teacherName;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("weekDay")
    @Expose
    private Integer weekDay;
    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("pay")
    @Expose
    private Boolean pay;
    @SerializedName("appointment")
    @Expose
    private Boolean appointment;
    @SerializedName("teacher_v2")
    @Expose
    private Teacher teacher;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("availability")
    @Expose
    private Integer availability;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public Boolean getPay() {
        return pay;
    }

    public Boolean getAppointment() {
        return appointment;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getColor() {
        return color;
    }

    public Integer getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Training)) return false;

        Training training = ((Training) obj);
        return name.equals(training.name) &&
                description.equals(training.description) &&
                place.equals(training.place) &&
                teacherName.equals(training.teacherName) &&
                startTime.equals(training.startTime) &&
                endTime.equals(training.endTime) &&
                weekDay.equals(training.weekDay) &&
                appointmentId.equals(training.appointmentId) &&
                serviceId.equals(training.serviceId) &&
                pay.equals(training.pay) &&
                appointment.equals(training.appointment) &&
                teacher.equals(training.teacher) &&
                color.equals(training.color) &&
                availability.equals(training.availability);
    }
}
