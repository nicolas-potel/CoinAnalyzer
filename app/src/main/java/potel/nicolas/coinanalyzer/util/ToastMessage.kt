package potel.nicolas.coinanalyzer.util

import android.content.Context
import android.widget.Toast

/**
 * Displays a toast message to the user.
 *
 * @param context The context of the activity.
 * @param message The message to display.
 */
fun displayToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}