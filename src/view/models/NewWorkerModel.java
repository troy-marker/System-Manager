/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.models.WorkerTable                                                                                                         *
 *  File Name: NewWorkerModel.java                                                                                                                 *
 *  File Creation Date: 8/27/2022                                                                                                               *
 *  File Creation Time: 21:31:47                                                                                                                *
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
package view.models;

import model.Worker;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NewWorkerModel extends AbstractTableModel {
    private List<Worker> workerDb;
    private String[] colNames = {"ID", "Name", "Position"};
    public NewWorkerModel() {
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<Worker> db) {
        this.workerDb = db;
    }

    @Override
    public int getRowCount() {
        try {
            return workerDb.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Worker worker = workerDb.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return worker.getId();
            case 1:
                return worker.getName();
            case 2:
                return worker.getPosition();
        }
        return null;
    }
}
