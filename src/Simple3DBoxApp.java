import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Simple3DBoxApp extends Application {
	/**
	 * Java main for when running without JavaFX launcher
	 */
	Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}
	public Parent createContent() throws Exception {

		// Box
		Box testBox = new Box(5, 5, 5);
		//Sphere testSphere = new Sphere(3);
		testBox.setMaterial(new PhongMaterial(Color.RED));
		//testSphere.setMaterial(new PhongMaterial(Color.POWDERBLUE));
		testBox.setDrawMode(DrawMode.FILL);
		//testSphere.setDrawMode(DrawMode.FILL);
		
		//Events
		testBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				System.out.println("Click");
			}
		});
		testBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		        testBox.setMaterial(new PhongMaterial(new Color(Math.random(), Math.random(),Math.random(), Math.random())));
		    }
		});
		testBox.setOnMouseExited(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		        testBox.setMaterial(new PhongMaterial(new Color(Math.random(), Math.random(),Math.random(), Math.random())));
		    }
		});
		
		// Create and position camera
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll (
				new Rotate(-20, Rotate.Y_AXIS),
				new Rotate(-20, Rotate.X_AXIS),
				new Translate(0, 0, -15));

		// Build the Scene Graph
		Group root = new Group();       
		root.getChildren().add(camera);
		root.getChildren().add(testBox);
		//root.getChildren().add(testSphere);

		// Use a SubScene       
		SubScene subScene = new SubScene(root,500,500);
		subScene.setFill(Color.ALICEBLUE);
		subScene.setCamera(camera);
		Group group = new Group();
		group.getChildren().add(subScene);
		return group;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(true);
		Scene scene = new Scene(createContent());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}