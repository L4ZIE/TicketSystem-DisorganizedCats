package pl.controllers;

import be.Account;
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
import pl.models.AccountModel;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;
    private AccountModel accountModel;
    private int id;
    private boolean listsUpdated = false;
    private TableView<Event> eventsTable;

    private TableView<Account> accountTable;

    public mainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        accountModel = new AccountModel();
        displayUserControls(anpController);
        anpContent.getStyleClass().add("container");
        anpMain.setStyle("-fx-background-color: #474747");
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

        //manageUsers.setDisable(false);//
        manageUsers.setOnMouseClicked(e -> {
            ManageAccountScreen(anpContent);
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
        eventsTable.setMaxWidth(container.getMinWidth() - 50);

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

    private void ManageTicketsScreen(AnchorPane container, Event selectedEvent) {
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
    private void fillAccountsTable(TableView accountTable) {accountTable.setItems(accountModel.getAllAccounts());}
    private void displayAccountTableView(AnchorPane container) {
        accountTable = new TableView<>();

        TableColumn<Account, String> accountTypeColumn = new TableColumn<>();
        accountTypeColumn.setResizable(false);
        accountTypeColumn.setText("AccountType");
        accountTypeColumn.setMinWidth(100); //set to dynamic later
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        TableColumn<Account, String> uNameColumn = new TableColumn<>();
        uNameColumn.setResizable(false);
        uNameColumn.setText("UserName");
        uNameColumn.setMinWidth(110); //set to dynamic later
        uNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Account, String> uPasswordColumn = new TableColumn<>();
        uPasswordColumn.setResizable(false);
        uPasswordColumn.setText("UserPassword");
        uPasswordColumn.setMinWidth(110); //set to dynamic later
        uPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        fillAccountsTable(accountTable);
        accountTable.setLayoutX(container.getLayoutX() - 300);
        accountTable.setLayoutY(container.getLayoutY() + 30);
        accountTable.setMaxHeight(container.getMinHeight() - 100);
        accountTable.setMaxWidth(container.getMinWidth() - 50);
        accountTable.getColumns().addAll(accountTypeColumn, uNameColumn, uPasswordColumn);
        container.getChildren().add(accountTable);
    }
    private void ManageAccountScreen(AnchorPane container) {
        clearContainer(container);

        Label lblTitle = new Label();
        Button btnNewAccount = new Button();
        Button btnManageUsers = new Button();

        Button btnDeleteAccount = new Button();
        Button btnEditAccount = new Button();

        lblTitle.setText("Manage Accounts");
        lblTitle.setLayoutX(container.getLayoutX() - 300);
        lblTitle.setLayoutY(container.getLayoutY());

        btnNewAccount.setText("New Account");
        btnNewAccount.setLayoutX(lblTitle.getLayoutX());
        btnNewAccount.setLayoutY(container.getMinHeight() - btnNewAccount.getMinHeight() - 50);
        btnNewAccount.getStyleClass().addAll("app-buttons");

        btnManageUsers.setText("Manage Accounts");
        btnManageUsers.setLayoutX(btnNewAccount.getLayoutX() + 100);
        btnManageUsers.setLayoutY(btnNewAccount.getLayoutY());
        btnManageUsers.getStyleClass().add("app-buttons");

        btnDeleteAccount.setText("Delete");
        btnDeleteAccount.setLayoutX(btnManageUsers.getLayoutX() + 140);
        btnDeleteAccount.setLayoutY(btnManageUsers.getLayoutY());
        btnDeleteAccount.getStyleClass().addAll("app-buttons", "negative-buttons");

        btnEditAccount.setText("Edit");
        btnEditAccount.setLayoutX(btnDeleteAccount.getLayoutX() + 80);
        btnEditAccount.setLayoutY(btnDeleteAccount.getLayoutY());
        btnEditAccount.getStyleClass().addAll("app-buttons");

        container.getChildren().addAll(lblTitle, btnManageUsers, btnEditAccount, btnDeleteAccount, btnNewAccount);


        displayAccountTableView(container);

        btnDeleteAccount.setOnMouseClicked(account -> {
            if (accountTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an account.");
            } else {
                Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
                accountModel.deleteAccount(selectedAccount.getId());
                displayAccountTableView(container);
            }
        });

        // btnNewAccount.setOnMouseClicked(e -> {
        //   displayCreateAccount(container);
        //});
    }

    public void displayCreateAccount(AnchorPane container, Account selectedAccount){
        clearContainer(container);

        Label lblAccountType = new Label();

        TextField txfUserName = new TextField();
        TextField txfUserPassword = new TextField();

        Button btnAdmin = new Button();
        Button btnEventCoordinator = new Button();

        lblAccountType.setText("Create Account");
        lblAccountType.setLayoutX(container.getLayoutX() - 300);
        lblAccountType.setLayoutY(container.getLayoutY());

        btnAdmin.setText("Admin");
        btnAdmin.setLayoutX(lblAccountType.getLayoutX());
        btnAdmin.setLayoutY(container.getMinHeight() - btnAdmin.getMinHeight() - 50);
        btnAdmin.getStyleClass().addAll("app-buttons");

        btnEventCoordinator.setText("Event Coordinator");
        btnEventCoordinator.setLayoutX(btnAdmin.getLayoutX() + 100 );
        btnEventCoordinator.setLayoutY(btnAdmin.getLayoutY());
        btnEventCoordinator.getStyleClass().addAll("app-buttons");

        container.getChildren().addAll(lblAccountType, txfUserName, txfUserPassword, btnAdmin, btnEventCoordinator);
    }
}
