package idat.com.administracion.app.utils;

import idat.com.administracion.app.utils.DepartamentoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {
       public static Retrofit getApiService() {
           HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
           httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
           OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl("http://192.168.1.5:9999/api/")
                   .addConverterFactory(GsonConverterFactory.create())
                   .client(okHttpClient)
                   .build();
           return retrofit;

        }

        public static DepartamentoService getDepartamentoService(){
           DepartamentoService departamentoService = getApiService().create(DepartamentoService.class);
           return  departamentoService;
        }
}
