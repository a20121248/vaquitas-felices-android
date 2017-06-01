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

import com.github.alvarosct.happycows.db.models.Porongo;

public class DatabaseInitializer {

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static void populateWithTestData(AppDatabase db) {

//        TODO: REMOVE THIS! DUMMY DATA UNTIL THE WS ARE DONE!
        if (db.porongoModel().getCountAll() == 0) {

            addPorongo(db, "Gonzales", 33);
            addPorongo(db, "Agapito", 34);
            addPorongo(db, "Vega", 35);
        }

    }

    private static void addPorongo(AppDatabase db, String gonzales, int i1) {
        Porongo p = new Porongo();
        p.setFamilia(gonzales);
        p.setNumero(i1);
        db.modelByName("Porongo").insert(p);
//        db.porongoModel().insert(p);
    }
}
