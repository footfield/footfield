package khoa.example.com.project_swd_managerfootfield.VM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordChangeVM {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("newPassword")
    @Expose
    private String newPassword;

    public PasswordChangeVM() {
    }

    public PasswordChangeVM(int id, String newPassword) {
        this.id = id;
        this.newPassword = newPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
