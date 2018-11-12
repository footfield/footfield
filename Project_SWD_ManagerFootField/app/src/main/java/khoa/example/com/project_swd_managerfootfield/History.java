package khoa.example.com.project_swd_managerfootfield;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("orderId")
    @Expose
    private int orderId;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("locationName")
    @Expose
    private String locationName;

    @SerializedName("datePicked")
    @Expose
    private String datePicked;

    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;

    public History() {
    }

    public History(int orderId, int userId, String locationName, String datePicked, String totalPrice) {
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

    public String getDatePicked() {
        return datePicked;
    }

    public void setDatePicked(String datePicked) {
        this.datePicked = datePicked;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
