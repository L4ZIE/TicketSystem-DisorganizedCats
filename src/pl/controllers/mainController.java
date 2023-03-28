package pl.controllers;

import be.Event;
import be.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pl.models.EventModel;
import pl.models.TicketModel;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;

    private TicketModel ticketModel;

    public mainController() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        displayUserControls(anpController);
        anpContent.getStyleClass().add("container");
        anpMain.setStyle("-fx-background-color: #474747");
    }

    private void displayUserControls(AnchorPane container) {
        //TODO
        container.getStyleClass().add("container");

        Button manageUsers = new Button();
        Button manageEvents = new Button();
        Button manageSpecTickets = new Button();

        Label userControls = new Label();

        VBox buttonContainer = new VBox(5);

        manageUsers.getStyleClass().add("app-buttons");
        manageEvents.getStyleClass().add("app-buttons");
        manageSpecTickets.getStyleClass().add("app-buttons");

        manageUsers.setText("Manage Users");
        manageEvents.setText("Manage Events");
        manageSpecTickets.setText("Manage Special Tickets");
        userControls.setText("User Controls");

        manageUsers.setMinWidth(container.getMinWidth() - 20);
        manageUsers.setMinHeight((container.getMinHeight() / 3) - 30);

        manageEvents.setMinWidth(manageUsers.getMinWidth());
        manageEvents.setMinHeight(manageUsers.getMinHeight());

        manageSpecTickets.setMinWidth(manageEvents.getMinWidth());
        manageSpecTickets.setMinHeight(manageEvents.getMinHeight());

        buttonContainer.getChildren().addAll(manageUsers, manageEvents, manageSpecTickets);
        buttonContainer.setLayoutX(container.getLayoutX() + 3);
        buttonContainer.setLayoutY(container.getLayoutY() + 50);
        buttonContainer.setMinHeight(container.getMinHeight() - 20);

        userControls.setLayoutX((container.getMinWidth() / 2) - 50);
        userControls.setLayoutY(container.getLayoutY() + 10);

        container.getChildren().addAll(userControls, buttonContainer);

        manageUsers.setDisable(true);
        manageUsers.setOnMouseClicked(e -> {
            //this is for sprint 2
        });

        manageEvents.setOnMouseClicked(e -> {
            ManageEventsScreen(anpContent);
        });

        manageSpecTickets.setOnMouseClicked(e -> {
            ManageTicketsScreen(anpContent);
        });
    }

    private void displayEventsTableView(AnchorPane container) {
        //TODO
        TableView eventsTable = new TableView();

        TableColumn<Event, String> nameColumn = new TableColumn<>();
        nameColumn.setResizable(false);
        nameColumn.setText("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> startColumn = new TableColumn<>();
        startColumn.setResizable(false);
        startColumn.setText("Start");
        startColumn.setMaxWidth(75);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));

        TableColumn<Event, String> endColumn = new TableColumn<>();
        endColumn.setResizable(false);
        endColumn.setText("End");
        endColumn.setMaxWidth(75);
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));

        TableColumn<Event, String> locationColumn = new TableColumn<>();
        locationColumn.setResizable(false);
        locationColumn.setText("Location");
        locationColumn.setMinWidth(110);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        fillEventsTable(eventsTable);
        eventsTable.setLayoutX(container.getLayoutX() - 300);
        eventsTable.setLayoutY(container.getLayoutY() + 30);
        eventsTable.setMaxHeight(container.getMinHeight() - 100);
        eventsTable.setMaxWidth(360);

        eventsTable.getColumns().addAll(nameColumn, startColumn, endColumn, locationColumn);
        container.getChildren().add(eventsTable);

    }

    private void fillEventsTable(TableView eventsTable) {
        eventsTable.setItems(eventModel.getAllEvents());
    }

    private void ManageEventsScreen(AnchorPane container) {
        clearContainer(container);

        Label title = new Label();
        TextField searchBox = new TextField();
        Button searchButton = new Button();
        Button newEvent = new Button();

        searchBox.setPromptText("Search...");
        newEvent.setText("New Event");
        searchButton.setText("\uD83D\uDD0D");
        searchButton.setStyle("-fx-font-size: 12");

        searchButton.getStyleClass().add("app-buttons");
        newEvent.getStyleClass().addAll("app-buttons", "negative-buttons");

        title.setText("Manage Events");

        title.setLayoutX(container.getLayoutX() - 300);
        title.setLayoutY(container.getLayoutY());

        searchBox.setLayoutX(title.getLayoutX() + 125);
        searchBox.setLayoutY(title.getLayoutY());
        searchBox.setMinWidth(container.getMinWidth() / 2);

        searchButton.setLayoutX(searchBox.getLayoutX() + searchBox.getMinWidth() + 5);
        searchButton.setLayoutY(searchBox.getLayoutY());

        newEvent.setLayoutX(title.getLayoutX());
        newEvent.setLayoutY(container.getMinHeight() - newEvent.getMinHeight() - 50);


        container.getChildren().addAll(title, searchBox, searchButton, newEvent);
        //TODO Display: latest events and line, search bar and search button
        displayEventsTableView(container);
        //TODO Display: New Event button

        newEvent.setOnMouseClicked(e -> {
            //Add new event window
        });
    }

    private void clearContainer(AnchorPane container) {
        container.getChildren().clear();
    }


    private void ManageSelectedEventScreen(AnchorPane container) {//when merged with backend, add an Event event to the constructor
        //TODO Display: latest events and line
        displayEventsTableView(container);
        //TODO Display: New Event button

    }

    private void CreateEventScreen(AnchorPane container) {
        //TODO Display: all elements according to figma
    }



    private void displayTicketsTableView(AnchorPane container){
        TableView ticketsTable = new TableView();

        TableColumn<Event, String> nameColumn = new TableColumn<>();
        nameColumn.setResizable(false);
        nameColumn.setText("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Event, String> emailColumn = new TableColumn<>();
        emailColumn.setResizable(false);
        emailColumn.setText("Email");
        emailColumn.setMaxWidth(75);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        TableColumn<Event, String> priceColumn = new TableColumn<>();
        priceColumn.setResizable(false);
        priceColumn.setText("Price");
        priceColumn.setMaxWidth(75);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        TableColumn<Event, String> typeColumn = new TableColumn<>();
        typeColumn.setResizable(false);
        typeColumn.setText("Type");
        typeColumn.setMinWidth(110);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));

        fillTicketsTable(ticketsTable);
        ticketsTable.setLayoutX(container.getLayoutX() - 310);
        ticketsTable.setLayoutY(container.getLayoutY() + 30);
        ticketsTable.setMaxHeight(container.getMinHeight() - 100);
        ticketsTable.setMaxWidth(360);

        ticketsTable.getColumns().addAll(nameColumn, emailColumn, priceColumn, typeColumn);
        container.getChildren().add(ticketsTable);
    }

    private void fillTicketsTable(TableView ticketsTable) {
        ticketsTable.setItems(ticketModel.getAllTickets());
    }

    private void ManageTicketsScreen(AnchorPane container) {
        //TODO display event name,
        // generate tableview with 2 buttons (use and delete)
        // display search bar and go back button

        clearContainer(container);

        Label title = new Label();
        TextField searchBox = new TextField();
        Button searchButton = new Button();
        Button goBack = new Button();

        searchBox.setPromptText("Search...");
        searchButton.setText("\uD83D\uDD0D");
        searchButton.setStyle("-fx-font-size: 12");
        goBack.setText("<-");

        searchButton.getStyleClass().add("app-buttons");
        goBack.getStyleClass().addAll("app-buttons", "negative-buttons");

        title.setText("Event Tickets");

        title.setLayoutX(container.getLayoutX()-220);
        title.setLayoutY(container.getLayoutY());

        searchBox.setLayoutX(title.getLayoutX()+115);
        searchBox.setLayoutY(title.getLayoutY());
        searchBox.setMinWidth(container.getMinWidth()/6);

        searchButton.setLayoutX(searchBox.getLayoutX()+searchBox.getMinWidth()+70);
        searchButton.setLayoutY(searchBox.getLayoutY());

        goBack.setLayoutX(title.getLayoutX()-90);
        goBack.setLayoutY(goBack.getLayoutY()+5);

        container.getChildren().addAll(title, searchBox, searchButton, goBack);
        displayTicketsTableView(container);

        goBack.setOnMouseClicked(e -> {
            //Add new event window
        } );
    }

    private void ManageSpecialTicketsScreen(AnchorPane container) {

    }

    private void displayManageAccountScreen(AnchorPane container) {
        //sprint 2
    }


}
