/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.frames.MainFrame                                                                                                           *
 *  File Name: MainFrame.java                                                                                                                   *
 *  File Creation Date: 8/19/2022                                                                                                               *
 *  File Creation Time: 17:39:49                                                                                                                *
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
package view.frames;

import controller.Controller;
import view.dialogs.data.NewWorkerDialog;
import view.dialogs.settings.Database;
import view.events.NewWorkerEvent;
import view.listeners.NewWorkerListener;
import view.models.NewWorkerModel;
import view.panels.NewWorkerPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

public class Main extends JFrame {

    final private Database databaseDialog;
    final private NewWorkerDialog newWorkerDialog;
    private final NewWorkerPanel newWorkerPanel;
    private final Preferences prefs;

    private NewWorkerListener newWorkerListener;

    public Main() {
        super("System Manager");
        Image icon = Toolkit.getDefaultToolkit().getImage("src/img/proicon.png");
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        setSize(600, 500);
        setJMenuBar(createMenuBar());
        Controller controller = new Controller();
        newWorkerPanel = new NewWorkerPanel();
        NewWorkerModel newWorkerModel = new NewWorkerModel();
        newWorkerModel.setData(controller.getWorkers());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        databaseDialog = new Database(this);
        newWorkerDialog = new NewWorkerDialog(this);
        prefs = Preferences.userRoot().node("db");
        databaseDialog.setListener((server, port, user, password) -> {
            prefs.put("server", server);
            prefs.putInt("port", port);
            prefs.put("user", user);
            prefs.put("password", password);

        });

        newWorkerDialog.setNewWorkerListener(new NewWorkerListener() {
            @Override
            public void add(NewWorkerEvent e) {
                controller.addWorker(e);
                newWorkerPanel.refresh();
            }
        });

        String server = prefs.get("server", "");
        int port = prefs.getInt("port", 3306);
        String user = prefs.get("user", "");
        String pass = prefs.get("password", "");

        databaseDialog.setDefaults(server, port, user, pass);

        add(newWorkerPanel, BorderLayout.CENTER);
        newWorkerPanel.setVisible(false);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenu newMenu = new JMenu("New ");
        newMenu.setMnemonic(KeyEvent.VK_N);

        JMenuItem newUser = new JMenuItem("User... ");
        newUser.setMnemonic(KeyEvent.VK_U);
        newUser.addActionListener(e -> {
            newWorkerPanel.setVisible(true);
            newWorkerDialog.setVisible(true);
        });


        newMenu.add(newUser);

        JMenu settingsMenu = new JMenu("Settings ");
        settingsMenu.setMnemonic(KeyEvent.VK_S);


        JMenuItem databaseItem = new JMenuItem("Database...");
        databaseItem.setMnemonic(KeyEvent.VK_D);
        databaseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        databaseItem.addActionListener(e -> databaseDialog.setVisible(true));


        settingsMenu.add(databaseItem);

        fileMenu.add(newMenu);
        fileMenu.add(settingsMenu);

        menuBar.add(fileMenu);

        return menuBar;
    }
}
