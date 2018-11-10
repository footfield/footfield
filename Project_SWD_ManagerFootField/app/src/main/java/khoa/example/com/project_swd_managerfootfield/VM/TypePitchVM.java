package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypePitchVM {

    @SerializedName("fieldTypeId")
    @Expose
    private int id;

    @SerializedName("fieldType")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("description")
    @Expose
    private String des;

    @SerializedName("price")
    @Expose
    private double price;

    public TypePitchVM() {
    }

    public TypePitchVM(int id, String description, String image, String des, double price) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.des = des;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
