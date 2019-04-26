package namhenry.com.vn.projectweek4.utills


import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast




fun Context.showAlertDialog(message1: String) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setTitle("Error")
    builder.setMessage(message1)
    builder.setNegativeButton("OK") { dialog, _ ->
        dialog.dismiss()
    }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.show()

}

fun Context.showMessage( message:String = "OK"){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
fun openActivity(context: Context,activity: AppCompatActivity){
    val intent = Intent(context, activity::class.java)
    context.startActivity(intent)
}

