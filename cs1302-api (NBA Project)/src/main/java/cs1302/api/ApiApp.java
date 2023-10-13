package cs1302.api;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URI;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {

    /** HTTP client. */
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

    private Stage stage;
    private Scene scene;
    private VBox root;
    private HBox topBar;
    private HBox middleBar;
    private VBox bottomBar;
    private HBox topMiddleBar;

    private Label firstNameText;
    private TextField firstNameTextField;
    private Label lastNameText;
    private TextField lastNameTextField;
    private Button getPlayerButton;

    private Label noteText;
    private ImageView nbaLogoImg;

    private ImageView logoImg;
    private ImageView playerImg;

    private Label positionLabel;
    private Label heightLabel;
    private Label weightLabel;
    private Label teamLabel;

    private String uri = "";

    private String abbrev = "";
    private String firstName = "";
    private String lastName = "";

    private Image defaultPlayerImg = new Image (
        "file:resources/PersonOutline.png", 300.0, 300.0, false, false);
    private Image defaultLogoImg = new Image (
        "file:resources/AllNBALogos.jpg", 300.0, 300.0, false, false);
    private Image nbaLogo = new Image("file:resources/NBALogo.png", 65.0, 65.0, true, false);
    private Color colorRed = Color.web("#FF0000");
    private Color colorBlack = Color.web("#000000");

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public ApiApp() {
        this.stage = null;
        this.scene = null;
        this.root = new VBox(10);

        topBar = new HBox(5);
        middleBar = new HBox(20);
        bottomBar = new VBox(0);
        topMiddleBar = new HBox(20);

        firstNameText = new Label("First Name: "); //goes into topBar
        firstNameText.setMinSize(25.0, 25.0); //changes text size
        firstNameTextField = new TextField("Lebron"); //goes into topBar

        lastNameText = new Label("Last Name: "); //goes into topBar
        lastNameText.setMinSize(25.0, 25.0); //cchanges text size
        lastNameTextField = new TextField("James"); //goes into topBar
        getPlayerButton = new Button("Get Player"); //goes into topBar
        HBox.setHgrow(getPlayerButton, Priority.ALWAYS);

        noteText = new Label("Please enter the name of any NBA Player (Retired or Active)." +
            "\nNote: The data may be limited on older players" +
            " and some \ncurrent players data may be missing!");
        noteText.setTextFill(colorRed);
        nbaLogoImg = new ImageView(nbaLogo); //goes into topMidleBar


        logoImg = new ImageView(defaultLogoImg); //goes into middleBar
        playerImg = new ImageView(defaultPlayerImg); //goes into middleBar

        positionLabel = new Label("Position: "); //goes into bottomBar
        positionLabel.setMinSize(Double.MAX_VALUE, 35.0);
        positionLabel.setTranslateX(200.0); //centers
        heightLabel = new Label("Height: " ); //goes into bottomBar
        heightLabel.setTranslateX(200.0); //centers
        heightLabel.setMinSize(Double.MAX_VALUE, 35.0);
        weightLabel = new Label("Weight: " ); //goes into bottomBar
        weightLabel.setTranslateX(200.0); //centers
        weightLabel.setMinSize(Double.MAX_VALUE, 35.0);
        teamLabel = new Label("Team: "); //goes into bottomBar
        teamLabel.setTranslateX(200.0); //centers
        teamLabel.setMinSize(Double.MAX_VALUE, 35.0);


    } // ApiApp


    /** {@inheritDoc} */
    @Override
    public void init() {

        root.getChildren().addAll(topBar, topMiddleBar, middleBar, bottomBar);
        topBar.getChildren().addAll(firstNameText, firstNameTextField,
            lastNameText, lastNameTextField, getPlayerButton);
        topMiddleBar.getChildren().addAll(nbaLogoImg, noteText);
        middleBar.getChildren().addAll(logoImg, playerImg);
        bottomBar.getChildren().addAll(positionLabel, heightLabel, weightLabel, teamLabel);


        EventHandler<ActionEvent> getPlayerHandler = (ActionEvent e) -> {
            this.getStats(e);
        };
        getPlayerButton.setOnAction(getPlayerHandler);

        System.out.println("init() called");

    } //init


    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;
        this.scene = new Scene(this.root, 620, 580);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));

    } // start

    /** {@inheritDoc} */
    @Override
    public void stop() {
        System.out.println("stop() called");
    } //stop

    /**
     * Called after getPlayer button clicked. Should put the stats of the player.
     * @param e tells the event.
     */
    private void getStats(ActionEvent e) {
        Runnable task = () -> {
            System.out.println("getStats() called");
            downloadJsonStats(); //also soons displays the stats
            insertTeamImage();
            insertPlayerImage(); //also changes the noteText
        };
        Platform.runLater(task);
    } //getStats

    /**
     * Private helper method to downlaod images.
     */
    private void downloadJsonStats() {
        try {
            System.out.println("downloadJsonStats() called");
            // form URI
            String firstName = URLEncoder.encode
                (firstNameTextField.getText().trim(), StandardCharsets.UTF_8);
            String lastName =  URLEncoder.encode
                (lastNameTextField.getText().trim(), StandardCharsets.UTF_8);
            uri = "https://www.balldontlie.io/api/v1/players?search="
                + firstName + "%20" + lastName;
            // build request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            System.out.println("Request Built...");
            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            System.out.println("HTTPRequest is sent and recieved...");
            // ensure the request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if

            // get request body (the content we requested)
            String jsonString = response.body();
            System.out.println("Raw JSON String: \n" + jsonString);

            // parse the JSON-formatted string using GSON
            APIResponse APIResponse = GSON.fromJson(jsonString, APIResponse.class);

            // print info about the response
            printAPIResponse(APIResponse);
        } catch (IOException | InterruptedException e) {
            // either:
            // 1. an I/O error occurred when sending or receiving;
            // 2. the operation was interrupted; or
            // 3. the Image class could not load the image.
            alertError(e);
            // e.printStackTrace();
        } // try

    } //downloadJsonStats

    /**
     * Private helper method to downlaod team logo. This reads in data
     * from the first api response and gives to second.
     */
    private void insertTeamImage() {

        System.out.println("insertTeamImage() called");

        if (abbrev.length() == 0) {
            ; //dont do anything if unable to get the team abbreviation
        } else {
            String teamImageURI = "";
            // form URI
            if (abbrev.equals("NOP")) { //dealing with an irregular
                teamImageURI = "https://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/" +
                    abbrev.substring(0,2) + ".png&h=200&w=200";
            } else if (abbrev.equals("UTA")) { //dealing with another irregular
                teamImageURI = "https://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/" +
                    "utah" + ".png&h=200&w=200";
            } else {
                teamImageURI = "https://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/" +
                    abbrev + ".png&h=200&w=200";
            }

            Image teamImage = new Image(teamImageURI);
            logoImg.setImage(teamImage);
        }

    } //insertTeamImage

     /**
     * Private helper method to downlaod team logo. This reads in data
     * from the first api response and gives to second. Uses data from
     * first API response.
     */
    private void insertPlayerImage() {

        System.out.println("insertPlayerImage() called");

        try {
            // form URI
            String urlLastName;
            if (lastName.length() <= 5) { //accounting if last name not atleast 5 letters for url
                urlLastName = lastName;
            } else {
                urlLastName = lastName.substring(0,5);
            }
            String urlFirstName = firstName.substring(0,2);
            String urlName = urlLastName + urlFirstName;
            urlName = urlName.toLowerCase();
            System.out.println("Name part for player image: " + urlName);

            String playerImageURI =
                "https://www.basketball-reference.com/req/202106291/images/headshots/" +
                urlName + "01.jpg";

            Image playerImage = new Image(playerImageURI, 300.0, 300.0, true, false);
            playerImg.setImage(playerImage);

            noteText.setText("Now showing: " + firstName + " " + lastName +
                "\nEnter another player.");
            noteText.setUnderline(true);
            noteText.setTextFill(colorBlack);
        } catch (StringIndexOutOfBoundsException e) {
            alertError(e);
        }

    } //insertPlayerImage


    /**
     * Give the response from API.
     * @param APIResponse is the response object.
     */
    private void printAPIResponse(APIResponse APIResponse) {
        try {
            DataItem result = APIResponse.data[0];
            System.out.println("printItunesResponse() started");
            System.out.println();
            System.out.println("********** PRETTY JSON STRING: **********");
            System.out.println(GSON.toJson(APIResponse));
            System.out.println();
            System.out.println("********** PARSED RESULTS: **********");

            System.out.printf("APIResponse.data[0]:\n");
            System.out.printf(" - id = %s\n", result.id);
            System.out.printf(" - firstName = %s\n", result.firstName);
            firstName = result.firstName;
            System.out.printf(" - lastName = %s\n", result.lastName);
            lastName = result.lastName;
            System.out.printf(" - position = %s\n", result.position);
            System.out.printf(" - heightFeet = %s\n", result.heightFeet);
            System.out.printf(" - heightInches = %s\n", result.heightInches);
            System.out.printf(" - weightPounds = %s\n", result.weightPounds);
            System.out.printf(" - abbreviation = %s\n", result.team.abbreviation);
            abbrev = result.team.abbreviation;
            System.out.printf(" - team = %s\n", result.team.fullName);


            System.out.println("The players team abbrev is: " + result.team.abbreviation);
            System.out.println("The players position is: " + result.position);

            insertStats(result);
        } catch (ArrayIndexOutOfBoundsException e) {
            alertError(e);
        } //try-catch

    } // printItunesResponse

    /**
     * Method to insert/display the players stats.
     * @param result will have the data.
     */
    public void insertStats(DataItem result) {
        System.out.println("insertStats() called");

        String fullPosition = "";
        if (result.position.equals("F")) {
            fullPosition = "Forward";
        }
        if (result.position.equals("C")) {
            fullPosition = "Center";
        }
        if (result.position.equals("G")) {
            fullPosition = "Guard";
        }
        if (result.position.equals("F-G") || result.position.equals("G-F")) {
            fullPosition = "Forward/Guard";
        }
        if (result.position.equals("F-C") || result.position.equals("C-F")) {
            fullPosition = "Forward/Center";
        }
        if (result.position.equals("G-C") || result.position.equals("C-G")) {
            fullPosition = "Guard/Center";
        }
        if (result.position.equals("")) {
            fullPosition = "This player's position is unavailable :(";
        }
        if (result.heightFeet == 0 && result.heightInches == 0) { //if height data not available
            heightLabel.setText("Height: This player's height is unavailable :(");
        } else {
            heightLabel.setText("Height: " + result.heightFeet + "ft " +
                result.heightInches + "in");
        }
        if (result.weightPounds == 0) { //if weight data not available
            weightLabel.setText("Weight: This player's weight is unavailable :(");
        } else {
            weightLabel.setText("Weight: " + result.weightPounds + " pounds");
        }

        positionLabel.setText("Position: " + fullPosition);
        positionLabel.setMinSize(Double.MAX_VALUE, 35.0);
        positionLabel.setTranslateX(200.0); //centers
        heightLabel.setTranslateX(200.0); //centers
        heightLabel.setMinSize(Double.MAX_VALUE, 35.0);
        weightLabel.setTranslateX(200.0); //centers
        weightLabel.setMinSize(Double.MAX_VALUE, 35.0);
        teamLabel.setText("Team: " + result.team.fullName);
        teamLabel.setTranslateX(200.0); //centers
        teamLabel.setMinSize(Double.MAX_VALUE, 35.0);

    } //insertStats


    /**
     * Makes a alert prompt.
     * @param cause is the parameter.
     */
    public void alertError(Throwable cause) {
        TextArea text = new TextArea("URI: " + uri + "\n\nException: " + cause.toString()
            + "\n\nThat is not a valid player. \nMake sure spelling is correct!");
        text.setEditable(false);
        Alert alert = new Alert(AlertType.ERROR);
        alert.getDialogPane().setContent(text);
        alert.setResizable(true);
        alert.showAndWait();
    } //alertError



} // ApiApp
