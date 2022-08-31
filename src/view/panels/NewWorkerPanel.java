/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.panels.Worker                                                                                                              *
 *  File Name: Worker.java                                                                                                                      *
 *  File Creation Date: 8/27/2022                                                                                                               *
 *  File Creation Time: 21:46:16                                                                                                                *
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
package view.panels;

import model.Worker;
import view.models.NewWorkerModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewWorkerPanel extends JPanel {

    private final JTable workers;
    private final NewWorkerModel workersModel;

    public NewWorkerPanel() {
        workersModel = new NewWorkerModel();
        workers = new JTable(workersModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(workers), BorderLayout.CENTER);
    }

    public void setData(List<Worker> db) {
        workersModel.setData(db);
    }

    public void refresh() {
        workersModel.fireTableDataChanged();
    }
}
