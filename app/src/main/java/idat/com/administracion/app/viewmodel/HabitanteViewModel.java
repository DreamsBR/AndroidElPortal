package idat.com.administracion.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.LiveData;

import idat.com.administracion.app.Entidades.GenericResponse;
import idat.com.administracion.app.Entidades.Habitante;
import idat.com.administracion.app.repositories.HabitanteRepository;

public class HabitanteViewModel extends AndroidViewModel {

    private final HabitanteRepository repository;

    public HabitanteViewModel(@NonNull Application application){
        super(application);
        this.repository = HabitanteRepository.getInstance();
    }

    public LiveData<GenericResponse<Habitante>> guardarHabitante(Habitante h){
        return repository.guardarHabitante(h);
    }

}
