package mkruglikov.fitnesskittest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Teacher {
    @SerializedName("short_name")
    @Expose
    private String shortName;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Teacher)) return false;

        Teacher teacher = ((Teacher) obj);
        return shortName.equals(teacher.shortName) &&
                name.equals(teacher.name) &&
                position.equals(teacher.position) &&
                imageUrl.equals(teacher.imageUrl);
    }
}
