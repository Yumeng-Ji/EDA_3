import java.util.*;
import java.io.*;

public class Publicacion {

    private String id;
    private String titulo;
    private UnorderedDoubleLinkedList<Autor> listaAutores;
    private UnorderedDoubleLinkedList<Publicacion> listaCitadas;

    public Publicacion(String pId, String pTitulo) {
        this.id = pId;
        this.titulo = pTitulo;
        this.listaAutores = new UnorderedDoubleLinkedList<Autor>();
        this.listaCitadas = new UnorderedDoubleLinkedList<Publicacion>();
    }

    public String getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public UnorderedDoubleLinkedList<Autor> getListaAutores() {
        return this.listaAutores;
    }

    public UnorderedDoubleLinkedList<Publicacion> getListaCitadas() {
        return this.listaCitadas;
    }

    public void addAutor(Autor autor) {
        listaAutores.addToRear(autor);
    }

    public void addCitada(Publicacion publicacion) {
        listaCitadas.addToRear(publicacion);
    }

    public void añadirCitaPorId(String pCitaId) {
        Publicacion pub = Repositorio.getRepositorio().buscarPublicacionPorId(pCitaId);
        listaCitadas.addToRear(pub);
    }
}