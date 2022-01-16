package idat.com.administracion.app.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import idat.com.administracion.app.utils.DateSerializer;
import idat.com.administracion.app.utils.TimeSerializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {

    public static final String baseUrl = "http://192.168.1.5:9999";
    private static Retrofit retrofit;
    private static String token;
    private static UsuarioApi usuarioApi;
    private static HabitanteApi habitanteApi;
    private static DocumentoAlmacenadoApi documentoAlmacenadoApi;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stethoInterceptor = new StethoInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(loggin)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(stethoInterceptor);

        return builder.build();
    }

    public static void setToken(String value){
        token = value;
        initClient();
    }

    public static UsuarioApi getUsuarioApi(){
        if(usuarioApi == null){
            usuarioApi = retrofit.create(UsuarioApi.class);
        }
        return usuarioApi;
    }

    public static HabitanteApi getHabitanteApi(){
        if (habitanteApi == null){
            habitanteApi = retrofit.create(HabitanteApi.class);
        }
        return habitanteApi;
    }

    public static DocumentoAlmacenadoApi getDocumentoAlmacenadoApi(){
        if (documentoAlmacenadoApi == null){
            documentoAlmacenadoApi = retrofit.create(DocumentoAlmacenadoApi.class);
        }
        return documentoAlmacenadoApi;
    }
}
