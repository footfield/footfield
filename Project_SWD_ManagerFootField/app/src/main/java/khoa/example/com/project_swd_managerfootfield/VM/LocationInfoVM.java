package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationInfoVM {

    @SerializedName("locationID")
    @Expose
    private Long locationID;

    @SerializedName("locationName")
    @Expose
    private String locationName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("phone")
    @Expose
    private String phone;

    public LocationInfoVM(Long locationID, String locationName, String address, String image, String status, String phone) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.address = address;
        this.image = image;
        this.status = status;
        this.phone = phone;
    }

    public LocationInfoVM() {

    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
