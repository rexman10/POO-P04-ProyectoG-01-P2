module com.mycompany.proyecto_poo_mascotas_fx_p2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto_poo_mascotas_fx_p2 to javafx.fxml;
    exports com.mycompany.proyecto_poo_mascotas_fx_p2;
}
