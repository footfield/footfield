package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictVM {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("disName")
    @Expose
    private String disName;

    public DistrictVM() {
    }

    public DistrictVM(int id, String disName) {
        this.id = id;
        this.disName = disName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }
}
