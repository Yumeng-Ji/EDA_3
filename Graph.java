import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    HashMap<String, Integer> th;
    String[] keys;
    ArrayList<Integer>[] adjList;

    public void crearGrafo(Repositorio lista) {
        // Post: crea el grafo desde la lista de autores
        //       Los nodos son nombres de autores

        // Paso 1: llenar th
        // COMPLETAR CÓDIGO
        int id = 0;
        for (Autor autor : lista.getListaAutores().values())
            th.put(autor.getNombre(), id++);

        // Paso 2: llenar keys
        keys = new String[th.size()];
        for (String nombre : th.keySet())
            keys[th.get(nombre)] = nombre;

        // Paso 3: llenar adjList
        // COMPLETAR CÓDIGO
        adjList = (ArrayList<Integer>[]) new ArrayList[th.size()];
        ArrayList<Integer> autoresPub = null;
        for (Publicacion pub : lista.getListaPublicaciones().values()) {
            autoresPub = new ArrayList<>();
            for (Autor a : pub.getListaAutores()) {
                if (th.containsKey(a.getNombre()))
                    autoresPub.add(th.get(a.getNombre()));
            }
        }
        // Añadir enlaces entre todos los autores de la publicación
        for (int i = 0; i < autoresPub.size(); i++) {
            for (int j = i + 1; j < autoresPub.size(); j++){
                int x = autoresPub.get(i);
                int y = autoresPub.get(j);
                adjList[x].add(y);
                adjList[y].add(x);
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
        Queue<Integer> porExaminar = new LinkedList<Integer>();

        int pos1 = th.get(a1);
        int pos2 = th.get(a2);
        boolean enc = false;
        boolean[] examinados = new boolean[th.size()];

        // COMPLETAR CÓDIGO

        return enc;

    }

    public ArrayList<String> estanConectados(String a1, String a2){

        // COMPLETAR CÓDIGO

        return null;

    }

}
