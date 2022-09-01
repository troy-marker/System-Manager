/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.dialogs.settings.Database                                                                                                  *
 *  File Name: Database.java                                                                                                                    *
 *  File Creation Date: 8/20/2022                                                                                                               *
 *  File Creation Time: 23:36:31                                                                                                                *
 *  File Creator: troylmarker                                                                                                                   *
 *                                                                                                                                              *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                                *
 *                                                                                                                                              *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.            *
 *  You may obtain a copy of the License at                                                                                                     *
 *                                                                                                                                              *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                             *
 *                                                                                                                                              *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,       *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                    *
 *  See the License for the specific language governing permissions and limitations under the License.                                          *
 ************************************************************************************************************************************************/
package view.dialogs.settings;

import model.Worker;
import view.listeners.Preference;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class Database extends JDialog {

    private final JButton okButton;
    private final JButton cancelButton;
    private final JSpinner portSpinner;
    private final JTextField serverField;
    private final JTextField userField;
    private final JPasswordField passField;
    private Preference preference;
    private List<Worker> workers;

    public Database(JFrame parent) {
        super(parent, "Database Settings", false);
        workers = new LinkedList<Worker>();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        serverField = new JTextField(20);
        userField = new JTextField(20);
        passField = new JPasswordField(20);
        passField.setEchoChar('*');
        layoutControls();
        okButton.addActionListener(e -> {
            Integer port = (Integer) portSpinner.getValue();
            String server = serverField.getText();
            String user = userField.getText();
            char[] password = passField.getPassword();

            if (preference != null) {
                preference.set(server, port, user, new String(password));
            }
            setVisible(false);
        });
        cancelButton.addActionListener(e -> setVisible(false));
        setSize(400, 250);
        setLocationRelativeTo(parent);
    }

    private void layoutControls() {
        JPanel controlPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("");
        controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Server:"), gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(serverField, gc);
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Port:"), gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(portSpinner, gc);
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Username: "), gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(userField, gc);
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Password: "), gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(passField, gc);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setDefaults(String server, int port, String user, String pass) {
        serverField.setText(server);
        portSpinner.setValue(port);
        userField.setText(user);
        passField.setText(pass);
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }
    public void setListener(Preference preference) {
        this.preference = preference;
    }
}
