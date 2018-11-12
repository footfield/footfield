package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class OrderInfoVM {

    @SerializedName("orderId")
    @Expose
    private int id;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("locationId")
    @Expose
    private int locationId;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("fieldName")
    @Expose
    private String fieldName;

    @SerializedName("slotVMs")
    @Expose
    private List<String> slotVMs;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("dateBook")
    @Expose
    private Long dateBook;

    @SerializedName("dateTookPlace")
    @Expose
    private Long dateTookPlace;

    @SerializedName("totalPrice")
    @Expose
    private double totalPrice;

    public OrderInfoVM() {
    }

    public OrderInfoVM(int id, int userId, int locationId, String type, String fieldName, List<String> slotVMs, String status, Long dateBook, Long dateTookPlace, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.type = type;
        this.fieldName = fieldName;
        this.slotVMs = slotVMs;
        this.status = status;
        this.dateBook = dateBook;
        this.dateTookPlace = dateTookPlace;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getSlotVMs() {
        return slotVMs;
    }

    public void setSlotVMs(List<String> slotVMs) {
        this.slotVMs = slotVMs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDateBook() {
        return dateBook;
    }

    public void setDateBook(Long dateBook) {
        this.dateBook = dateBook;
    }

    public Long getDateTookPlace() {
        return dateTookPlace;
    }

    public void setDateTookPlace(Long dateTookPlace) {
        this.dateTookPlace = dateTookPlace;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
