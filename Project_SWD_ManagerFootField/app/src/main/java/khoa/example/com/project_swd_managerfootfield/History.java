package khoa.example.com.project_swd_managerfootfield;

public class History {

    private int stt;
    private String locationName;
    private String datePicked;
    private String totalPrice;

    public History(int stt, String locationName, String datePicked, String totalPrice) {
        this.stt = stt;
        this.locationName = locationName;
        this.datePicked = datePicked;
        this.totalPrice = totalPrice;
    }

    public History() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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
