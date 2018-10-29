package khoa.example.com.project_swd_managerfootfield.VM;

public class LocationVM {
    private Long locationID;

    private String locationName;

    public LocationVM(Long locationID, String locationName) {
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public LocationVM() {
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
}
