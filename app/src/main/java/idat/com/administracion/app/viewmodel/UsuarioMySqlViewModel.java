package idat.com.administracion.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.UsuarioMySql;
import idat.com.administracion.app.repositories.UsuarioRepository;

public class UsuarioMySqlViewModel extends AndroidViewModel {

    private final UsuarioRepository repository;


    public UsuarioMySqlViewModel(@NonNull Application application) {
        super(application);
        this.repository = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse<UsuarioMySql>> login(String email, String password){
        return this.repository.login(email,password);
    }

    public LiveData<GenericResponse<UsuarioMySql>> save(UsuarioMySql umsql){
        return this.repository.save(umsql);
    }
}
