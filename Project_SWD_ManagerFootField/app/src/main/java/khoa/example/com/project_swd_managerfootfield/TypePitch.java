package khoa.example.com.project_swd_managerfootfield;

public class TypePitch {
    private int id;
    private String description;

    public TypePitch(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public TypePitch() {
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
}
