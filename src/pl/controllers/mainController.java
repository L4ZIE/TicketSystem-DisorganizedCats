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

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;

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
            ManageSpecialTicketsScreen(anpContent);
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

        newEvent.setOnMouseClicked(e->{
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

    private void ManageTicketsScreen(AnchorPane container) {
        //TODO display event name,
        // generate tableview with 2 buttons (use and delete)
        // display search bar and go back button

        TableView ticketList = new TableView<>();

        TableColumn<Ticket, String> nameColumn = new TableColumn<>("Name");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Ticket, String> emailColumn = new TableColumn<>("Email");

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        TableColumn<Ticket, String> priceColumn = new TableColumn<>("Price");

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        TableColumn<Ticket, String> typeColumn = new TableColumn<>("Type");

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));

        TableColumn<Ticket, String> usedColumn = new TableColumn<>("Used");

        usedColumn.setCellValueFactory(new PropertyValueFactory<>("used"));

        //TableColumn<Ticket, String> useNdelColumn = new TableColumn<>("useNdel");

        //useNdelColumn.setCellValueFactory();

        ticketList.getColumns().add(nameColumn);
        ticketList.getColumns().add(emailColumn);
        ticketList.getColumns().add(priceColumn);
        ticketList.getColumns().add(typeColumn);
        ticketList.getColumns().add(usedColumn);

        //TableView.setPlaceholder(new Label("No Tickets Available"));

        Button goBack = new Button();
        Button useBtn = new Button();
        Button delBtn = new Button();
        Label eventLabel = new Label();
        TextField searchBar = new TextField();


        goBack.setText("<-");
        useBtn.setText("Use");
        delBtn.setText("Delete");
        searchBar.setText("Search...");

        goBack.getStyleClass().add("return");
        searchBar.getStyleClass().add("ticket-search");

        //ticketList.setMinWidth(container.getMinWidth()-100);


        goBack.setLayoutX(container.getLayoutX() - 290);
        goBack.setLayoutY(container.getLayoutY() + 5);

        searchBar.setLayoutX(container.getLayoutX() - 80);
        searchBar.setLayoutY(container.getLayoutY() + 5);

        ticketList.setLayoutX(container.getLayoutX() - 290);
        ticketList.setLayoutY(container.getLayoutY() + 40);


        container.getChildren().addAll(goBack, searchBar, ticketList);

        goBack.setOnMouseClicked(e -> {
            ManageSelectedEventScreen(anpContent);
        });

    }

    private void ManageSpecialTicketsScreen(AnchorPane container) {

    }

    private void displayManageAccountScreen(AnchorPane container) {
        //sprint 2
    }


}
