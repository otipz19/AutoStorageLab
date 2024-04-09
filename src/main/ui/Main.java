package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.components.buttons.*;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.forms.group.GroupCreateForm;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new AppFrame();
        frame.setVisible(true);
    }
}