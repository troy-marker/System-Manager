/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.dialogs.data.Worker                                                                                                        *
 *  File Name: Worker.java                                                                                                                        *
 *  File Creation Date: 8/28/2022                                                                                                               *
 *  File Creation Time: 9:50:46                                                                                                                 *
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
package view.dialogs.data;

import model.Positions;
import view.events.NewWorkerEvent;
import view.listeners.NewWorkerListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NewWorkerDialog extends JDialog {
    private final JButton okButton;
    private final JButton cancelButton;
    private final JTextField nameField;
    private final JLabel nameLabel;
    private final JLabel positionLabel;
    private final JComboBox<Positions> positionField;
    private NewWorkerListener newWorkerListener;
    public NewWorkerDialog(JFrame parent) {
        super(parent, "New Worker",false);
        okButton = new JButton( "OK");
        cancelButton = new JButton("Cancel");
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(20);
        positionLabel = new JLabel("Position: ");
        positionField = new JComboBox<>(Positions.values());
        cancelButton.addActionListener(e -> setVisible(false));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String position = Objects.requireNonNull(positionField.getSelectedItem()).toString();
                NewWorkerEvent ev = new NewWorkerEvent(this, name, position);
                setVisible(false);
                if (newWorkerListener != null) {
                    newWorkerListener.add(ev);
                }
            }
        });
        layoutControls();
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
        gc.fill =- GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(nameLabel, gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(nameField, gc);
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(positionLabel, gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(positionField, gc);

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    public void setNewWorkerListener(NewWorkerListener newWorkerListener) {
        this.newWorkerListener = newWorkerListener;
    }

}
