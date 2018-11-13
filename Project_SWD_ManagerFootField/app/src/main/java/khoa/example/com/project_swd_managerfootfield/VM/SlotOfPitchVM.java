package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlotOfPitchVM {

    @SerializedName("slotID")
    @Expose
    private int id;

    @SerializedName("slotName")
    @Expose
    private String slotName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("mutiple")
    @Expose
    private double mutiple;

    public SlotOfPitchVM(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public SlotOfPitchVM() {
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

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public double getMutiple() {
        return mutiple;
    }

    public void setMutiple(double mutiple) {
        this.mutiple = mutiple;
    }
}
