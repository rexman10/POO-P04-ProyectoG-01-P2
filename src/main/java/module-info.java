module com.mycompany.proyecto_poo_mascotas_fx_p2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires transitive javafx.graphics;

    opens com.mycompany.proyecto_poo_mascotas_fx_p2 to javafx.fxml;
    opens modelo to javafx.base;
    exports com.mycompany.proyecto_poo_mascotas_fx_p2;

}
