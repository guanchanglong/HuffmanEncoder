package sample;

import gcl.unzip.UnZip;
import gcl.zip.Zip;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(final Stage stage) {

        stage.setTitle("哈夫曼编译码器");

        final Button openButton = new Button("压缩");
        final Button openMultipleButton = new Button("解压缩");

        //压缩
        openButton.setOnAction(
                (final ActionEvent e) -> {
                    FileChooser fileChooserToZip = new FileChooser();
                    configureFileChooserToZip(fileChooserToZip);
                    //showOpenDialog 方法打开一个文件选择窗口。这个方法的返回值就是用户选择的文件，如果没有选择返回null
                    File file = fileChooserToZip.showOpenDialog(stage);
                    if (file != null) {
                        Zip.zipTotal(file);
                    }
                });

        //解压缩
        openMultipleButton.setOnAction(
                (final ActionEvent e) -> {
                    FileChooser fileChooserToUnZip = new FileChooser();
                    configureFileChooserToUnZip(fileChooserToUnZip);
                    File file = fileChooserToUnZip.showOpenDialog(stage);
                    if (file != null) {
                        UnZip.unZipTotal(file);
                    }
                });

//        BorderPane root = new BorderPane();
//        Image image = new Image("resource/img/图片2.jpg");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        root.setCenter(imageView);
//        double w = image.getWidth();
//        double h = image.getHeight();

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 1);
        GridPane.setConstraints(openMultipleButton, 1, 1);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, openMultipleButton);

        final Pane root = new VBox(12);
        root.setPadding(new Insets(200, 200, 200, 200));
        stage.setScene(new Scene(root));
        root.getChildren().addAll(inputGridPane);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * 文件选择器
     * @param fileChooser
     */
    private static void configureFileChooserToZip(
            FileChooser fileChooser) {
        fileChooser.setTitle("打开待压缩的文件");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
    }

    private static void configureFileChooserToUnZip(
            FileChooser fileChooser) {
        fileChooser.setTitle("打开待解压缩的文件");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ZIP", "*.zip")
        );
    }
}
