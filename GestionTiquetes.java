
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class GestionTiquetes {

    // Cola de prioridad para los tickets PENDIENTES (ordenados por id)
    private PriorityQueue<Ticket> colaPendientes;

    // Lista enlazada simple para los tickets RESUELTOS
    private Ticket primeroResueltos;

    public GestionTiquetes() {
        colaPendientes = new PriorityQueue<>(Comparator.comparingInt(t -> Integer.parseInt(t.getId())));
        primeroResueltos = null;
    }

    private boolean estaVacia() {
        return primeroResueltos == null;
    }

    // Inserta al inicio de la lista enlazada de resueltos
    public void insertar(Ticket nodo) {
        nodo.setSiguiente(primeroResueltos);
        primeroResueltos = nodo;
    }

    // Busca en la lista enlazada de resueltos por id
    public Ticket buscar(String id) {
        if (estaVacia()) {
            System.out.println("No hay tiquetes resueltos.\n");
            return null;
        }
        Ticket temp = primeroResueltos;
        while (temp != null) {
            if (id.equals(temp.getId())) return temp;
            temp = temp.getSiguiente();
        }
        System.out.println("El tiquete buscado no está en la lista de resueltos");
        return null;
    }

    //Usuario
    public void CrearTiquete() {
        String nombreCompleto = JOptionPane.showInputDialog("Ingrese su nombre completo:");
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del problema:");
        if (descripcion == null || descripcion.trim().isEmpty()) return;

        Ticket nuevo = new Ticket(nombreCompleto, descripcion, new Date());

        colaPendientes.add(nuevo);

        JOptionPane.showMessageDialog(null, "¡Tiquete #" + nuevo.getId() + " creado y enviado a la cola de espera!");
    }

    public void BuscarTiquete() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del tiquete a buscar:");
        if (id == null || id.trim().isEmpty()) return;

        Ticket encontrado = buscar(id);

        if (encontrado != null) {
            JOptionPane.showMessageDialog(null, "--- Tiquete Resuelto Encontrado ---\n" + encontrado);
        } else {
            JOptionPane.showMessageDialog(null, "El tiquete #" + id + " se encuentra PENDIENTE de solución.");
        }
    }
    //Fin de Usuario

    //Admin
    public void VisualizarTiquete() {
        if (colaPendientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tiquetes pendientes en la cola.");
            return;
        }
        Ticket alFrente = colaPendientes.peek();
        JOptionPane.showMessageDialog(null, "Tiquete al frente de la cola:\n" + alFrente);
    }

    public void ResolverTiquete() {
        if (colaPendientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tiquetes pendientes para resolver.");
            return;
        }

        Ticket resuelto = colaPendientes.poll();
        resuelto.setFechaResolucion(new Date());

        insertar(resuelto);

        JOptionPane.showMessageDialog(null, "El tiquete #" + resuelto.getId() + " ha sido resuelto:\n" + resuelto);
    }
    //Fin de Admin




















    // Clase Ticket: representa cada tiquete con sus atributos
    private class Ticket {
        private static int cantidad = 1; // Contador para generar el id automáticamente

        private String id;
        private String nombreCompleto;
        private String descripcion;
        private Date fechaCreacion;
        private Date fechaResolucion;
        private Ticket siguiente; // Puntero al siguiente nodo en la lista enlazada

        public Ticket(String nombreCompleto, String descripcion, Date fechaCreacion) {
            this.id = String.valueOf(cantidad++);
            this.nombreCompleto = nombreCompleto;
            this.descripcion = descripcion;
            this.fechaCreacion = fechaCreacion;
            this.fechaResolucion = null; // Empieza en null
            this.siguiente = null;
        }

        public String getId() {
            return id;
        }

        public void setFechaResolucion(Date fechaResolucion) {
            this.fechaResolucion = fechaResolucion;
        }

        public Ticket getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Ticket siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public String toString() {
            return "ID: " + id +
                    "\nNombre: " + nombreCompleto +
                    "\nDescripción: " + descripcion +
                    "\nFecha de Creación: " + fechaCreacion +
                    "\nFecha de Resolución: " + (fechaResolucion == null ? "PENDIENTE" : fechaResolucion);
        }
    }
}