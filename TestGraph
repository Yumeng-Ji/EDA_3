import java.util.ArrayList;
import java.io.IOException;


public class TestGraph {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL GRAFO ===");

        try {

            // PRIMERA PARTE: Prueba con datos reales
            System.out.println("\n--- PRUEBA CON DATOS REALES ---");
            testConDatosReales();

            // SEGUNDA PARTE: Prueba con datos de prueba
            System.out.println("\n--- PRUEBA CON DATOS DE PRUEBA ---");
            testConDatosPrueba();

        } catch (Exception e) {
            System.out.println("Error en las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testConDatosReales() throws IOException {
        Repositorio repo = Repositorio.getRepositorio();

        // Cargar datos reales
        System.out.println("Cargando datos ...");
        repo.cargarAutor("/Users/nimilama/IdeaProjects/EDA_3/out/Datuak/authors-name-all.txt");
        repo.cargarPublicacion("/Users/nimilama/IdeaProjects/EDA_3/out/Datuak/publications-titles-all.txt");
        repo.cargarPublicacionesAutores("/Users/nimilama/IdeaProjects/EDA_3/out/Datuak/publications-authors-all-final.txt");

        ArrayList<Autor> listaAutores = new ArrayList<>(repo.getListaAutores().values());

        System.out.println("Autores cargados: " + listaAutores.size());
        System.out.println("Publicaciones cargadas: " + repo.getListaPublicaciones().size());

        // Crear grafo
        Graph grafo = new Graph();
        System.out.println("Creando grafo ...");
        grafo.crearGrafo(listaAutores);
        System.out.println("Grafo creado exitosamente");

        // Estadísticas básicas
        System.out.println("Nodos: " + grafo.th.size());
        int totalRelaciones = 0;
        for (ArrayList<Integer> lista : grafo.adjList) {
            totalRelaciones += lista.size();
        }
        totalRelaciones /= 2;
        System.out.println("Relaciones: " + totalRelaciones);

        // Probar con algunos autores reales
        if (listaAutores.size() >= 3) {
            String autor1 = listaAutores.get(0).getNombre();
            String autor2 = listaAutores.get(1).getNombre();
            String autor3 = listaAutores.get(2).getNombre();

            System.out.println("\nProbando conectividad:");
            System.out.println(autor1 + " <-> " + autor2 + ": " + grafo.estanConectados(autor1, autor2));
            System.out.println(autor1 + " <-> " + autor3 + ": " + grafo.estanConectados(autor1, autor3));

            ArrayList<String> camino = grafo.estanConectados(autor1, autor2);
            if (camino != null) {
                System.out.println("Camino encontrado: " + camino.size() + " autores");
            }
        }

        // Limpiar para la siguiente prueba
        repo.getListaAutores().clear();
        repo.getListaPublicaciones().clear();
    }

    private static void testConDatosPrueba() {
        Repositorio repo = Repositorio.getRepositorio();

        // Crear datos de prueba simples
        System.out.println("Creando datos de prueba...");

        // Autores
        repo.añadirAutor("A1", "Autor 1");
        repo.añadirAutor("A2", "Autor 2");
        repo.añadirAutor("A3", "Autor 3");
        repo.añadirAutor("A4", "Autor 4");
        repo.añadirAutor("A5", "Autor 5");

        // Publicaciones
        repo.añadirPublicacion("P1", "Publicacion 1");
        repo.añadirPublicacion("P2", "Publicacion 2");
        repo.añadirPublicacion("P3", "Publicacion 3");

        // Relaciones: A1-A2-A3 y A1-A4-A5
        repo.añadirAutorAPublicacion("A1", "P1");
        repo.añadirAutorAPublicacion("A2", "P1");

        repo.añadirAutorAPublicacion("A2", "P2");
        repo.añadirAutorAPublicacion("A3", "P2");

        repo.añadirAutorAPublicacion("A1", "P3");
        repo.añadirAutorAPublicacion("A4", "P3");
        repo.añadirAutorAPublicacion("A5", "P3");

        ArrayList<Autor> listaAutores = new ArrayList<>(repo.getListaAutores().values());

        // Crear grafo
        Graph grafo = new Graph();
        System.out.println("Creando grafo con datos de prueba...");
        grafo.crearGrafo(listaAutores);
        System.out.println("Grafo creado exitosamente");

        // Pruebas de conectividad
        System.out.println("\n--- PRUEBAS DE CONECTIVIDAD ---");
        System.out.println("A1 <-> A2: " + grafo.estanConectados("Autor 1", "Autor 2"));
        System.out.println("A1 <-> A3: " + grafo.estanConectados("Autor 1", "Autor 3"));
        System.out.println("A1 <-> A5: " + grafo.estanConectados("Autor 1", "Autor 5"));
        System.out.println("A2 <-> A5: " + grafo.estanConectados("Autor 2", "Autor 5"));

        // Pruebas de caminos
        System.out.println("\n--- PRUEBAS DE CAMINOS ---");
        ArrayList<String> camino1 = grafo.estanConectados("Autor 1", "Autor 3");
        System.out.println("Camino A1->A3: " + camino1);

        ArrayList<String> camino2 = grafo.estanConectados("Autor 1", "Autor 5");
        System.out.println("Camino A1->A5: " + camino2);

        ArrayList<String> camino3 = grafo.estanConectados("Autor 2", "Autor 5");
        System.out.println("Camino A2->A5: " + camino3);

        // Mostrar estructura del grafo
        System.out.println("\n--- ESTRUCTURA DEL GRAFO ---");
        grafo.print();
    }



}
