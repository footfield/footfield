package khoa.example.com.project_swd_managerfootfield.Retrofit2;

import khoa.example.com.project_swd_managerfootfield.VM.LoginVM;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordForgotVM;
import khoa.example.com.project_swd_managerfootfield.VM.RegisterVM;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface DataClient {

    @POST("login")
    Call<Boolean> checkLogin(@Body LoginVM vm);

    @POST("user/register")
    Call<RegisterVM> createUser(@Body RegisterVM vm);

    @POST("password/forgot")
    Call<PasswordForgotVM> getNewPassword(@Body PasswordForgotVM vm);


//    @GET("english/{id}")
//    Call<List<EnglishQuestion>> loadEngQuestions(@Path("id") int id);
}
