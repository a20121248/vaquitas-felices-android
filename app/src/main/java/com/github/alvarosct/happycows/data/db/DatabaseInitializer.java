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

package com.github.alvarosct.happycows.data.db;

import android.support.annotation.NonNull;

import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.PreguntaInsumo;

public class DatabaseInitializer {

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    //    TODO: REMOVE THIS! SHOULD NOT BE CALLED
    private static void populateWithTestData(AppDatabase db) {

//        TODO: REMOVE THIS! DUMMY DATA UNTIL THE WS ARE DONE!
        addPregunta(db, "¿Esta de acuerdo con el trato brindado por el personal de Vacas Felices?");
        addPregunta(db, "¿Esta de acuerdo con el monto que se le asigna por kilo de leche?");
        addPregunta(db, "¿Cree usted que Vacas Felices contribuye a su desarrollo personal?");

//        TODO: REMOVE THIS! DUMMY DATA UNTIL THE WS ARE DONE!
        addPreguntaInsumo(db, "¿Presenta abolladuras?");
        addPreguntaInsumo(db, "¿Esta roto?");
        addPreguntaInsumo(db, "¿Es fresco?");
        addPreguntaInsumo(db, "¿Huele bien?");
        addPreguntaInsumo(db, "¿Fue entragado a tiempo?");


    }

    private static void addPregunta(AppDatabase db, String preg) {
        Pregunta p = new Pregunta();
        p.setDescripcion(preg);
        db.preguntaModel().insert(p);
    }

    private static void addPreguntaInsumo(AppDatabase db, String preg) {
        PreguntaInsumo p = new PreguntaInsumo();
        p.setDescripcion(preg);
        db.preguntaInsumoModel().insert(p);
    }

}
