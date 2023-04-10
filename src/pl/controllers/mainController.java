package pl.controllers;

import be.Event;
import be.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pl.models.EventModel;
import pl.models.TicketModel;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;

    private TicketModel ticketModel;

    private int id;
    private boolean listsUpdated = false;
    private TableView<Event> eventsTable;

    private TableView<Ticket> ticketsTable;

    public mainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        ticketModel = new TicketModel();
        loginScreen(anpMain);
        anpMain.setStyle("-fx-background-color: #474747");
    }
    private void loginScreen(AnchorPane container){
        Label usernameLbl = new Label();
        TextField usernameField = new TextField();
        Label passwordLbl = new Label();
        TextField passwordField = new TextField();
        Button loginBtn = new Button();
        Button recoverBtn = new Button();

        usernameLbl.setText("Username");
        usernameLbl.setLayoutX(container.getLayoutX()+300);
        usernameLbl.setLayoutY(container.getLayoutY()+70);
        usernameLbl.setStyle("-fx-font-size: 20");

        usernameField.setLayoutX(usernameLbl.getLayoutX()-50);
        usernameField.setLayoutY(usernameLbl.getLayoutY()+40);
        usernameField.setPrefWidth(200);

        passwordLbl.setText("Password");
        passwordLbl.setLayoutX(usernameLbl.getLayoutX());
        passwordLbl.setLayoutY(usernameField.getLayoutY()+50);
        passwordLbl.setStyle("-fx-font-size: 20");

        passwordField.setLayoutX(usernameField.getLayoutX());
        passwordField.setLayoutY(passwordLbl.getLayoutY()+40);
        passwordField.setPrefWidth(200);

        loginBtn.setText("Login");
        loginBtn.getStyleClass().addAll("app-buttons","positive-buttons");
        loginBtn.setLayoutX(passwordLbl.getLayoutX()-50);
        loginBtn.setLayoutY(passwordField.getLayoutY()+70);
        loginBtn.setPrefWidth(115);

        recoverBtn.setText("Recover");
        recoverBtn.getStyleClass().addAll("app-buttons","negative-buttons");
        recoverBtn.setLayoutX(loginBtn.getLayoutX()+120);
        recoverBtn.setLayoutY(loginBtn.getLayoutY());
        recoverBtn.setPrefWidth(75);

        container.getChildren().addAll(usernameLbl,usernameField,passwordLbl,passwordField,loginBtn,recoverBtn);

        //TODO
        loginBtn.setOnMouseClicked(event -> {
            displayUserControls(anpController);
            anpContent.getStyleClass().add("container");
        });

        recoverBtn.setOnMouseClicked(event -> {

        });


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

        manageUsers.getStyleClass().addAll("app-buttons", "big-buttons");
        manageEvents.getStyleClass().addAll("app-buttons", "big-buttons");
        manageSpecTickets.getStyleClass().addAll("app-buttons", "big-buttons");

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
        nameColumn.setMinWidth(100); //set to dynamic later
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> startColumn = new TableColumn<>();
        startColumn.setResizable(false);
        startColumn.setText("Start");
        startColumn.setMaxWidth(75); //set to dynamic later
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));

        TableColumn<Event, String> endColumn = new TableColumn<>();
        endColumn.setResizable(false);
        endColumn.setText("End");
        endColumn.setMaxWidth(75); //set to dynamic later
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));

        TableColumn<Event, String> locationColumn = new TableColumn<>();
        locationColumn.setResizable(false);
        locationColumn.setText("Location");
        locationColumn.setMinWidth(110); //set to dynamic later
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        fillEventsTable(eventsTable);
        eventsTable.setLayoutX(container.getLayoutX() - 300);
        eventsTable.setLayoutY(container.getLayoutY() + 30);
        eventsTable.setMaxHeight(container.getMinHeight() - 100);
        eventsTable.setMaxWidth(container.getMinWidth() - 51);

        eventsTable.getColumns().addAll(nameColumn, startColumn, endColumn, locationColumn);
        container.getChildren().add(eventsTable);
    }


    private void ManageEventsScreen(AnchorPane container) {
        clearContainer(container);

        Label lblTitle = new Label();
        TextField txfSearchBox = new TextField();
        Button btnSearchButton = new Button();
        Button btnNewEvent = new Button();

        Button btnDeleteEvent = new Button();
        Button btnEditEvent = new Button();
        Button btnManageTickets = new Button();

        lblTitle.setText("Manage Events");
        lblTitle.setLayoutX(container.getLayoutX() - 300);
        lblTitle.setLayoutY(container.getLayoutY());

        btnNewEvent.setText("New Event");
        btnNewEvent.setLayoutX(lblTitle.getLayoutX());
        btnNewEvent.setLayoutY(container.getMinHeight() - btnNewEvent.getMinHeight() - 50);
        btnNewEvent.getStyleClass().addAll("app-buttons");

        btnManageTickets.setText("Manage Tickets");
        btnManageTickets.setLayoutX(btnNewEvent.getLayoutX() + 100);
        btnManageTickets.setLayoutY(btnNewEvent.getLayoutY());
        btnManageTickets.getStyleClass().add("app-buttons");

        btnDeleteEvent.setText("Delete");
        btnDeleteEvent.setLayoutX(btnManageTickets.getLayoutX() + 140);
        btnDeleteEvent.setLayoutY(btnManageTickets.getLayoutY());
        btnDeleteEvent.getStyleClass().addAll("app-buttons", "negative-buttons");

        btnEditEvent.setText("Edit");
        btnEditEvent.setLayoutX(btnDeleteEvent.getLayoutX() + 80);
        btnEditEvent.setLayoutY(btnDeleteEvent.getLayoutY());
        btnEditEvent.getStyleClass().addAll("app-buttons");

        txfSearchBox.setPromptText("Search...");

        btnSearchButton.setText("\uD83D\uDD0D");
        btnSearchButton.setStyle("-fx-font-size: 12");

        btnSearchButton.getStyleClass().add("app-buttons");

        txfSearchBox.setLayoutX(lblTitle.getLayoutX() + 125);
        txfSearchBox.setLayoutY(lblTitle.getLayoutY());
        txfSearchBox.setMinWidth(container.getMinWidth() / 2);

        btnSearchButton.setLayoutX(txfSearchBox.getLayoutX() + txfSearchBox.getMinWidth() + 5);
        btnSearchButton.setLayoutY(txfSearchBox.getLayoutY());


        container.getChildren().addAll(lblTitle, txfSearchBox, btnSearchButton, btnNewEvent, btnDeleteEvent, btnEditEvent, btnManageTickets);
        //TODO Display: latest events and line, search bar and search button
        displayEventsTableView(container);

        btnNewEvent.setOnMouseClicked(e -> {
            displayCreateEvent(container);
        });

        btnDeleteEvent.setOnMouseClicked(event -> {
            if (eventsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an event.");
            } else {
                Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
                eventModel.deleteEvent(selectedEvent);
                displayEventsTableView(container);
            }
        });

        txfSearchBox.setOnAction(event -> {
            //TODO

        });

        btnEditEvent.setOnMouseClicked(event -> {
            if (eventsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an event.");
            } else {
                Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
                displayCreateEvent(container, selectedEvent);
            }

        });

        btnManageTickets.setOnMouseClicked(e -> {
            if (eventsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an event.");
            } else {
                ManageTicketsScreen(container, eventsTable.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void displayCreateEvent(AnchorPane container, Event selectedEvent) {
        clearContainer(container);
        TextField txfEventName = new TextField();

        TextField txfStartDate = new TextField();
        TextField txfStartHour = new TextField();
        TextField txfStartMin = new TextField();

        TextField txfEndDate = new TextField();
        TextField txfEndHour = new TextField();
        TextField txfEndMin = new TextField();

        TextField txfLocation = new TextField();
        TextField txfLocationGuidance = new TextField();
        TextField txfNotes = new TextField();

        Label lblStartDate = new Label();
        Label lblStartTime = new Label();
        Label lblEndDate = new Label();
        Label lblEndTime = new Label();
        Label lblPlace = new Label();
        Label lblLocGuidance = new Label();
        Label lblNote = new Label();
        Label lblColumnStartTime = new Label();
        Label lblColumnEndTime = new Label();

        Button btnSave = new Button();
        Button btnCancel = new Button();

        if (selectedEvent == null) {
            txfEventName.setPromptText("Event Name (Click to edit)");
        } else {
            txfEventName.setText(selectedEvent.getEventName());
            txfNotes.setText(selectedEvent.getNotes());
            txfLocation.setText(selectedEvent.getLocation());
            txfLocationGuidance.setText(selectedEvent.getLocationGuidance());

            txfStartDate.setText(selectedEvent.getStartDateTime());
            txfEndDate.setText(selectedEvent.getEndDateTime());
        }

        btnSave.getStyleClass().addAll("app-buttons");
        btnCancel.getStyleClass().addAll("app-buttons");

        txfEventName.setPrefWidth(300);
        txfEventName.setPrefHeight(40);
        txfEventName.setAlignment(Pos.CENTER);
        txfEventName.setLayoutX(container.getLayoutX() - 270);
        txfEventName.setLayoutY(container.getLayoutY());
        txfEventName.getStyleClass().add("transparent-textField");

        lblStartDate.setLayoutX(container.getLayoutX() - 300);
        lblStartDate.setLayoutY(container.getLayoutY() + 55);
        lblStartDate.setText("Start Date*");

        lblStartTime.setLayoutX(lblStartDate.getLayoutX() + 150);
        lblStartTime.setLayoutY(lblStartDate.getLayoutY());
        lblStartTime.setText("Start Time*");

        lblNote.setLayoutX(lblStartTime.getLayoutX() + 85);
        lblNote.setLayoutY(lblStartTime.getLayoutY());
        lblNote.setText("Notes*");

        txfStartDate.setPrefWidth(130);
        txfStartDate.setLayoutX(container.getLayoutX() - 300);
        txfStartDate.setLayoutY(container.getLayoutY() + 80);

        lblEndDate.setLayoutX(container.getLayoutX() - 300);
        lblEndDate.setLayoutY(container.getLayoutY() + 115);
        lblEndDate.setText("End Date");
        lblEndDate.getStyleClass().add("non-important");

        lblEndTime.setLayoutX(lblEndDate.getLayoutX() + 150);
        lblEndTime.setLayoutY(lblEndDate.getLayoutY());
        lblEndTime.setText("End Time");
        lblEndTime.getStyleClass().add("non-important");

        txfEndDate.setPrefWidth(130);
        txfEndDate.setLayoutX(container.getLayoutX() - 300);
        txfEndDate.setLayoutY(container.getLayoutY() + 140);

        lblPlace.setLayoutX(container.getLayoutX() - 300);
        lblPlace.setLayoutY(container.getLayoutY() + 180);
        lblPlace.setText("Location*");

        txfLocation.setPrefWidth(370);
        txfLocation.setPrefHeight(30);
        txfLocation.setLayoutX(container.getLayoutX() - 300);
        txfLocation.setLayoutY(container.getLayoutY() + 205);

        lblLocGuidance.setLayoutX(container.getLayoutX() - 300);
        lblLocGuidance.setLayoutY(container.getLayoutY() + 245);
        lblLocGuidance.setText("Location Guidance");
        lblLocGuidance.getStyleClass().add("non-important");

        txfLocationGuidance.setPrefWidth(370);
        txfLocationGuidance.setPrefHeight(50);
        txfLocationGuidance.setLayoutX(container.getLayoutX() - 300);
        txfLocationGuidance.setLayoutY(container.getLayoutY() + 270);

        txfNotes.setPrefWidth(145);
        txfNotes.setPrefHeight(85);
        txfNotes.setLayoutX(container.getLayoutX() - 75);
        txfNotes.setLayoutY(container.getLayoutY() + 80);

        txfStartHour.setPrefWidth(30);
        txfStartHour.setLayoutX(container.getLayoutX() - 155);
        txfStartHour.setLayoutY(container.getLayoutY() + 80);

        lblColumnStartTime.setText(":");
        lblColumnStartTime.setLayoutX(txfStartHour.getLayoutX() + 33);
        lblColumnStartTime.setLayoutY(txfStartHour.getLayoutY() + 1);

        txfStartMin.setPrefWidth(30);
        txfStartMin.setLayoutX(container.getLayoutX() - 115);
        txfStartMin.setLayoutY(container.getLayoutY() + 80);

        txfEndHour.setPrefWidth(30);
        txfEndHour.setLayoutX(container.getLayoutX() - 155);
        txfEndHour.setLayoutY(container.getLayoutY() + 140);

        lblColumnEndTime.setText(":");
        lblColumnEndTime.setLayoutX(txfEndHour.getLayoutX() + 33);
        lblColumnEndTime.setLayoutY(txfEndHour.getLayoutY() + 1);

        txfEndMin.setPrefWidth(30);
        txfEndMin.setLayoutX(container.getLayoutX() - 115);
        txfEndMin.setLayoutY(container.getLayoutY() + 140);

        btnSave.setPrefWidth(60);
        btnSave.setText("Save");
        btnSave.setLayoutX(container.getLayoutX() - 300);
        btnSave.setLayoutY(container.getLayoutY() + 350);

        btnCancel.setPrefWidth(70);
        btnCancel.setText("Go Back");
        btnCancel.setLayoutX(container.getLayoutX());
        btnCancel.setLayoutY(container.getLayoutY() + 350);

        container.getChildren().addAll(txfEventName, txfStartDate, txfEndDate, lblEndDate, lblStartDate, lblPlace, txfLocation,
                lblLocGuidance, txfLocationGuidance, lblNote, txfNotes, lblStartTime, txfStartHour, lblEndTime, lblColumnStartTime, lblColumnEndTime,
                txfStartMin, txfEndHour, txfEndMin, btnSave, btnCancel);

        btnSave.setOnMouseClicked(e -> {

            if (selectedEvent == null) {
                eventModel.createEvent(new Event(
                        eventModel.getMaxID() + 1,
                        txfStartDate.getText(),
                        txfEndDate.getText(),
                        txfLocation.getText(),
                        txfNotes.getText(),
                        txfLocationGuidance.getText(),
                        txfEventName.getText()
                ));
                JOptionPane.showMessageDialog(null, "Successfully saved selected event");
            } else {
                eventModel.updateEvent(new Event(
                        selectedEvent.getId(),
                        txfStartDate.getText(),
                        txfEndDate.getText(),
                        txfLocation.getText(),
                        txfNotes.getText(),
                        txfLocationGuidance.getText(),
                        txfEventName.getText()
                ));
                JOptionPane.showMessageDialog(null, "Successfully updated selected event");
            }

            ManageEventsScreen(container);
        });

        btnCancel.setOnMouseClicked(e -> {
            ManageEventsScreen(container);
        });
    }

    private void displayCreateEvent(AnchorPane container) {
        displayCreateEvent(container, null);
    }

    private void updateTableEvents(AnchorPane container) {
        if (listsUpdated) {
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

    private void displayTicketsTableView(AnchorPane container, int id) {
        ticketsTable = new TableView<>();

        TableColumn<Ticket, String> nameColumn = new TableColumn<>();
        nameColumn.setResizable(false);
        nameColumn.setText("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Ticket, String> emailColumn = new TableColumn<>();
        emailColumn.setResizable(false);
        emailColumn.setText("Email");
        emailColumn.setMaxWidth(75);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        TableColumn<Ticket, Integer> priceColumn = new TableColumn<>();
        priceColumn.setResizable(false);
        priceColumn.setText("Price");
        priceColumn.setMaxWidth(75);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        TableColumn<Ticket, String> typeColumn = new TableColumn<>();
        typeColumn.setResizable(false);
        typeColumn.setText("Type");
        typeColumn.setMinWidth(110);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));


        //if (ticketModel.getAllTickets() != null)

        fillTicketsTable(ticketsTable, id);
        ticketsTable.setLayoutX(container.getLayoutX() - 310);
        ticketsTable.setLayoutY(container.getLayoutY() + 40);
        ticketsTable.setMaxHeight(container.getMinHeight() - 100);
        ticketsTable.setMaxWidth(360);

        ticketsTable.getColumns().addAll(nameColumn, emailColumn, priceColumn, typeColumn);
        container.getChildren().add(ticketsTable);
    }

    private void fillTicketsTable(TableView ticketsTable, int id) {

        ticketsTable.setItems(ticketModel.getTicketsByEventID(id));
    }


    private void ManageTicketsScreen(AnchorPane container, Event selectedEvent) {
        //TODO display event name,
        // generate tableview with 2 buttons (use and delete)
        // display search bar and go back button

        clearContainer(container);

        Label title = new Label();
        TextField searchBox = new TextField();
        Button searchButton = new Button();
        Button goBack = new Button();
        Button newTicket = new Button();
        Button useBtn = new Button();
        Button delBtn = new Button();
        Button editBtn = new Button();
        Button printBtn = new Button();

        newTicket.setText("New Ticket");
        newTicket.getStyleClass().addAll("app-buttons");
        newTicket.setLayoutX(goBack.getLayoutX() + 20);
        newTicket.setLayoutY(container.getMinHeight() - newTicket.getMinHeight() - 50);

        useBtn.setText("USE");
        useBtn.getStyleClass().addAll("app-buttons");
        useBtn.setLayoutX(newTicket.getLayoutX() + 100);
        useBtn.setLayoutY(newTicket.getLayoutY());

        delBtn.setText("DELETE");
        delBtn.getStyleClass().addAll("app-buttons", "negative-buttons");
        delBtn.setLayoutX(useBtn.getLayoutX() + 70);
        delBtn.setLayoutY(useBtn.getLayoutY());

        editBtn.setText("Edit");
        editBtn.getStyleClass().addAll("app-buttons");
        editBtn.setLayoutX(delBtn.getLayoutX() + 80);
        editBtn.setLayoutY(delBtn.getLayoutY());

        printBtn.setText("Print");
        printBtn.getStyleClass().addAll("app-buttons");
        printBtn.setLayoutX(editBtn.getLayoutX()+70);
        printBtn.setLayoutY(editBtn.getLayoutY());


        goBack.setText("←");
        goBack.getStyleClass().addAll("app-buttons", "negative-buttons");
        goBack.setStyle("-fx-font-size: 13");
        goBack.setLayoutX(container.getLayoutX() - 300);
        goBack.setLayoutY(container.getLayoutY() + 5);
        goBack.setStyle("-fx-font-size: 12");

        title.setText(selectedEvent.getEventName());
        title.setLayoutX(goBack.getLayoutX() + 40);
        title.setLayoutY(goBack.getLayoutY() + 2);
        title.setStyle("-fx-font-size: 15");

        searchBox.setPromptText("Search...");
        searchBox.setLayoutX(title.getLayoutX() + 150);
        searchBox.setLayoutY(title.getLayoutY());
        searchBox.setPrefWidth(130);

        searchButton.setText("\uD83D\uDD0D");
        searchButton.setStyle("-fx-font-size: 12");
        searchButton.getStyleClass().add("app-buttons");
        searchButton.setLayoutX(searchBox.getLayoutX() + searchBox.getMinWidth() + 140);
        searchButton.setLayoutY(searchBox.getLayoutY());

        container.getChildren().addAll(title, searchBox, searchButton, goBack, newTicket, useBtn, delBtn, editBtn, printBtn);
        displayTicketsTableView(container, selectedEvent.getId());


        goBack.setOnMouseClicked(e -> {
            ManageEventsScreen(container);
        });

        newTicket.setOnMouseClicked(event -> {
            displayCreateTicket(container, selectedEvent);
        });

        delBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem()==null){
                JOptionPane.showMessageDialog(null, "Please select a ticket");
            }else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                ticketModel.deleteTicket(selectedTicket);
                //TODO check if it is right "getID"
                displayTicketsTableView(container, selectedTicket.getId());
            }
        });

        useBtn.setOnMouseClicked(event -> {
            if(ticketsTable.getSelectionModel().getSelectedItem()==null){
                JOptionPane.showMessageDialog(null,"Please select a ticket");
            }else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                selectedTicket.equals(true);
                useBtn.setDisable(true);
            }
        });

        //TODO Check if it is right
        editBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem()==null){
                JOptionPane.showMessageDialog(null,"Please select a ticket");
            } else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                displayCreateTicket(container,selectedEvent);
            }
        });



        printBtn.setOnMouseClicked(event -> {
            //TODO
        });
    }

    private void displayCreateTicket(AnchorPane container, Event selectedEvent, Ticket selectedTicket) {
        clearContainer(container);

        Label eventLbl = new Label();
        Label nameLbl = new Label();
        Label emailLbl = new Label();
        Label notesLbl = new Label();
        Label locLbl = new Label();
        Label locField = new Label();
        Label locGuideLbl = new Label();
        Label ticketPriceLbl = new Label();
        Label ticketPriceField = new Label();
        Label ticketTypeLbl = new Label();
        Label ticketTypeField = new Label();
        Button savBtn = new Button();
        Button canclBtn = new Button();
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField notesField = new TextField();
        TextField locGuideField = new TextField();

        nameLbl.setText("Name");
        nameLbl.setLayoutX(container.getLayoutX() - 290);
        nameLbl.setLayoutY(container.getLayoutY() + 40);

        nameField.setLayoutX(nameLbl.getLayoutX());
        nameField.setLayoutY(nameLbl.getLayoutY() + 20);

        emailLbl.setText("Email");
        emailLbl.setLayoutX(nameField.getLayoutX());
        emailLbl.setLayoutY(nameField.getLayoutY() + 30);

        emailField.setLayoutX(emailLbl.getLayoutX());
        emailField.setLayoutY(emailLbl.getLayoutY() + 20);

        notesLbl.setText("Notes");
        notesLbl.setLayoutX((container.getLayoutX() / 2) + 60);
        notesLbl.setLayoutY(nameLbl.getLayoutY());

        notesField.setLayoutX(notesLbl.getLayoutX());
        notesField.setLayoutY(nameField.getLayoutY());
        notesField.setPrefWidth(150);
        notesField.setPrefHeight(150);

        locLbl.setText("Location");
        locLbl.setLayoutX(emailField.getLayoutX());
        locLbl.setLayoutY(emailField.getLayoutY() + 30);

        locField.setText(selectedEvent.getLocation());
        locField.setLayoutX(locLbl.getLayoutX());
        locField.setLayoutY(locLbl.getLayoutY() + 20);
        locField.setStyle("-fx-font-weight: normal");

        locGuideLbl.setText("Location Guidance");
        locGuideLbl.setLayoutX(locLbl.getLayoutX());
        locGuideLbl.setLayoutY(locLbl.getLayoutY() + 60);

        locGuideField.setLayoutX(locGuideLbl.getLayoutX());
        locGuideField.setLayoutY(locLbl.getLayoutY() + 90);
        locGuideField.setPrefSize(350, 110);

        eventLbl.setText(selectedEvent.getEventName());
        eventLbl.setLayoutX(container.getLayoutX() / 2);
        eventLbl.setLayoutY(container.getLayoutY() + 5);

        savBtn.setPrefWidth(60);
        savBtn.setText("Save");
        savBtn.setLayoutX(container.getLayoutX() - 300);
        savBtn.setLayoutY(container.getLayoutY() + 350);

        canclBtn.setPrefWidth(70);
        canclBtn.setText("Cancel");
        canclBtn.setLayoutX(container.getLayoutX());
        canclBtn.setLayoutY(container.getLayoutY() + 350);

        container.getChildren().addAll(savBtn, canclBtn, eventLbl, nameLbl, emailLbl, notesLbl, locLbl, locGuideLbl, nameField, emailField, notesField, locField, locGuideField);


        //TODO need to fix
        /*savBtn.setOnMouseClicked(e -> {
            if (selectedTicket!=null){
                ticketModel.createTicket(new Ticket(
                        ticketModel.getMaxID()+1,
                        nameField.getText(),
                        emailField.getText(),
                        //not sure how to call properly
                        ticketTypeField.,
                        ticketPriceField.getText(),

                        locGuideField.getText(),
                        notesField.getText()
                ));
                JOptionPane.showMessageDialog(null, "Succesfully saved ticket");
            }else{
                ticketModel.updateTicket(new Ticket(
                        selectedTicket.getId(),
                        nameField.getText(),
                        emailField.getText(),
                        locGuideField.getText(),
                        notesField.getText()
                ));
                JOptionPane.showMessageDialog(null,"Succesfully updated selected ticket");
            }
            ManageTicketsScreen(container,selectedEvent);
        });*/

        canclBtn.setOnMouseClicked(event -> {
            ManageTicketsScreen(container, eventsTable.getSelectionModel().getSelectedItem());

        });

    }
    private void displayCreateTicket(AnchorPane container, Event selectedEvent){
        displayCreateTicket(container,selectedEvent,null);
    }


    private void ManageSpecialTicketsScreen(AnchorPane container) {

    }

    private void displayManageAccountScreen(AnchorPane container) {
        //sprint 2
    }



}
