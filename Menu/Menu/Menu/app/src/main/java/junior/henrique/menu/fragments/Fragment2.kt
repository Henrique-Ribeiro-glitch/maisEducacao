package junior.henrique.menu.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import henrique.junior.produto.modelo.Project
import junior.henrique.menu.R
import junior.henrique.menu.activitys.ListScreen
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    val database = FirebaseFirestore.getInstance()
    private lateinit var viewFragment: View
    private lateinit var buttonFragment1Resgister: Button
    private lateinit var buttonFragment1List: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater!!.inflate(R.layout.fragment_register, container, false)

        buttonFragment1Resgister = viewFragment.findViewById(R.id.buttonRegister2)

        buttonFragment1Resgister.setOnClickListener {
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

        buttonFragment1Resgister = viewFragment.findViewById(R.id.buttonRegister2)

        buttonFragment1List.setOnClickListener {
            startActivity<ListScreen>()
        }

    }
}
