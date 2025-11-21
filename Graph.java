import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    HashMap<String, Integer> th;
    String[] keys;
    ArrayList<Integer>[] adjList;

    public void crearGrafo(ArrayList<Autor> lista){
        // Post: crea el grafo desde la lista de autores
        //       Los nodos son nombres de autores

                // Paso 1: llenar th�
        // COMPLETAR C�DIGO
        th = new HashMap<>();
        for(int i = 0; i < lista.size(); i++)
            th.put(lista.get(i).getNombre(), i);


        // Paso 2: llenar keys
        keys = new String[th.size()];
        for (String k: th.keySet()) keys[th.get(k)] = k;

        // Paso 3: llenar adjList�
        // COMPLETAR C�DIGO
        adjList = new ArrayList[th.size()];
        for (int i = 0; i < adjList.length; i++) adjList[i] = new ArrayList<>();

        // Construir relaciones basadas en co-autoría
         HashMap<String, Autor> listaAutores= Repositorio.getRepositorio().getListaAutores();
        for (Autor autor : lista) {
            int indiceAutor = th.get(autor.getNombre());

            // Obtener todas las publicaciones del autor
            UnorderedDoubleLinkedList<Publicacion> publicaciones = autor.getListaPublicaciones();

            // Para cada publicacion, obtener los co-autores
            for (Publicacion pub : publicaciones) {
                UnorderedDoubleLinkedList<Autor> autoresPub = pub.getListaAutores();

                // Crear conexiones entre todos los autores de esta publicacion
                for (Autor coAutor : autoresPub) {
                    if (!coAutor.getNombre().equals(autor.getNombre())) {
                        if (th.containsKey(coAutor.getNombre())) {
                        int indiceCoAutor = th.get(coAutor.getNombre());

                            // Añadir conexion bidireccional
                            if (!adjList[indiceAutor].contains(indiceCoAutor)) {
                                adjList[indiceAutor].add(indiceCoAutor);
                            }
                            if (!adjList[indiceCoAutor].contains(indiceAutor)) {
                                adjList[indiceCoAutor].add(indiceAutor);
                            }
                        }
                    }
                }
            }
        }

    }

    public void print(){
        for (int i = 0; i < adjList.length; i++){
            System.out.print("Element: " + i + " " + keys[i] + " --> ");
            for (int k: adjList[i])  System.out.print(keys[k] + " ### ");

            System.out.println();
        }
    }

    public boolean estanConectados(String a1, String a2){
        if (!th.containsKey(a1) || !th.containsKey(a2)) return false;
        Queue<Integer> porExaminar = new LinkedList<Integer>();

        int pos1 = th.get(a1);
        int pos2 = th.get(a2);
        boolean enc = false;
        boolean[] examinados = new boolean[th.size()];
        // Si son el mismo autor
        if (pos1 == pos2) return true;

        porExaminar.add(pos1);
        examinados[pos1] = true;

        while (!porExaminar.isEmpty() && !enc) {
            int actual = porExaminar.poll();
            for (int i : adjList[actual]) {
                if (!examinados[i]) {
                    if (i==pos2){
                        enc = true;
                    }
                    examinados[i] = true;
                    porExaminar.add(i);
                }
            }
        }

        // COMPLETAR CÓDIGO

        return enc;

    }

    public ArrayList<String> estanConectados(String a1, String a2){

        // COMPLETAR CÓDIGO

        return null;

    }


}


