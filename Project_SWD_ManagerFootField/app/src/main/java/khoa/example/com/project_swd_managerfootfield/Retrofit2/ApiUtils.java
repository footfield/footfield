package khoa.example.com.project_swd_managerfootfield.Retrofit2;

public class ApiUtils {
    public static final String BASE_URL = "http://25.8.22.194:1234/api/";
//    public static final String BASE_URL = "http://10.82.138.41:1234/api/";
//    public static final String BASE_URL = "http://192.168.43.196:1234/api/";
    // public static final String BASE_URL = "http://192.168.1.10:1234/api/";
    //public static final String BASE_URL = "http://192.168.137.1:1234/api/";
   // public static final String BASE_URL = "http://172.16.3.155:1234/api/";

    public static DataClient getData() {
        return RetrofitClient.getClient(BASE_URL).create(DataClient.class);
    }
}
