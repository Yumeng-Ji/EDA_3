import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    HashMap<String, Integer> th;
    String[] keys;
    ArrayList<Integer>[] adjList;

    public void crearGrafo(ArrayList<Autor> lista) {
        // Post: crea el grafo desde la lista de autores
        //       Los nodos son nombres de autores

        // Paso 1: llenar th
        // COMPLETAR CÓDIGO
        th = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            th.put(lista.get(i).getNombre(), i);
        }

        // Paso 2: llenar keys
        keys = new String[th.size()];
        for (String k : th.keySet()) {
            keys[th.get(k)] = k;
        }

        // Paso 3: llenar adjList
        // COMPLETAR CÓDIGO
        adjList = (ArrayList<Integer>[]) new ArrayList[th.size()];
        for (int i = 0; i < adjList.length(); i++) {
            adjList[i] = new ArrayList<>();
        }
        // Construir relaciones basadas en co-autoría
        for (Autor autor : lista) {
            int idAutor = th.get(autor.getNombre());
            // Obtener todas las publicaciones del autor
            HashSet<Publicacion> publicaciones = autor.getListaPublicaciones();
            // Para cada publicacion, obtener los co-autores
            for (Publicacion pub : publicaciones) {
                HashSet<Autor> autoresPub = pub.getListaAutores();
                // Crear conexiones entre todos los autores de esta publicacion
                for (Autor coAutor : autoresPub) {
                    if (!coAutor.getNombre().equals(autor.getNombre())) { // Verificar que no es el mismo autor
                        if (th.containsKey(coAutor.getNombre())) { // Verificar que está en la lista
                            int idCoautor = th.get(coAutor.getNombre());
                            // Añadir conexión bidireccional si no existe
                            if (!adjList[idAutor].contains(idCoautor)) {
                                adjList[idAutor].add(idCoautor);
                            }
                            if (!adjList[idCoautor].contains(idAutor)) {
                                adjList[idCoautor].add(idAutor);
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
        if (!th.containsKey(a1) || !th.containsKey(a2)) return false; //Verificar que estan en la lista
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
                if (i==pos2){
                    enc=true; // Encontrado
                }
                if (!examinados[i]) {
                    examinados[i] = true;
                    porExaminar.add(i);
                }
            }
        }

        return enc; 


    }

    public ArrayList<String> estanConectados(String a1, String a2){

        
        if (!th.containsKey(a1) || !th.containsKey(a2)) return null;
        int pos1 = th.get(a1);
        int pos2 = th.get(a2);
        ArrayList<String> camino= new ArrayList<>();
        // Si son el mismo autor
        if (pos1 == pos2) {
            camino.add(keys[pos1]);
            return camino;
        }
        Queue<Integer> porExaminar = new LinkedList<>();
        boolean[] examinados = new boolean[th.size()];
        int[] backPos = new int[th.size()];

        //Inicializar backPos
        for (int i = 0; i < backPos.length; i++) backPos[i] = -1;
        porExaminar.add(pos1);
        examinados[pos1] = true;
        boolean enc = false;

        while (!porExaminar.isEmpty() && !enc) {
            int actual = porExaminar.poll();

            for (int i : adjList[actual]) {
                if (i == pos2) {
                    backPos[i] = actual;
                    enc = true;
                }
                if (!examinados[i]) {
                    examinados[i] = true;
                    backPos[i] = actual;
                    porExaminar.add(i);
                }
            }
        }
            if (!enc) return null;// No hay camino

            Stack<String> pila = new Stack<>();
            int aux = pos2;
            while(aux!=-1){
                pila.push(keys[aux]);
                aux = backPos[aux];
            }
            while (!pila.isEmpty()){
                camino.add(pila.pop());
            }
            return camino;
        }

}





