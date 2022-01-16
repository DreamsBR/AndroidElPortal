package idat.com.administracion.app.handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import idat.com.administracion.app.Entidades.Usuario;
import idat.com.administracion.app.baseclass.enunciado.ModoIngreso;

public class Memoria {
    private static Memoria memoria;
    private ModoIngreso modoIngreso;
    private Usuario usuario;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseAuth mAuth;

    public static Memoria getInstance(){
        if(memoria == null)
            memoria = new Memoria();
        return memoria;
    }

    public ModoIngreso getModoIngreso() {
        return modoIngreso;
    }

    public void setModoIngreso(ModoIngreso modoIngreso) {
        this.modoIngreso = modoIngreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public FirebaseDatabase getDatabase(){
        if (database == null)
            database=FirebaseDatabase.getInstance();
        return database;
    }

    public FirebaseAuth getmAuth(){
        if (mAuth == null)
            mAuth=FirebaseAuth.getInstance();
        return mAuth;
    }

    public FirebaseStorage getStorage(){
        if (storage == null)
            storage=FirebaseStorage.getInstance();
        return storage;
    }


}
