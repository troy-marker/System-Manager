/************************************************************************************************************************************************
 * Project Name: SystemManager                                                                                                                  *
 *  Class Name: view.listeners.NewWorkerListener                                                                                                *
 *  File Name: NewWorkerListener.java                                                                                                           *
 *  File Creation Date: 8/29/2022                                                                                                               *
 *  File Creation Time: 13:1:48                                                                                                                 *
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
package view.listeners;

import view.events.NewWorkerEvent;

import java.util.EventListener;

public interface NewWorkerListener extends EventListener {
    public void add(NewWorkerEvent e);

}
