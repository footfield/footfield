package khoa.example.com.project_swd_managerfootfield.Retrofit2;

import java.util.Date;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.PitchDetail;
import khoa.example.com.project_swd_managerfootfield.VM.DistrictVM;
import khoa.example.com.project_swd_managerfootfield.VM.HistoryVM;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.LoginVM;
import khoa.example.com.project_swd_managerfootfield.VM.OrderInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.OrderVM;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordChangeVM;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordForgotVM;
import khoa.example.com.project_swd_managerfootfield.VM.RegisterVM;
import khoa.example.com.project_swd_managerfootfield.VM.AppUserVM;
import khoa.example.com.project_swd_managerfootfield.VM.SlotOfPitchVM;
import khoa.example.com.project_swd_managerfootfield.VM.TypePitchVM;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataClient {

    @POST("login")
    Call<Integer> checkLogin(@Body LoginVM vm);

    @POST("user-register")
    Call<RegisterVM> createUser(@Body RegisterVM vm);

    @POST("password-forgot")
    Call<PasswordForgotVM> getNewPassword(@Body PasswordForgotVM vm);

    @GET("user-info/{userId}")
    Call<AppUserVM> getUserInfo(@Path("userId") int userId);

    @PUT("user-update")
    Call<AppUserVM> updateUser(@Body AppUserVM vm);

    @PUT("password-change")
    Call<Boolean> updatePassword(@Body PasswordChangeVM vm);

    @GET("district-all")
    Call<List<DistrictVM>> getAllDistrict();

    @GET("location-info/district/{id}")
    Call<List<LocationInfoVM>> getLocationInfo(@Path("id") int id);

    @GET("slot")
    Call<List<SlotOfPitchVM>> getAllSlot();

    @GET("footfield-type/{locationId}")
    Call<List<TypePitchVM>> getFieldTypeLocation(@Path("locationId") int id);

    @POST("field-detail")
    Call<List<PitchDetail>> getPitch(@Body List<Integer> ids, @Query("fieldTypeId") int fieldTypeId, @Query("dateTook") Long dateTook);

    @POST("oder")
    Call<Integer> orderPitch(@Body OrderVM vm);

    @GET("oder-info/{id}")
    Call<OrderInfoVM> getOrderInfo(@Path("id") int id);

    @GET("location/{id}")
    Call<LocationInfoVM> getLocation(@Path("id") int id);

    @GET("history/{userId}")
    Call<List<HistoryVM> > getHistory(@Path("userId") int userId);


//    @GET("english/{id}")
//    Call<List<EnglishQuestion>> loadEngQuestions(@Path("id") int id);
}
