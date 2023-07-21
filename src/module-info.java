module Shalom_1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires mysql.connector.j;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base,javafx.controls,java.sql;
	opens ClassDesignForDB to javafx.graphics, javafx.fxml,javafx.base,javafx.controls,java.sql;
}
