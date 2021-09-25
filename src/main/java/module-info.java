module com.zloibob43.filemanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.zloibob43.filemanager to javafx.fxml;
    exports com.zloibob43.filemanager;
}