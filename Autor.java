import java.util.*;
import java.io.*;

public class Autor {
    private String id;
    private String nombre;
    private UnorderedDoubleLinkedList<Publicacion> listaPublicaciones;

    public Autor(String pId, String pNombre) {
        this.id = pId;
        this.nombre = pNombre;
        this.listaPublicaciones = new UnorderedDoubleLinkedList<Publicacion>();
    }

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public UnorderedDoubleLinkedList<Publicacion> getListaPublicaciones() {
        return this.listaPublicaciones;
    }

    public void addPublicacion(Publicacion publicacion) {
        listaPublicaciones.addToRear(publicacion);
    }

}