package elegit.controllers;

import elegit.Main;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by makik on 7/8/15.
 */
public class BusyWindow {

    public static final Stage window = initWindow();

    private static int numProcessesActive = 0;

    private static Text loadingMessage;

    private static final Logger logger = LogManager.getLogger();
    private static final Logger console = LogManager.getLogger("briefconsolelogger");

    private static Stage initWindow(){
        Main.assertFxThread();
        Stage window = new Stage();

        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);

        loadingMessage = new Text("Loading...");
        loadingMessage.setFont(new Font(20));

        window.setScene(new Scene(getRootOfScene()));

        return window;
    }

    // link to .gif loading indicator creator: http://www.ajaxload.info
    private static Parent getRootOfScene(){
        Main.assertFxThread();
        ProgressBar pb = new ProgressBar();
        pb.setProgress(-1); // indicates indeterminate
        VBox parent = new VBox(pb, loadingMessage);
        parent.setSpacing(20);
        parent.setAlignment(Pos.CENTER);
        parent.setStyle("-fx-border-color: #000000;\n -fx-padding: 10 10 10 10");
        return parent;
    }

    public static void setParentWindow(Window parent){
        Main.assertFxThread();
        window.initOwner(parent);
        window.setX(parent.getX()+parent.getWidth()/2-window.getWidth()/2);
        window.setY(parent.getY()+parent.getHeight()/2-window.getHeight()/2);
    }

    public static void show(){
        Main.assertFxThread();

        if(!window.isShowing()) {
            Window parent = window.getOwner();
            double windowWidth, windowHeight;
            // If window hasn't been shown before, getWidth() will be NaN
            // so we have to have a default
            if (window.getWidth()>0) {
                windowWidth = window.getWidth();
                windowHeight = window.getHeight();
            } else {
                windowWidth = 148;
                windowHeight = 82;
            }
            window.setX(parent.getX()+parent.getWidth()/2-windowWidth/2);
            window.setY(parent.getY()+parent.getHeight()/2-windowHeight/2);
            window.setMinWidth(loadingMessage.getBoundsInLocal().getWidth() + 20);
            window.show();
        } else {
            throw new RuntimeException("BusyWindow is already showing.");
        }
    }

    /**
     * Occasionally this hide method may be called even if the window is not showing, such as when Elegit is
     * starting up.
     */
    public static void hide(){
        Main.assertFxThread();
        if(window.isShowing()) {
            logger.info("Hiding busy window");
            window.hide();
            loadingMessage.setText("Loading...");
        }
    }

    public static void setLoadingText(String message) {
        Main.assertFxThread();
        loadingMessage.setText(message);
    }

    public static boolean isShowing() {
        Main.assertFxThread();
        return window.isShowing();
    }

}
