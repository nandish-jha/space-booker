package com.space_booker.view;

import com.space_booker.controller.SpaceBookerController;
import javafx.scene.control.Button;

public class CreateAdminView extends LoginView {
    private Button _backButton = new Button("Go Back");
    public CreateAdminView() {
        isAdmin = true;
        isSwitchButtonVisible = false;
        _accountActionState = AccountActionType.CREATE;
        addElementsToView();
        _root.getChildren().add(_backButton);
    }
    public void setController(SpaceBookerController controller) {
        _backButton.setOnAction(e -> {
            controller.switchToAdminView();
        });
    }
}
