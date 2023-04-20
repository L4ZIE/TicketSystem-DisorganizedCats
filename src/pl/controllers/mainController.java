package pl.controllers;

import be.Account;
import be.Event;
import be.SpecialTicket;
import be.Ticket;
import com.sun.tools.javac.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.models.EventModel;
import pl.models.SpecialTicketModel;
import pl.models.TicketModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import pl.models.AccountModel;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    private EventModel eventModel;
    private AccountModel accountModel;
    private boolean userType;
    private SpecialTicketModel specTicketModel;
    private String qrCode;
    private TicketModel ticketModel;
    private int id;
    private boolean listsUpdated = false;
    private TableView<Event> eventsTable;
    private TableView<SpecialTicket> specialTicketsTable;
    private Boolean isAdmin;

    private TableView<Ticket> ticketsTable;

    private TableView<Account> accountTable;

    public mainController() {
    }
    //Disclaimer: This is a gigantic bloater, I'd have personally taken it apart into multiple classes
    //but for the purpose of inexperienced members and me not having time to teach how the classes could
    //interact with each other while having loose coupling I have decided to have the bloater instead.

    //For easier navigation, I have included regions.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        accountModel = new AccountModel();
        specTicketModel = new SpecialTicketModel();
        ticketModel = new TicketModel();
        isAdmin = checkIfAdmin();
        displayUserControls(anpController);
        anpContent.getStyleClass().add("container");
        anpMain.setStyle("-fx-background-color: #474747");
    }

    //region Utility
    private Boolean checkIfAdmin() {
        return accountModel.getAccountByName(LoginController.getUsername()).getAccountType();
    }

    private void displayUserControls(AnchorPane container) {
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

        if (!isAdmin)
            manageUsers.setDisable(true);

        container.getChildren().addAll(userControls, buttonContainer);

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

    private void clearContainer(AnchorPane container) {
        container.getChildren().clear();
    }

    //endregion
    //region Events
    private void fillEventsTable(TableView eventsTable) {
        eventsTable.setItems(eventModel.getAllEvents());
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


        container.getChildren().addAll(lblTitle, txfSearchBox, btnSearchButton, btnNewEvent,
                btnDeleteEvent, btnEditEvent, btnManageTickets);
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

        txfSearchBox.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                eventModel.searchForEvent(txfSearchBox.getText());
            }
        });

        btnSearchButton.setOnAction(event -> {
            eventModel.searchForEvent(txfSearchBox.getText());
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
            if (txfEventName.getText().equals("") || txfStartDate.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill out the required fields.");
            } else {
                if (selectedEvent == null) {
                    eventModel.createEvent(new Event(
                            eventModel.getMaxID() + 1,
                            txfStartDate.getText(),
                            txfEndDate.getText(),
                            txfLocation.getText(),
                            txfLocationGuidance.getText(),
                            txfNotes.getText(),
                            txfEventName.getText()
                    ));
                    JOptionPane.showMessageDialog(null, "Successfully saved selected event");
                } else {
                    eventModel.updateEvent(new Event(
                            selectedEvent.getId(),
                            txfStartDate.getText(),
                            txfEndDate.getText(),
                            txfLocation.getText(),
                            txfLocationGuidance.getText(),
                            txfNotes.getText(),
                            txfEventName.getText()
                    ));
                    JOptionPane.showMessageDialog(null, "Successfully updated selected event");
                }

                ManageEventsScreen(container);
            }


        });

        btnCancel.setOnMouseClicked(e -> {
            ManageEventsScreen(container);
        });
    }

    private void displayCreateEvent(AnchorPane container) {
        displayCreateEvent(container, null);
    }

    private void ManageSelectedEventScreen(AnchorPane container) {
        displayEventsTableView(container);
    }

    //endregion
    //region Tickets
    private void displayTicketsTableView(AnchorPane container, int id) {
        ticketsTable = new TableView();

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

        TableColumn<Ticket, Boolean> usedColumn = new TableColumn<>();
        usedColumn.setResizable(false);
        usedColumn.setText("Used");
        usedColumn.setMaxWidth(75);
        usedColumn.setCellValueFactory(new PropertyValueFactory<>("used"));

        TableColumn<Ticket, String> typeColumn = new TableColumn<>();
        typeColumn.setResizable(false);
        typeColumn.setText("Type");
        typeColumn.setMinWidth(110);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));


        //if (ticketModel.getAllTickets() != null)


        ticketsTable.setLayoutX(container.getLayoutX() - 310);
        ticketsTable.setLayoutY(container.getLayoutY() + 40);
        ticketsTable.setMaxHeight(container.getMinHeight() - 100);
        ticketsTable.setMaxWidth(360);

        ticketsTable.getColumns().addAll(nameColumn, emailColumn, usedColumn, typeColumn);
        fillTicketsTable(id);
        container.getChildren().add(ticketsTable);
    }

    private void fillTicketsTable(int id) {
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

        useBtn.setText("Use");
        useBtn.getStyleClass().addAll("app-buttons");
        useBtn.setLayoutX(newTicket.getLayoutX() + 100);
        useBtn.setLayoutY(newTicket.getLayoutY());

        delBtn.setText("Delete");
        delBtn.getStyleClass().addAll("app-buttons", "negative-buttons");
        delBtn.setLayoutX(useBtn.getLayoutX() + 70);
        delBtn.setLayoutY(useBtn.getLayoutY());

        editBtn.setText("Edit");
        editBtn.getStyleClass().addAll("app-buttons");
        editBtn.setLayoutX(delBtn.getLayoutX() + 80);
        editBtn.setLayoutY(delBtn.getLayoutY());

        printBtn.setText("Print");
        printBtn.getStyleClass().addAll("app-buttons");
        printBtn.setLayoutX(editBtn.getLayoutX() + 70);
        printBtn.setLayoutY(editBtn.getLayoutY());


        goBack.setText("←");
        goBack.getStyleClass().addAll("app-buttons", "negative-buttons");

        goBack.setLayoutX(container.getLayoutX() - 290);
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

        displayTicketsTableView(container, selectedEvent.getId());

        container.getChildren().addAll(title, searchBox, searchButton, goBack, newTicket, useBtn, delBtn, editBtn, printBtn);
        displayTicketsTableView(container, selectedEvent.getId());


        goBack.setOnMouseClicked(e -> {
            clearContainer(container);
            ManageSelectedEventScreen(anpContent);
            ManageEventsScreen(container);
        });

        newTicket.setOnMouseClicked(event -> {
            displayCreateTicket(container, selectedEvent);
        });

        delBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select a ticket");
            } else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                ticketModel.deleteTicket(selectedTicket.getId());
                displayTicketsTableView(container, selectedEvent.getId());
            }
        });

        useBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select a ticket");
            } else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                if (!ticketsTable.getSelectionModel().getSelectedItem().getUsed()) {
                    ticketModel.useTicket(selectedTicket.getId());
                    fillTicketsTable(selectedEvent.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "This ticket has already been used");
                }
            }
        });


        editBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select a ticket");
            } else {
                Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
                displayCreateTicket(container, selectedEvent, selectedTicket);
            }
        });

        searchBox.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                ticketsTable.setItems(ticketModel.searchForTicket(searchBox.getText()));
            }
        });

        searchButton.setOnMouseClicked(event -> {
            ticketsTable.setItems(ticketModel.searchForTicket(searchBox.getText()));
        });


        printBtn.setOnMouseClicked(event -> {
            if (ticketsTable.getSelectionModel().getSelectedItem() != null)
                createTicketToPrint(selectedEvent, ticketsTable.getSelectionModel().getSelectedItem());
            else
                JOptionPane.showMessageDialog(null, "Please select a ticket to print.");
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
        Button saveBtn = new Button();
        Button cancelBtn = new Button();
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField notesField = new TextField();
        TextField locGuideField = new TextField();

        saveBtn.getStyleClass().add("app-buttons");
        cancelBtn.getStyleClass().addAll("app-buttons", "negative-buttons");

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

        notesField.setText(selectedEvent.getNotes());
        notesField.setDisable(true);

        locGuideField.setText(selectedEvent.getNotes());
        locGuideField.setDisable(true);

        saveBtn.setPrefWidth(60);
        saveBtn.setText("Save");
        saveBtn.setLayoutX(container.getLayoutX() - 300);
        saveBtn.setLayoutY(container.getLayoutY() + 350);


        cancelBtn.setPrefWidth(70);
        cancelBtn.setText("Cancel");
        cancelBtn.setLayoutX(container.getLayoutX());
        cancelBtn.setLayoutY(container.getLayoutY() + 350);

        if (selectedTicket != null) {
            nameField.setText(selectedTicket.getCustomerName());
            emailField.setText(selectedTicket.getCustomerEmail());
        }

        container.getChildren().addAll(saveBtn, cancelBtn, eventLbl, nameLbl, emailLbl, notesLbl, locLbl, locGuideLbl, nameField, emailField, notesField, locField, locGuideField);


        saveBtn.setOnMouseClicked(e -> {
            if (selectedTicket == null) {
                ticketModel.createTicket(new Ticket(
                        ticketModel.getMaxID() + 1,
                        nameField.getText(),
                        emailField.getText(),
                        1,
                        100,
                        "qr placeholder",
                        false,
                        selectedEvent.getId()
                ), selectedEvent.getId());
                JOptionPane.showMessageDialog(null, "Successfully saved ticket");
            } else {
                ticketModel.updateTicket(new Ticket(
                        selectedTicket.getId(),
                        nameField.getText(),
                        emailField.getText(),
                        1,
                        100,
                        "qr placeholder",
                        false,
                        selectedEvent.getId()
                ));
                JOptionPane.showMessageDialog(null, "Successfully updated selected ticket");
            }
            ManageTicketsScreen(container, selectedEvent);
        });

        cancelBtn.setOnMouseClicked(event -> {
            ManageTicketsScreen(container, eventsTable.getSelectionModel().getSelectedItem());

        });

    }

    private void displayCreateTicket(AnchorPane container, Event selectedEvent) {
        displayCreateTicket(container, selectedEvent, null);
    }

    //endregion
    //region Print tickets
    private void createTicketToPrint(Event event, Ticket ticket) {
        int ticketHeight = 700, ticketWidth = 250;
        StackPane pane = new StackPane();
        Label lblBoldText = new Label();
        Label lblSimpleText = new Label();
        ImageView qrCode;

        lblBoldText.setText(event.getEventName() + "\n\n" +
                "Start Date & Time: " + "\n\n" +
                "End Date & Time: " + "\n\n" +
                "Notes:" + "\n\n" +
                "Location:" + "\n\n" +
                "Location Guidance:" + "\n\n");
        lblSimpleText.setText(
                "\t\t\t\t " + event.getStartDateTime() + "\n\n" +
                        "\t\t\t\t " + event.getEndDateTime() + "\n\n\n" +
                        event.getNotes() + "\n\n" +
                        event.getLocation() + "\n\n" +
                        event.getLocationGuidance() + "\n\n");
        lblSimpleText.setStyle("-fx-font-weight: normal");

        pane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px");
        pane.setMinSize(700, 250);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setAlignment(lblBoldText, Pos.TOP_LEFT);
        pane.setAlignment(lblSimpleText, Pos.CENTER_LEFT);
        pane.setPadding(new Insets(5));

        qrCode = generateQRCode(ticket.getQrCode());
        pane.setAlignment(qrCode, Pos.CENTER_RIGHT);

        pane.getChildren().addAll(lblBoldText, lblSimpleText, qrCode);

        anpMain.getChildren().add(pane);
        savePicture(ticketWidth + 50, ticketHeight);
        anpMain.getChildren().remove(pane);

    }

    private void createSpecialTicketToPrint(SpecialTicket specialTicket) {
        int ticketWidth = 400, ticketHeight = 400;
        StackPane pane = new StackPane();
        Label lblTicketName = new Label();
        ImageView qrCode;

        lblTicketName.setText(specialTicket.getTicketName());

        lblTicketName.setStyle("-fx-font-size: 28px");
        pane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px");
        pane.setMinSize(ticketWidth, ticketHeight);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setAlignment(lblTicketName, Pos.TOP_CENTER);
        pane.setPadding(new Insets(5));

        qrCode = generateQRCode(specialTicket.getQrCode());
        pane.setAlignment(qrCode, Pos.CENTER);

        pane.getChildren().addAll(lblTicketName, qrCode);

        anpMain.getChildren().add(pane);
        savePicture(ticketWidth, ticketHeight);
        anpMain.getChildren().remove(pane);

    }

    private ImageView generateQRCode(String data) {
        QRCodeWriter writer = new QRCodeWriter();
        int height = 300, width = 300;
        BufferedImage qrImage = null;
        ImageView generatedQR = new ImageView();

        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);
            qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            qrImage.createGraphics();

            Graphics2D graphics = (Graphics2D) qrImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

        } catch (WriterException e) {

        }
        generatedQR.setImage(SwingFXUtils.toFXImage(qrImage, null));

        return generatedQR;
    }

    private void savePicture(int width, int height) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        fileChooser.setInitialFileName("Ticket");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(height, width);
                anpMain.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Couldn't save image.\nError Message:\n" + e.getMessage());
            }
        }

    }

    //endregion
    //region Special Tickets
    private void ManageSpecialTicketsScreen(AnchorPane container) {
        clearContainer(container);

        Label lblTitle = new Label();
        TextField txfSearch = new TextField();

        Button btnSearch = new Button();
        Button btnNewSpecTicket = new Button();
        Button btnDeleteSpecTicket = new Button();
        Button btnEditSpecTicket = new Button();
        Button btnPrintTicket = new Button();
        Button btnUse = new Button();

        lblTitle.setText("Special Tickets");
        lblTitle.setLayoutX(container.getLayoutX() - 300);
        lblTitle.setLayoutY(container.getLayoutY());

        txfSearch.setPromptText("Search...");
        txfSearch.setLayoutX(lblTitle.getLayoutX() + 125);
        txfSearch.setLayoutY(lblTitle.getLayoutY());
        txfSearch.setMinWidth(container.getMinWidth() / 2);

        btnSearch.setText("\uD83D\uDD0D");
        btnSearch.setLayoutX(txfSearch.getLayoutX() + txfSearch.getMinWidth() + 5);
        btnSearch.setLayoutY(txfSearch.getLayoutY());
        btnSearch.setStyle("-fx-font-size: 12");
        btnSearch.getStyleClass().add("app-buttons");

        btnNewSpecTicket.setText("Create");
        btnNewSpecTicket.setLayoutX(lblTitle.getLayoutX());
        btnNewSpecTicket.setLayoutY(container.getMinHeight() - btnNewSpecTicket.getMinHeight() - 50);
        btnNewSpecTicket.getStyleClass().addAll("app-buttons");

        btnEditSpecTicket.setText("Edit");
        btnEditSpecTicket.setLayoutX(btnNewSpecTicket.getLayoutX() + 75);
        btnEditSpecTicket.setLayoutY(btnNewSpecTicket.getLayoutY());
        btnEditSpecTicket.getStyleClass().addAll("app-buttons");

        btnDeleteSpecTicket.setText("Delete");
        btnDeleteSpecTicket.setLayoutX(btnEditSpecTicket.getLayoutX() + 80);
        btnDeleteSpecTicket.setLayoutY(btnEditSpecTicket.getLayoutY());
        btnDeleteSpecTicket.getStyleClass().addAll("app-buttons", "negative-buttons");

        btnUse.setText("Use");
        btnUse.setLayoutX(btnDeleteSpecTicket.getLayoutX() + 90);
        btnUse.setLayoutY(btnDeleteSpecTicket.getLayoutY());
        btnUse.getStyleClass().addAll("app-buttons");

        btnPrintTicket.setText("Print");
        btnPrintTicket.setLayoutX(btnUse.getLayoutX() + 75);
        btnPrintTicket.setLayoutY(btnUse.getLayoutY());
        btnPrintTicket.getStyleClass().addAll("app-buttons");

        container.getChildren().addAll(lblTitle, txfSearch, btnSearch, btnNewSpecTicket, btnDeleteSpecTicket,
                btnEditSpecTicket, btnPrintTicket, btnUse);
        displaySpecialTicketsTableView(container);

        btnNewSpecTicket.setOnAction(event -> {
            createSpecTicketPopUp(container);

        });

        txfSearch.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                specTicketModel.searchForSpecTicket(txfSearch.getText());
            }
        });

        btnSearch.setOnAction(event -> {
            specTicketModel.searchForSpecTicket(txfSearch.getText());
        });

        btnUse.setOnAction(event -> {
            SpecialTicket selectedItem = specialTicketsTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null && !selectedItem.getUsed()) {
                specTicketModel.setUseForSpecTicket(selectedItem.getId(), true);
                ManageSpecialTicketsScreen(container);
            } else if (selectedItem != null && selectedItem.getUsed()) {
                JOptionPane.showMessageDialog(null, "The selected ticket is already used.");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a ticket to use.");
            }
        });

        btnDeleteSpecTicket.setOnMouseClicked(event -> {
            if (specialTicketsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an ticket.");
            } else {
                SpecialTicket selectedItem = specialTicketsTable.getSelectionModel().getSelectedItem();
                specTicketModel.deleteSpecTicket(selectedItem);
                displaySpecialTicketsTableView(container);
            }
        });
        btnEditSpecTicket.setOnMouseClicked(event -> {
            if (specialTicketsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an ticket.");
            } else {
                SpecialTicket selectedItem = specialTicketsTable.getSelectionModel().getSelectedItem();
                createSpecTicketPopUp(container, selectedItem);

            }
        });
        btnPrintTicket.setOnMouseClicked(e -> {
            if (specialTicketsTable.getSelectionModel().getSelectedItem() != null)
                createSpecialTicketToPrint(specialTicketsTable.getSelectionModel().getSelectedItem());
            else
                JOptionPane.showMessageDialog(null, "Please select a ticket to print.");
        });
    }

    private void createSpecTicketPopUp(AnchorPane container) {
        createSpecTicketPopUp(container, null);
    }

    private void createSpecTicketPopUp(AnchorPane container, SpecialTicket selectedItem) {
        clearContainer(container);

        Label lblTitle = new Label();
        lblTitle.setText("Special Ticket");
        lblTitle.setLayoutX(container.getLayoutX() - 155);
        lblTitle.setLayoutY(container.getLayoutY() + 10);

        Label lblTicketName = new Label();
        lblTicketName.setText("Ticket Name");
        lblTicketName.setLayoutX(container.getLayoutX() - 300);
        lblTicketName.setLayoutY(container.getLayoutY() + 50);

        TextField txfTicketName = new TextField();
        txfTicketName.setPrefWidth(350);
        txfTicketName.setLayoutX(container.getLayoutX() - 300);
        txfTicketName.setLayoutY(lblTicketName.getLayoutY() + 25);

        Label lblTicketAmount = new Label();
        lblTicketAmount.setText("Ticket Amount");
        lblTicketAmount.setLayoutX(container.getLayoutX() - 300);
        lblTicketAmount.setLayoutY(txfTicketName.getLayoutY() + 60);

        TextField txfTicketAmount = new TextField();
        txfTicketAmount.setPrefWidth(55);
        txfTicketAmount.setLayoutX(lblTicketAmount.getLayoutX() + 110);
        txfTicketAmount.setLayoutY(lblTicketAmount.getLayoutY());

        Label lblEvents = new Label();
        lblEvents.setText("Events");
        lblEvents.setLayoutX(container.getLayoutX() - 300);
        lblEvents.setLayoutY(txfTicketAmount.getLayoutY() + 60);

        ChoiceBox<String> choiceBoxAllEvents = new ChoiceBox<>();
        choiceBoxAllEvents.setPrefWidth(290);
        choiceBoxAllEvents.setLayoutX(lblEvents.getLayoutX() + 60);
        choiceBoxAllEvents.setLayoutY(lblEvents.getLayoutY());
        choiceBoxAllEvents.getStyleClass().addAll("textField");

        Label lblAddRemove = new Label();
        lblAddRemove.setText("Add/Remove");
        lblAddRemove.setLayoutX(container.getLayoutX() - 300);
        lblAddRemove.setLayoutY(choiceBoxAllEvents.getLayoutY() + 60);

        ChoiceBox<String> choiceBoxNewEvents = new ChoiceBox<>();
        choiceBoxNewEvents.setPrefWidth(250);
        choiceBoxNewEvents.setLayoutX(lblAddRemove.getLayoutX() + 100);
        choiceBoxNewEvents.setLayoutY(lblAddRemove.getLayoutY());
        choiceBoxNewEvents.getStyleClass().addAll("choice-box");


        Button btnGoBack = new Button();
        btnGoBack.setText("<-");
        btnGoBack.setLayoutX(container.getLayoutX() - 290);
        btnGoBack.setLayoutY(container.getLayoutY() + 5);
        btnGoBack.getStyleClass().addAll("app-buttons", "negative-buttons");

        Button btnAdd = new Button();
        btnAdd.setText("Add Event");
        btnAdd.setLayoutX(container.getLayoutX() - 200);
        btnAdd.setLayoutY(choiceBoxNewEvents.getLayoutY() + 60);
        btnAdd.getStyleClass().addAll("app-buttons", "positive-buttons");

        Button btnDelete = new Button();
        btnDelete.setText("Remove");
        btnDelete.setLayoutX(btnAdd.getLayoutX() + 190);
        btnDelete.setLayoutY(choiceBoxNewEvents.getLayoutY() + 60);
        btnDelete.getStyleClass().addAll("app-buttons", "negative-buttons");

        Button btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setPrefWidth(80);
        btnSave.setLayoutX(container.getLayoutX() - 300);
        btnSave.setLayoutY(btnDelete.getLayoutY() + 40);
        btnSave.getStyleClass().addAll("app-buttons");

        if (selectedItem != null) {
            List<Event> events = eventModel.getEventsBySpecTicketID(selectedItem.getId());
            ObservableList<String> eventNames = FXCollections.observableArrayList();

            for (Event e : events) {
                eventNames.add(e.getEventName());
            }

            System.out.println(eventNames.size());
            choiceBoxNewEvents.setItems(eventNames);

        }

        //force numbers only to ticket amount
        txfTicketAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txfTicketAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        if (selectedItem != null) {
            txfTicketAmount.setDisable(true);
            txfTicketName.setText(selectedItem.getTicketName());
        }

        btnGoBack.setOnAction(event -> {
            ManageSpecialTicketsScreen(container);
        });


        ObservableList<String> eventsName = FXCollections.observableArrayList();
        for (Event e : eventModel.getAllEvents()) {
            eventsName.add(e.getEventName());
        }
        choiceBoxAllEvents.setItems(eventsName);

        ObservableList<String> addedEvents = FXCollections.observableArrayList();
        btnAdd.setOnAction(event -> {
            String selectedEvent = choiceBoxAllEvents.getSelectionModel().getSelectedItem();
            Boolean exist = false;
            for (String s : addedEvents) {
                if (s == selectedEvent) {
                    exist = true;
                }
            }
            if (exist == false) {
                addedEvents.add(selectedEvent);
                eventModel.addEventToSpecTicket(selectedItem.getId(), eventModel.getEventByName(selectedEvent).getId());
            } else
                JOptionPane.showMessageDialog(null, "This event is already added.");
            choiceBoxNewEvents.setItems(addedEvents);

        });

        btnSave.setOnMouseClicked(event -> {
            if (selectedItem != null) {
                specTicketModel.updateSpecTicket(new SpecialTicket(selectedItem.getId(), txfTicketName.getText(), selectedItem.getQrCode(), selectedItem.getUsed()));

                JOptionPane.showMessageDialog(null, "Successfully updated special ticket.");
            } else {
                List<SpecialTicket> tickets = new ArrayList<>();

                for (int i = 0; i < Integer.parseInt(txfTicketAmount.getText()); i++) {
                    tickets.add(new SpecialTicket(i, txfTicketName.getText(), "placeholder", false));
                }
                specTicketModel.massCreateSpecTicket(tickets);
                JOptionPane.showMessageDialog(null, "Successfully created " + txfTicketAmount + "tickets.");
            }

        });
        btnDelete.setOnMouseClicked(e -> {
            String selectedEvent = choiceBoxNewEvents.getSelectionModel().getSelectedItem();
            eventModel.deleteEventFromSpecTicket(selectedItem.getId(), eventModel.getEventByName(selectedEvent).getId());
        });

        container.getChildren().addAll(lblTitle, lblTicketName, txfTicketName, lblTicketAmount, txfTicketAmount,
                lblEvents, choiceBoxNewEvents, lblAddRemove, choiceBoxAllEvents, btnAdd, btnDelete, btnSave, btnGoBack);
    }

    private void displaySpecialTicketsTableView(AnchorPane container) {
        specialTicketsTable = new TableView<>();

        TableColumn<SpecialTicket, Integer> colID = new TableColumn<>();
        colID.setResizable(false);
        colID.setText("ID");
        colID.setMaxWidth(40);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<SpecialTicket, String> colName = new TableColumn<>();
        colName.setResizable(false);
        colName.setText(" Ticket Name");
        colName.setMinWidth(150);
        colName.setCellValueFactory(new PropertyValueFactory<>("ticketName"));

        TableColumn<SpecialTicket, Boolean> colUsed = new TableColumn<>();
        colUsed.setResizable(false);
        colUsed.setText("Used");
        colUsed.setMinWidth(100);
        colUsed.setCellValueFactory(new PropertyValueFactory<>("used"));

        fillSpecialTicketsTable(specialTicketsTable);
        specialTicketsTable.setLayoutX(container.getLayoutX() - 265);
        specialTicketsTable.setLayoutY(container.getLayoutY() + 30);
        specialTicketsTable.setMaxHeight(container.getMinHeight() - 100);
        specialTicketsTable.setMaxWidth(container.getMinWidth() - 121);

        specialTicketsTable.getColumns().addAll(colID, colName, colUsed);
        container.getChildren().add(specialTicketsTable);

    }

    private void fillSpecialTicketsTable(TableView<SpecialTicket> specialTicketsTable) {
        specialTicketsTable.setItems(specTicketModel.getAllSpecTickets());
    }

    //endregion
    //region Accounts
    private void fillAccountsTable(TableView accountTable) {
        accountTable.setItems(accountModel.getAllAccounts());
    }

    private void displayAccountTableView(AnchorPane container) {
        accountTable = new TableView<>();

        TableColumn<Account, String> uNameColumn = new TableColumn<>();
        uNameColumn.setResizable(false);
        uNameColumn.setText("User Name");
        uNameColumn.setMinWidth(110); //set to dynamic later
        uNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Account, String> accountTypeColumn = new TableColumn<>();
        accountTypeColumn.setResizable(false);
        accountTypeColumn.setText("Account Type");
        accountTypeColumn.setMinWidth(110); //set to dynamic later
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        fillAccountsTable(accountTable);
        accountTable.setLayoutX(container.getLayoutX() - 300);
        accountTable.setLayoutY(container.getLayoutY() + 30);
        accountTable.setMaxHeight(container.getMinHeight() - 100);
        accountTable.setMaxWidth(container.getMinWidth() - 190);
        accountTable.getColumns().addAll(uNameColumn, accountTypeColumn);
        container.getChildren().add(accountTable);
    }

    private void ManageAccountScreen(AnchorPane container) {
        clearContainer(container);

        Label lblTitle = new Label();

        TextField txfSearchBox = new TextField();

        Button btnNewAccount = new Button();

        Button btnDeleteAccount = new Button();
        Button btnEditAccount = new Button();

        Button btnSearchButton = new Button();

        lblTitle.setText("Manage Users");
        lblTitle.setLayoutX(container.getLayoutX() - 300);
        lblTitle.setLayoutY(container.getLayoutY());

        btnNewAccount.setText("New Account");
        btnNewAccount.setLayoutX(lblTitle.getLayoutX());
        btnNewAccount.setLayoutY(container.getMinHeight() - btnNewAccount.getMinHeight() - 50);
        btnNewAccount.getStyleClass().addAll("app-buttons");

        btnDeleteAccount.setText("Delete");
        btnDeleteAccount.setLayoutX(btnNewAccount.getLayoutX() + 240);
        btnDeleteAccount.setLayoutY(btnNewAccount.getLayoutY());
        btnDeleteAccount.getStyleClass().addAll("app-buttons", "negative-buttons");

        btnEditAccount.setText("Edit");
        btnEditAccount.setLayoutX(btnDeleteAccount.getLayoutX() + 80);
        btnEditAccount.setLayoutY(btnDeleteAccount.getLayoutY());
        btnEditAccount.getStyleClass().addAll("app-buttons");

        txfSearchBox.setPromptText("Search...");

        btnSearchButton.setText("\uD83D\uDD0D");
        btnSearchButton.setStyle("-fx-font-size: 12");

        btnSearchButton.getStyleClass().add("app-buttons");

        txfSearchBox.setLayoutX(lblTitle.getLayoutX() + 125);
        txfSearchBox.setLayoutY(lblTitle.getLayoutY());
        txfSearchBox.setMinWidth(container.getMinWidth() / 2);

        btnSearchButton.setLayoutX(txfSearchBox.getLayoutX() + txfSearchBox.getMinWidth() + 5);
        btnSearchButton.setLayoutY(txfSearchBox.getLayoutY());

        container.getChildren().addAll(lblTitle, btnEditAccount, btnDeleteAccount, btnNewAccount, btnSearchButton, txfSearchBox);
        displayAccountTableView(container);

        txfSearchBox.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                accountTable.setItems(accountModel.searchForAccount(txfSearchBox.getText()));
            }
        });

        btnSearchButton.setOnAction(event -> {
            accountTable.setItems(accountModel.searchForAccount(txfSearchBox.getText()));
        });

        btnDeleteAccount.setOnMouseClicked(event -> {
            if (accountTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an account.");
            } else {
                Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
                accountModel.deleteAccount(selectedAccount.getId());
                displayAccountTableView(container);
            }
        });

        btnEditAccount.setOnMouseClicked(event -> {
            if (accountTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an account.");
            } else {
                Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
                displayCreateAccount(container, selectedAccount);
            }
        });

        btnNewAccount.setOnMouseClicked(e -> {
            displayCreateAccount(container);
        });
    }

    public void displayCreateAccount(AnchorPane container, Account selectedAccount) {
        clearContainer(container);

        Label lblAccountType = new Label();

        TextField txfUserName = new TextField();
        TextField txfUserPassword = new TextField();

        Label lblUserName = new Label();
        Label lblUserPassword = new Label();

        Label lblSelectAccountType = new Label();

        Button btnAdmin = new Button();
        Button btnEventCoordinator = new Button();

        Button btnSave = new Button();
        Button btnCancel = new Button();

        lblAccountType.setText("Create Account");
        lblAccountType.setLayoutX(container.getLayoutX() - 300);
        lblAccountType.setLayoutY(container.getLayoutY());

        lblUserName.setText("User Name");
        lblUserName.setLayoutX(container.getLayoutX() - 300);
        lblUserName.setLayoutY(container.getLayoutY() + 70);

        lblUserPassword.setText("Password");
        lblUserPassword.setLayoutX(lblUserName.getLayoutX());
        lblUserPassword.setLayoutY(lblUserName.getLayoutY() + 60);

        lblSelectAccountType.setText("Select Account Type");
        lblSelectAccountType.setLayoutX(lblUserPassword.getLayoutX());
        lblSelectAccountType.setLayoutY(lblUserPassword.getLayoutY() + 60);

        txfUserName.setPrefWidth(200);
        txfUserName.setPrefHeight(20);
        txfUserName.setLayoutX(lblUserName.getLayoutX());
        txfUserName.setLayoutY(lblUserName.getLayoutY() + 20);

        txfUserPassword.setPrefWidth(200);
        txfUserPassword.setPrefHeight(20);
        txfUserPassword.setLayoutX(lblUserPassword.getLayoutX());
        txfUserPassword.setLayoutY(lblUserPassword.getLayoutY() + 20);

        btnAdmin.setText("Admin");
        btnAdmin.setLayoutX(lblAccountType.getLayoutX());
        btnAdmin.setLayoutY(container.getMinHeight() - btnAdmin.getMinHeight() - 175);
        btnAdmin.getStyleClass().addAll("app-buttons");

        btnEventCoordinator.setText("Event Coordinator");
        btnEventCoordinator.setLayoutX(btnAdmin.getLayoutX() + 60);
        btnEventCoordinator.setLayoutY(btnAdmin.getLayoutY());
        btnEventCoordinator.getStyleClass().addAll("app-buttons");

        btnSave.setPrefWidth(60);
        btnSave.setText("Save");
        btnSave.setLayoutX(container.getLayoutX() - 300);
        btnSave.setLayoutY(container.getLayoutY() + 350);
        btnSave.getStyleClass().addAll("app-buttons");

        btnCancel.setPrefWidth(70);
        btnCancel.setText("Go Back");
        btnCancel.setLayoutX(container.getLayoutX());
        btnCancel.setLayoutY(container.getLayoutY() + 350);
        btnCancel.getStyleClass().addAll("app-buttons");

        container.getChildren().addAll(
                lblAccountType, lblUserName, lblUserPassword, lblSelectAccountType,
                txfUserName, txfUserPassword, btnAdmin, btnEventCoordinator, btnSave, btnCancel);

        if (selectedAccount != null) {
            txfUserName.setText(selectedAccount.getUsername());
            if (selectedAccount.getAccountType())
                btnAdmin.setDisable(true);
            else
                btnEventCoordinator.setDisable(true);
        }

        btnAdmin.setOnAction(event -> {
            if (btnAdmin.isArmed()) {
                btnAdmin.setDisable(true);
                btnEventCoordinator.setDisable(false);
                userType = true;
            }
        });
        btnEventCoordinator.setOnAction(event -> {
            if (btnEventCoordinator.isArmed()) {
                btnAdmin.setDisable(false);
                btnEventCoordinator.setDisable(true);
                userType = false;
            }
        });

        btnSave.setOnMouseClicked(event -> {
            if (txfUserName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pleas enter a username");
            } else if (txfUserPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pleas enter a password");
            }
            if (selectedAccount == null) {
                accountModel.createAccount(new Account(
                        accountModel.getMaxID() + 1,
                        txfUserName.getText(),
                        txfUserPassword.getText(),
                        userType
                ));
                JOptionPane.showMessageDialog(null, "Successfully saved selected account");
            } else {
                accountModel.updateAccount(new Account(
                        selectedAccount.getId(),
                        txfUserName.getText(),
                        txfUserPassword.getText(),
                        userType
                ));
                JOptionPane.showMessageDialog(null, "Successfully updated selected account");
            }
            ManageAccountScreen(container);
            displayAccountTableView(container);
        });
        btnCancel.setOnMouseClicked(e -> {
            ManageAccountScreen(anpContent);
        });


    }

    private void displayCreateAccount(AnchorPane container) {
        displayCreateAccount(container, null);
    }
    //endregion


}
