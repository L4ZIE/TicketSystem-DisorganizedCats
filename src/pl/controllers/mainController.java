package pl.controllers;

import be.Event;
import be.Ticket;
import dal.EventDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import pl.models.EventModel;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;
    private int id;
    private boolean listsUpdated = false;
    private Event selectedEvent;
    private TableView<Event> eventsTable;
    private TextField txfEventName,txfStartDate,txfEndDate,txfLocation,txfLocationGuidance,txfNotes;

    public mainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        displayUserControls(anpController);
        anpContent.getStyleClass().add("container");
        anpMain.setStyle("-fx-background-color: #474747");
    }
    public TableView<Event> getEventsTable() {
        return this.eventsTable;
    }

    private void fillEventsTable(TableView eventsTable) {
        eventsTable.setItems(eventModel.getAllEvents());
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
        eventsTable = new TableView<>();

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



    private void ManageEventsScreen(AnchorPane container) {
        clearContainer(container);

        Label title = new Label();
        TextField searchBox = new TextField();
        Button searchButton = new Button();
        Button newEvent = new Button();

        Button deleteEvent = new Button();
        Button editEvent = new Button();

        editEvent.setText("Edit");
        editEvent.setLayoutX(container.getLayoutX() -5);
        editEvent.setLayoutY(container.getLayoutY() + 341);
        editEvent.getStyleClass().addAll("app-buttons", "negative-buttons");

        deleteEvent.setText("Delete");
        deleteEvent.setLayoutX(container.getLayoutX() - 140);
        deleteEvent.setLayoutY(container.getLayoutY() + 341);
        deleteEvent.getStyleClass().addAll("app-buttons", "negative-buttons");

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


        container.getChildren().addAll(title, searchBox, searchButton, newEvent,deleteEvent,editEvent);
        //TODO Display: latest events and line, search bar and search button
        displayEventsTableView(container);

        newEvent.setOnMouseClicked(e -> {
            displayCreateEvent(container);
        });

        deleteEvent.setOnMouseClicked(event -> {
            Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
            eventModel.deleteEvent(selectedEvent);
        });

        searchBox.setOnAction(event -> {
            //TODO

        });

        editEvent.setOnMouseClicked(event -> {
           displayCreateEvent(container);
           //TODO
        });
    }

    public void displayCreateEvent(AnchorPane container) {
        clearContainer(container);
        TextField txfEventName = new TextField();

        TextField txfStartDate = new TextField();
        TextField txfStartTime = new TextField();
        TextField txfStartMin = new TextField();

        TextField txfEndDate = new TextField();
        TextField txfEndTime = new TextField();
        TextField txfEndMin = new TextField();

        TextField txfLocation = new TextField();
        TextField txfLocationGuidance = new TextField();
        TextField txfNotes = new TextField();

        Label labelStart = new Label();
        Label labelEnd = new Label();
        Label labelPlace = new Label();
        Label labelLocGuidance = new Label();
        Label labelNote = new Label();
        Label labelStartTime = new Label();

        Button btnSave = new Button();
        Button btnManageTicket = new Button();
        Button btnCancel = new Button();


        txfEventName.setPrefWidth(300);
        txfEventName.setPrefHeight(40);
        txfEventName.setAlignment(Pos.CENTER);
        txfEventName.setLayoutX(container.getLayoutX() - 270);
        txfEventName.setLayoutY(container.getLayoutY() );
        txfEventName.setPromptText("Event Name");

        labelStart.setLayoutX(container.getLayoutX() - 300);
        labelStart.setLayoutY(container.getLayoutY() +55);
        labelStart.setText("Start Date");

        txfStartDate.setPrefWidth(130);
        txfStartDate.setLayoutX(container.getLayoutX()- 300);
        txfStartDate.setLayoutY(container.getLayoutY() + 80);

        labelEnd.setLayoutX(container.getLayoutX() - 300);
        labelEnd.setLayoutY(container.getLayoutY() + 115);
        labelEnd.setText("End Date");

        txfEndDate.setPrefWidth(130);
        txfEndDate.setLayoutX(container.getLayoutX() -300);
        txfEndDate.setLayoutY(container.getLayoutY() + 140);

        labelPlace.setLayoutX(container.getLayoutX() -300);
        labelPlace.setLayoutY(container.getLayoutY() + 180);
        labelPlace.setText("Location");

        txfLocation.setPrefWidth(370);
        txfLocation.setPrefHeight(30);
        txfLocation.setLayoutX(container.getLayoutX() - 300);
        txfLocation.setLayoutY(container.getLayoutY() +205);

        labelLocGuidance.setLayoutX(container.getLayoutX() - 300);
        labelLocGuidance.setLayoutY(container.getLayoutY() + 245);
        labelLocGuidance.setText(" Location Guidance");

        txfLocationGuidance.setPrefWidth(370);
        txfLocationGuidance.setPrefHeight(50);
        txfLocationGuidance.setLayoutX(container.getLayoutX() - 300);
        txfLocationGuidance.setLayoutY(container.getLayoutY() + 270);

        labelNote.setLayoutX(container.getLayoutX() - 75);
        labelNote.setLayoutY(container.getLayoutY() + 55);
        labelNote.setText("Notes");

        txfNotes.setPrefWidth(145);
        txfNotes.setPrefHeight(85);
        txfNotes.setLayoutX(container.getLayoutX() - 75);
        txfNotes.setLayoutY(container.getLayoutY() + 80);

        labelStartTime.setLayoutX(container.getLayoutX() - 140);
        labelStartTime.setLayoutY(container.getLayoutY() + 55);
        labelStartTime.setText("Time");

        txfStartTime.setPrefWidth(30);
        txfStartTime.setLayoutX(container.getLayoutX() - 150);
        txfStartTime.setLayoutY(container.getLayoutY() + 80);

        txfStartMin.setPrefWidth(30);
        txfStartMin.setLayoutX(container.getLayoutX() - 115);
        txfStartMin.setLayoutY(container.getLayoutY() + 80);

        txfEndTime.setPrefWidth(30);
        txfEndTime.setLayoutX(container.getLayoutX() - 150);
        txfEndTime.setLayoutY(container.getLayoutY() + 140);

        txfEndMin.setPrefWidth(30);
        txfEndMin.setLayoutX(container.getLayoutX() - 115);
        txfEndMin.setLayoutY(container.getLayoutY() + 140);

        btnSave.setPrefWidth(60);
        btnSave.setText("Save");
        btnSave.setLayoutX(container.getLayoutX() -300);
        btnSave.setLayoutY(container.getLayoutY() + 350);

        btnManageTicket.setPrefWidth(120);
        btnManageTicket.setAlignment(Pos.CENTER);
        btnManageTicket.setText("Manage Ticket");
        btnManageTicket.setLayoutX(container.getLayoutX() - 175);
        btnManageTicket.setLayoutY(container.getLayoutY() + 350);

        btnCancel.setPrefWidth(60);
        btnCancel.setText("Cancel");
        btnCancel.setLayoutX(container.getLayoutX());
        btnCancel.setLayoutY(container.getLayoutY() + 350);

        container.getChildren().addAll(txfEventName,txfStartDate,txfEndDate,labelEnd,labelStart,labelPlace,txfLocation,
                                labelLocGuidance,txfLocationGuidance,labelNote,txfNotes,labelStartTime,txfStartTime,
                                txfStartMin,txfEndTime,txfEndMin,btnSave,btnManageTicket,btnCancel);

        btnSave.setOnMouseClicked(e -> {

            eventModel.createEvent(new Event(
                    id,
                    txfStartDate.getText(),
                    txfEndDate.getText(),
                    txfLocation.getText(),
                    txfNotes.getText(),
                    txfLocationGuidance.getText(),
                    txfEventName.getText()
            ));
            updateTableEvents(container);
            container.getChildren().clear();
            displayEventsTableView(container);
        });

        btnManageTicket.setOnMouseClicked(event -> {
            ManageTicketsScreen(container);
        });
    }
    public void updateTableEvents(AnchorPane container) {
        if (listsUpdated){
            listsUpdated = false;
        }
    }

    private void clearContainer(AnchorPane container) {
        container.getChildren().clear();
    }

    private void ManageSelectedEventScreen(AnchorPane container) {//when merged with backend, add an Event event to the constructor
        //TODO Display: latest events and line
        displayEventsTableView(container);
        //TODO Display: New Event button

    }
    private void ManageTicketsScreen(AnchorPane container) {
        //TODO display event name,
        // generate tableview with 2 buttons (use and delete)
        // display search bar and go back button
        container.getChildren().clear();

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

        ticketList.getColumns().addAll(nameColumn, emailColumn, priceColumn, typeColumn, usedColumn);

        Button goBack = new Button();
        Button useBtn = new Button();
        Button delBtn = new Button();
        Label eventLabel = new Label();
        TextField searchBar = new TextField();


        goBack.setText("<-");
        useBtn.setText("Use");
        delBtn.setText("Delete");
        searchBar.setText("Search...");

        goBack.getStyleClass().addAll("app-buttons", "negative-buttons");


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
