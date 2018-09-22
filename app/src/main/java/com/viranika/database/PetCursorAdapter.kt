package com.viranika.database

import android.content.Context
import android.database.Cursor
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

/**
 * [PetCursorAdapter] is an adapter for a list or grid view
 * that uses a [Cursor] of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the [Cursor].
 */
class PetCursorAdapter
/**
 * Constructs a new [PetCursorAdapter].
 *
 * @param context The context
 * @param c       The cursor from which to get the data.
 */
(context: Context, c: Cursor?= null)/* flags */ : CursorAdapter(context, c, 0) {
    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     * moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     * correct row.
     */
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        // Find individual views that we want to modify in the list item layout
        val nameTextView = view.findViewById(R.id.name) as TextView
        val summaryTextView = view.findViewById(R.id.summary) as TextView

        // Find the columns of pet attributes that we're interested in
        val nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME)
        val breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED)

        // Read the pet attributes from the Cursor for the current pet
        val petName = cursor.getString(nameColumnIndex)
        var petBreed = cursor.getString(breedColumnIndex)

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed)
        }

        // Update the TextViews with the attributes for the current pet
        nameTextView.text = petName
        summaryTextView.text = petBreed
    }
}