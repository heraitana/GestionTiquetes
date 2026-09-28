///

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Estructura Enlazada(1,2,3,4) no Anexada(0,1,2,3)
        GestionTiquetes gestor = new GestionTiquetes();

        int opcionMenu = 0;
        while (opcionMenu != 3) {
            // Mostrar menú
            String textoMenu = """
                    
                     1. Menú Administrador
                     2. Menú Usuario
                     3. Salir
                    
                    """;

            try {
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(textoMenu));
            } catch (NumberFormatException e) {
                opcionMenu = 0;
            }

            // Evaluar la opción
            switch (opcionMenu) {

                case 1:
                    int opcionSubMenu = 0;
                    while (opcionSubMenu != 3) {

                        String textoSubMenu = """
                                 1. Visualizar Tiquete Al Frente
                                 2. Resolver Tiquete Al Frente
                                 3. Volver al Menú Principal                                    
                                """;

                        try {
                            opcionSubMenu = Integer.parseInt(JOptionPane.showInputDialog(textoSubMenu));
                        } catch (NumberFormatException e) {
                            opcionSubMenu = 0;
                        }

                        switch (opcionSubMenu) {
                            case 1:
                                gestor.VisualizarTiquete();
                                break;
                            case 2:
                                gestor.ResolverTiquete();
                                break;
                            case 3:
                                // Salir del submenú y volver al menú principal
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 2: // Menú Usuario
                    int opcionSubMenu2 = 0;
                    while (opcionSubMenu2 != 3) {

                        String textoSubMenu = """
                                 1. Crear un Tiquete
                                 2. Buscar un Tiquete Resuelto
                                 3. Volver al Menú Principal                                    
                                """;

                        try {
                            opcionSubMenu2 = Integer.parseInt(JOptionPane.showInputDialog(textoSubMenu));
                        } catch (NumberFormatException e) {
                            opcionSubMenu2 = 0;
                        }

                        switch (opcionSubMenu2) {
                            case 1:
                                gestor.CrearTiquete();
                                break;
                            case 2:
                                gestor.BuscarTiquete();
                                break;
                            case 3:
                                // Salir del submenú y volver al menú principal
                                break;
                            default:
                                break;
                        }
                    }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error! La opción " + opcionMenu + " no es válida");

            }
        }
    }
}
