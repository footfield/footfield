package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryVM {

    @SerializedName("orderId")
    @Expose
    private int orderId;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("locationName")
    @Expose
    private String locationName;

    @SerializedName("dateTookPlace")
    @Expose
    private Long datePicked;

    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;

    public HistoryVM() {
    }

    public HistoryVM(int orderId, int userId, String locationName, Long datePicked, String totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.locationName = locationName;
        this.datePicked = datePicked;
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getDatePicked() {
        return datePicked;
    }

    public void setDatePicked(Long datePicked) {
        this.datePicked = datePicked;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
