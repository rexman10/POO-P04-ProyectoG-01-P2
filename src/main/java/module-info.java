module com.mycompany.proyecto_poo_mascotas_fx_p2 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;
    requires transitive javafx.graphics;

    opens com.mycompany.proyecto_poo_mascotas_fx_p2 to javafx.fxml;
    opens com.mycompany.modelo to javafx.base;
    exports com.mycompany.proyecto_poo_mascotas_fx_p2;

}
