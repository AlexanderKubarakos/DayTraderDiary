module com.group.projectsrc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens com.group50.projectsrc to javafx.fxml;
    exports com.group50.projectsrc;
}