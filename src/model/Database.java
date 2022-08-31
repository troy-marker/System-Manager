/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: model.Database                                                                                                                  *
 *  File Name: Database.java                                                                                                                    *
 *  File Creation Date: 8/24/2022                                                                                                               *
 *  File Creation Time: 21:55:51                                                                                                                *
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
package model;

import java.util.LinkedList;
import java.util.List;

public class Database {
    private final List<Worker> workers;

    public Database() {
        workers = new LinkedList<>();
    }
    public void addWorker(Worker worker) {
        workers.add(worker);
    }
    public List<Worker> getWorkers() {
        return workers;
    }

}
