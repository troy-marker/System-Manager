/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: controller.Controller                                                                                                           *
 *  File Name: Controller.java                                                                                                                  *
 *  File Creation Date: 8/27/2022                                                                                                               *
 *  File Creation Time: 22:21:7                                                                                                                 *
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
package controller;

import model.Database;
import model.Positions;
import model.Worker;
import view.events.NewWorkerEvent;

import java.util.List;

public class Controller {
    Database db = new Database();

    public List<Worker> getWorkers() {
        return db.getWorkers();
    }

    public void addWorker(NewWorkerEvent ev) {
        String name = ev.getName();
        String position = ev.getPosition();

        Positions positions;

        switch(position) {
            case "Host":
                positions = Positions.Host;
                break;
            case "Server":
                positions = Positions.Server;
                break;
            case "Bartender":
                positions = Positions.Bartender;
                break;
            case "Runner":
                positions = Positions.Runner;
                break;
            default:
                positions = null;
        }

        Worker worker = new Worker(name, positions);

        db.addWorker(worker);
    }
}
