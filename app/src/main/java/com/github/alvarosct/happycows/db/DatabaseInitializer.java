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

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static void populateWithTestData(AppDatabase db) {

        addPorongo(db, 1, "Gonzales", 33);
        addPorongo(db, 2, "Gonzales", 34);
        addPorongo(db, 3, "Vega", 35);

//        User user2 = addUser(db, "2", "Mike", "Seaver", 12);
//        addUser(db, "3", "Carol", "Seaver", 15);
//
//        Book book1 = addBook(db, "1", "Dune");
//        Book book2 = addBook(db, "2", "1984");
//        Book book3 = addBook(db, "3", "The War of the Worlds");
//        Book book4 = addBook(db, "4", "Brave New World");
//        addBook(db, "5", "Foundation");
//        try {
//            // Loans are added with a delay, to have time for the UI to react to changes.
//
//            Date today = getTodayPlusDays(0);
//            Date yesterday = getTodayPlusDays(-1);
//            Date twoDaysAgo = getTodayPlusDays(-2);
//            Date lastWeek = getTodayPlusDays(-7);
//            Date twoWeeksAgo = getTodayPlusDays(-14);
//
//            addLoan(db, "1", user1, book1, twoWeeksAgo, lastWeek);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "2", user2, book1, lastWeek, yesterday);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "3", user2, book2, lastWeek, today);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "4", user2, book3, lastWeek, twoDaysAgo);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "5", user2, book4, lastWeek, today);
//            Log.d("DB", "Added loans");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void addPorongo(AppDatabase db, int i, String gonzales, int i1) {
        Porongo p = new Porongo();
        p.setUid(i);
        p.setFamilia(gonzales);
        p.setNumero(i1);
        db.porongoModel().insertar(p);
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }
}
