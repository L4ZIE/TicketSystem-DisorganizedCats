package pl.controllers;

import be.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    public AnchorPane anpController,
            anpContent,
            anpMain;

    public mainController() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUserControls(anpController);
        displayEventsTableView(anpContent);
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
        manageUsers.setOnMouseClicked(e->{
            //this is for sprint 2
        });

        manageEvents.setOnMouseClicked(e->{
            ManageEventsScreen(anpContent);
        });

        manageSpecTickets.setOnMouseClicked(e->{
            ManageSpecialTicketsScreen(anpContent);
        });
    }

    private void displayEventsTableView(AnchorPane container) {
        //TODO

        container.getStyleClass().add("container");
    }

    private void ManageEventsScreen(AnchorPane container) {
        //TODO Display: latest events and line, search bar and search button
        displayEventsTableView(container);
        //TODO Display: New Event button

        //Button newEvent = new Button();
        //newEvent.getStyleClass().add("new-event");
        //newEvent.setText("New Event");
        //newEvent.setLayoutX(container.getLayoutX()-50);
        //container.getChildren().addAll(newEvent);
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

        displayEventsTableView(container);

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



        goBack.setLayoutX(container.getLayoutX()-290);
        goBack.setLayoutY(container.getLayoutY()+5);

        searchBar.setLayoutX(container.getLayoutX()-80);
        searchBar.setLayoutY(container.getLayoutY()+5);

        ticketList.setLayoutX(container.getLayoutX()-290);
        ticketList.setLayoutY(container.getLayoutY()+40);



        container.getChildren().addAll(goBack,searchBar,ticketList);

        goBack.setOnMouseClicked(e->{
            ManageSelectedEventScreen(anpContent);
        });

    }

    private void ManageSpecialTicketsScreen(AnchorPane container) {

    }

    private void displayManageAccountScreen(AnchorPane container) {
        //sprint 2
    }


}
