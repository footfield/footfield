package khoa.example.com.project_swd_managerfootfield;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PitchDetail {

    @SerializedName("fieldId")
    @Expose
    private int id;

    @SerializedName("fieldName")
    @Expose
    private String description;

    public PitchDetail(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public PitchDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
