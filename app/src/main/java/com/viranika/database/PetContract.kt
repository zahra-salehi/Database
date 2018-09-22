/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.viranika.database

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

/**
 * API Contract for the Pets app.
 */
object PetContract {

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    val CONTENT_AUTHORITY = "com.example.android.pets"

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    val PATH_PETS = "pets"

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    class PetEntry : BaseColumns {
        companion object {

            /** The content URI to access the pet data in the provider  */
            val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS)

            /**
             * The MIME type of the [.CONTENT_URI] for a list of pets.
             */
            val CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS

            /**
             * The MIME type of the [.CONTENT_URI] for a single pet.
             */
            val CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS

            /** Name of database table for pets  */
            val TABLE_NAME = "pets"

            /**
             * Unique ID number for the pet (only for use in the database table).
             *
             * Type: INTEGER
             */
            val _ID = BaseColumns._ID

            /**
             * Name of the pet.
             *
             * Type: TEXT
             */
            val COLUMN_PET_NAME = "name"

            /**
             * Breed of the pet.
             *
             * Type: TEXT
             */
            val COLUMN_PET_BREED = "breed"

            /**
             * Gender of the pet.
             *
             * The only possible values are [.GENDER_UNKNOWN], [.GENDER_MALE],
             * or [.GENDER_FEMALE].
             *
             * Type: INTEGER
             */
            val COLUMN_PET_GENDER = "gender"

            /**
             * Weight of the pet.
             *
             * Type: INTEGER
             */
            val COLUMN_PET_WEIGHT = "weight"

            /**
             * Possible values for the gender of the pet.
             */
            val GENDER_UNKNOWN = 0
            val GENDER_MALE = 1
            val GENDER_FEMALE = 2

            /**
             * Returns whether or not the given gender is [.GENDER_UNKNOWN], [.GENDER_MALE],
             * or [.GENDER_FEMALE].
             */
            fun isValidGender(gender: Int): Boolean {
                return if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                    true
                } else false
            }
        }
    }

}// To prevent someone from accidentally instantiating the contract class,
// give it an empty constructor.

