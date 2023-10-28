/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2media;

import desafio2media.Clases.Libro;
import desafio2media.conexion.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josue
 */
public class Desafio2Media extends JApplet {

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static boolean mostrarMenu = true;

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        while (true) {
            if (mostrarMenu) {
                String opcion = JOptionPane.showInputDialog("1. Agregar Material\n2. Modificar Material\n3. Listar Material\n4. Salir");
                switch (opcion) {
                    case "1":
                        agregarMaterial();
                        break;
                    case "2":
                        modificarMaterial();
                        break;
                    case "3":
                        listarMateriales();
                        mostrarMenu = false;  // No mostrar el menú mientras se lista los materiales
                        break;
                    case "4":
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            }
        }
    }

    public static void agregarMaterial() {
        String[] options = {"Libro", "Revista", "CD", "DVD"};
        int x = JOptionPane.showOptionDialog(null, "Selecciona el tipo de material que deseas agregar",
                "Agregar Material",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (x) {
            case 0:
                agregarLibro();
                break;
            case 1:
//                agregarRevista();
                break;
            case 2:
//                agregarCD();
                break;
            case 3:
//                agregarDVD();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }

    public static void agregarLibro() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del libro:");
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
        int idGenero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del género del libro:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad en stock del libro:"));
        int idAutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del autor del libro:"));
        int idEditorial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la editorial del libro:"));
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");

        Libro libro = new Libro(id, titulo, idGenero, stock, idAutor, idEditorial, isbn);
        guardarLibroEnBD(libro);
    }

    public static void guardarLibroEnBD(Libro libro) {
        Connection conexion = ConexionBD.getConnection();
        if (conexion != null) {
            try {
                // Insertar en la tabla materiales
                String sqlMaterial = "INSERT INTO materiales (IdMaterial, Titulo, IdGenero, Stock) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psMaterial = conexion.prepareStatement(sqlMaterial)) {
                    psMaterial.setString(1, libro.getId());
                    psMaterial.setString(2, libro.getTitulo());
                    psMaterial.setInt(3, libro.getIdGenero());
                    psMaterial.setInt(4, libro.getStock());
                    psMaterial.executeUpdate();
                }

                // Insertar en la tabla materiales_escritos
                String sqlMaterialEscrito = "INSERT INTO materiales_escritos (IdMaterial, IdAutor, IdEditorial) VALUES (?, ?, ?)";
                try (PreparedStatement psMaterialEscrito = conexion.prepareStatement(sqlMaterialEscrito)) {
                    psMaterialEscrito.setString(1, libro.getId());
                    psMaterialEscrito.setInt(2, libro.getIdAutor());
                    psMaterialEscrito.setInt(3, libro.getIdEditorial());
                    psMaterialEscrito.executeUpdate();
                }

                // Insertar en la tabla libros
                String sqlLibro = "INSERT INTO libros (IdMaterial, ISBN) VALUES (?, ?)";
                try (PreparedStatement psLibro = conexion.prepareStatement(sqlLibro)) {
                    psLibro.setString(1, libro.getId());
                    psLibro.setString(2, libro.getIsbn());
                    psLibro.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Libro agregado exitosamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar el libro: " + e.getMessage());
            } finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos");
        }
    }

    public static void modificarMaterial() {
        String[] tiposDeMaterial = {"Libro", "Revista", "CD", "DVD", "Cancelar"};
        String tipoMaterial = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de material que desea modificar:", "Modificar Material", JOptionPane.QUESTION_MESSAGE, null, tiposDeMaterial, tiposDeMaterial[0]);

        if (tipoMaterial != null && !"Cancelar".equals(tipoMaterial)) {
            String query = "SELECT materiales.IdMaterial, materiales.Titulo FROM materiales ";
            switch (tipoMaterial) {
                case "Libro":
                    query += "INNER JOIN materiales_escritos ON materiales.IdMaterial = materiales_escritos.IdMaterial INNER JOIN libros ON materiales_escritos.IdMaterial = libros.IdMaterial";
                    break;
                case "Revista":
                    query += "INNER JOIN materiales_escritos ON materiales.IdMaterial = materiales_escritos.IdMaterial INNER JOIN revistas ON materiales_escritos.IdMaterial = revistas.IdMaterial";
                    break;
                case "CD":
                    query += "INNER JOIN materiales_audiovisuales ON materiales.IdMaterial = materiales_audiovisuales.IdMaterial INNER JOIN cds ON materiales_audiovisuales.IdMaterial = cds.IdMaterial";
                    break;
                case "DVD":
                    query += "INNER JOIN materiales_audiovisuales ON materiales.IdMaterial = materiales_audiovisuales.IdMaterial INNER JOIN dvds ON materiales_audiovisuales.IdMaterial = dvds.IdMaterial";
                    break;
            }

            try {
                conn = ConexionBD.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                while (rs.next()) {
                    String idMaterial = rs.getString("IdMaterial");
                    String titulo = rs.getString("Titulo");
                    model.addElement(idMaterial + " - " + titulo);
                }

                JComboBox<String> comboBox = new JComboBox<>(model);
                int resultado = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione el material a modificar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (resultado == JOptionPane.OK_OPTION) {
                    String selectedItem = (String) comboBox.getSelectedItem();
                    if (selectedItem != null) {
                        String idMaterial = selectedItem.split(" - ")[0];
                        mostrarDetallesMaterial(idMaterial);
                        modificarCampoMaterial(idMaterial, tipoMaterial);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al obtener la lista de materiales: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void modificarCampoMaterial(String idMaterial, String tipoMaterial) {
        String[] camposAModificar;
        switch (tipoMaterial) {
            case "Libro":
            case "Revista":
                camposAModificar = new String[]{"Titulo", "Genero", "Stock", "Autor", "Editorial", "Cancelar"};
                break;
            case "CD":
            case "DVD":
                camposAModificar = new String[]{"Titulo", "Genero", "Stock", "Duracion", "Cancelar"};
                break;
            default:
                return;
        }

        String campoAModificar = (String) JOptionPane.showInputDialog(null, "Seleccione el campo que desea modificar:", "Modificar Material", JOptionPane.QUESTION_MESSAGE, null, camposAModificar, camposAModificar[0]);

        if (campoAModificar != null && !"Cancelar".equals(campoAModificar)) {
            String nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor para " + campoAModificar + ":");
            if (nuevoValor != null && !nuevoValor.trim().isEmpty()) {
                actualizarCampoMaterial(idMaterial, campoAModificar, nuevoValor, tipoMaterial);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha ingresado un nuevo valor. Cancelando operación.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
        }
    }

    public static void actualizarCampoMaterial(String idMaterial, String campoAModificar, String nuevoValor, String tipoMaterial) {
        String query = "UPDATE materiales SET ";
        switch (campoAModificar) {
            case "Titulo":
            case "Genero":
            case "Stock":
                query += campoAModificar + " = ? WHERE IdMaterial = ?";
                break;
            case "Autor":
            case "Editorial":
                if ("Libro".equals(tipoMaterial) || "Revista".equals(tipoMaterial)) {
                    query = "UPDATE materiales_escritos SET " + campoAModificar + " = ? WHERE IdMaterial = ?";
                } else {
                    JOptionPane.showMessageDialog(null, "Campo no válido para el tipo de material seleccionado.");
                    return;
                }
                break;
            case "Duracion":
                if ("CD".equals(tipoMaterial) || "DVD".equals(tipoMaterial)) {
                    query = "UPDATE materiales_audiovisuales SET Duracion = ? WHERE IdMaterial = ?";
                } else {
                    JOptionPane.showMessageDialog(null, "Campo no válido para el tipo de material seleccionado.");
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Campo no reconocido.");
                return;
        }

        try {
            conn = ConexionBD.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nuevoValor);
            pstmt.setString(2, idMaterial);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Material actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el material.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el material: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mostrarDetallesMaterial(String idMaterial) {
        String query = "SELECT * FROM materiales WHERE IdMaterial = '" + idMaterial + "'";
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                StringBuilder detalles = new StringBuilder();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    detalles.append(metaData.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, detalles.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el material con el ID especificado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar los detalles del material: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void listarMateriales() {
        String[] opciones = {"Listar Todo", "Listar Contenido Escrito", "Listar Contenido Audiovisual", "Volver"};
        int eleccion = JOptionPane.showOptionDialog(null, "Elige una opción", "Listar Materiales",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                listarTodo();
                break;
            case 1:
                listarContenidoEscrito();
                break;
            case 2:
                listarContenidoAudiovisual();
                break;
            case 3:
                mostrarMenu = true;

                Menu();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
        mostrarMenu = true;
    }

    public static void listarTodo() {
        String query = "SELECT m.IdMaterial, m.Titulo, g.Nombre as NombreGenero, m.Stock, "
                + "a.Nombre as NombreAutor, e.Nombre as NombreEditorial, l.ISBN, r.Periodicidad, "
                + "ma.Duracion, cd.NumCanciones, d.Nombre as NombreDirector "
                + "FROM materiales m "
                + "LEFT JOIN generos g ON m.IdGenero = g.IdGenero "
                + "LEFT JOIN materiales_escritos me ON m.IdMaterial = me.IdMaterial "
                + "LEFT JOIN autores a ON me.IdAutor = a.IdAutor "
                + "LEFT JOIN editoriales e ON me.IdEditorial = e.IdEditorial "
                + "LEFT JOIN libros l ON me.IdMaterial = l.IdMaterial "
                + "LEFT JOIN revistas r ON me.IdMaterial = r.IdMaterial "
                + "LEFT JOIN materiales_audiovisuales ma ON m.IdMaterial = ma.IdMaterial "
                + "LEFT JOIN cds cd ON ma.IdMaterial = cd.IdMaterial "
                + "LEFT JOIN dvds dvd ON ma.IdMaterial = dvd.IdMaterial "
                + "LEFT JOIN autores d ON dvd.IdDirector = d.IdAutor";
        mostrarMateriales(query);
    }

    public static void listarContenidoEscrito() {
        String query = "SELECT m.IdMaterial, m.Titulo, g.Nombre as NombreGenero, m.Stock, "
                + "a.Nombre as NombreAutor, e.Nombre as NombreEditorial, l.ISBN, r.Periodicidad "
                + "FROM materiales m "
                + "LEFT JOIN generos g ON m.IdGenero = g.IdGenero "
                + "LEFT JOIN materiales_escritos me ON m.IdMaterial = me.IdMaterial "
                + "LEFT JOIN autores a ON me.IdAutor = a.IdAutor "
                + "LEFT JOIN editoriales e ON me.IdEditorial = e.IdEditorial "
                + "LEFT JOIN libros l ON me.IdMaterial = l.IdMaterial "
                + "LEFT JOIN revistas r ON me.IdMaterial = r.IdMaterial "
                + "WHERE l.IdMaterial IS NOT NULL OR r.IdMaterial IS NOT NULL";
        mostrarMateriales(query);
    }

    public static void listarContenidoAudiovisual() {
        String query = "SELECT m.IdMaterial, m.Titulo, g.Nombre as NombreGenero, m.Stock, "
                + "ma.Duracion, cd.NumCanciones, d.Nombre as NombreDirector "
                + "FROM materiales m "
                + "LEFT JOIN generos g ON m.IdGenero = g.IdGenero "
                + "LEFT JOIN materiales_audiovisuales ma ON m.IdMaterial = ma.IdMaterial "
                + "LEFT JOIN cds cd ON ma.IdMaterial = cd.IdMaterial "
                + "LEFT JOIN dvds dvd ON ma.IdMaterial = dvd.IdMaterial "
                + "LEFT JOIN autores d ON dvd.IdDirector = d.IdAutor "
                + "WHERE ma.IdMaterial IS NOT NULL";
        mostrarMateriales(query);
    }

    public static void mostrarMateriales(String query) {
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Obteniendo metadatos para construir las columnas de la tabla
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Vector<String> columnNames = new Vector<>();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // Obteniendo los datos para las filas de la tabla
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            // Creando el modelo de la tabla y configurando la JTable
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactivar auto-resize
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800, 600)); // Ajustar tamaño 

            // Mostrando la ventana con la tabla
            JFrame frame = new JFrame("Resultados");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    listarMateriales();  // Llama al menú una vez que la ventana se ha cerrado completamente
                }
            });
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar los materiales: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
