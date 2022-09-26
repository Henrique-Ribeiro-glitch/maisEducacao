package junior.henrique.menu.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import henrique.junior.produto.modelo.Project
import junior.henrique.menu.R
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class  ProjectScreen : AppCompatActivity() {

    val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        var objectIntent: Intent = intent

        buttonRegister2.setOnClickListener {
            if (editTextTitle.text.toString().isNotEmpty() &&
                editTextDescription.text.toString().isNotEmpty() &&
                editTextStartDate.text.toString().isNotEmpty() &&
                editTextExpectedEndDate.text.toString().isNotEmpty() &&
                editTextAmountPeople.text.toString().isNotEmpty()) {
                    var project = Project()
                    project.title = editTextTitle.text.toString()
                    project.description = editTextDescription.text.toString()
                    project.startDate = editTextStartDate.text.toString()
                    project.expectedEndDate = editTextExpectedEndDate.text.toString()
                    project.amountPeople = editTextAmountPeople.text.toString()

                    database.collection("projects")
                        .add(project)
                        .addOnSuccessListener { documentReference ->
                            toast(getString(R.string.projectAdded))
                        }
                        .addOnFailureListener{ e ->
                            toast(getString(R.string.RegisterError)) }
                } else {
                editTextTitle.setError(getString(R.string.edittext_title_seterror))
                editTextDescription.setError(getString(R.string.edittext_description_seterror))
                editTextStartDate.setError(getString(R.string.edittext_start_seterror))
                editTextExpectedEndDate.setError(getString(R.string.edittext_start_seterror))
                editTextAmountPeople.setError(getString(R.string.edittext_people_seterror))
            }

        }

        buttonRegisterListScreen.setOnClickListener {
            startActivity<ListScreen>()
        }

    }

}