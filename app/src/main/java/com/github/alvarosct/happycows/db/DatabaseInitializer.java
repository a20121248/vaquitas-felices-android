/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.alvarosct.happycows.db;

import android.support.annotation.NonNull;

public class DatabaseInitializer {

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }






    //    SHOULD NOT BE CALLED
    private static void populateWithTestData(AppDatabase db) {

//        TODO: REMOVE THIS! DUMMY DATA UNTIL THE WS ARE DONE!
        if (db.ganaderoModel().getCountAll() == 0) {

            addGanadero(db, "Gonzales", 33);
            addGanadero(db, "Agapito", 34);
            addGanadero(db, "Vega", 35);
        }

    }

    private static void addGanadero(AppDatabase db, String gonzales, int i1) {
    }
}
