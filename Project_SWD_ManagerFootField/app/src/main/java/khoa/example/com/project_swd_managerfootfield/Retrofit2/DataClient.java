package khoa.example.com.project_swd_managerfootfield.Retrofit2;

import java.util.List;

import khoa.example.com.project_swd_managerfootfield.VM.DistrictVM;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.LoginVM;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordChangeVM;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordForgotVM;
import khoa.example.com.project_swd_managerfootfield.VM.RegisterVM;
import khoa.example.com.project_swd_managerfootfield.VM.AppUserVM;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataClient {

    @POST("login")
    Call<Integer> checkLogin(@Body LoginVM vm);

    @POST("user/register")
    Call<RegisterVM> createUser(@Body RegisterVM vm);

    @POST("password/forgot")
    Call<PasswordForgotVM> getNewPassword(@Body PasswordForgotVM vm);

    @GET("user/info/{userId}")
    Call<AppUserVM> getUserInfo(@Path("userId") int userId);

    @PUT("user/update")
    Call<AppUserVM> updateUser(@Body AppUserVM vm);

    @PUT("password/change")
    Call<Boolean> updatePassword(@Body PasswordChangeVM vm);

    @GET("district/all")
    Call<List<DistrictVM>> getAllDistrict();

    @GET("location-info/district/{id}")
    Call<List<LocationInfoVM>> getLocationInfo(@Path("id") int id);


//    @GET("english/{id}")
//    Call<List<EnglishQuestion>> loadEngQuestions(@Path("id") int id);
}
