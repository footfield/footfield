package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class OrderVM {

    @SerializedName("fieldDetailID")
    @Expose
    private int fieldDetailID;

    @SerializedName("slotID")
    @Expose
    private List<Integer> slotID;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("dateTookPlace")
    @Expose
    private Long dateTookPlace;

    @SerializedName("totalPrice")
    @Expose
    private Double totalPrice;

    public OrderVM() {
    }

    public OrderVM(int id, List<Integer> slotID, int userId, Long dateTookPlace, Double totalPrice) {
        this.fieldDetailID = id;
        this.slotID = slotID;
        this.userId = userId;
        this.dateTookPlace = dateTookPlace;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return fieldDetailID;
    }

    public void setId(int id) {
        this.fieldDetailID = id;
    }

    public List<Integer> getSlotID() {
        return slotID;
    }

    public void setSlotID(List<Integer> slotID) {
        this.slotID = slotID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getDateTookPlace() {
        return dateTookPlace;
    }

    public void setDateTookPlace(Long dateTookPlace) {
        this.dateTookPlace = dateTookPlace;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
