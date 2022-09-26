package junior.henrique.menu.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import henrique.junior.produto.adapters.ProjectAdapter
import henrique.junior.produto.modelo.Project
import junior.henrique.menu.R
import kotlinx.android.synthetic.main.activity_screen_projects.*
import org.jetbrains.anko.toast

class ListScreen : AppCompatActivity() {

    val database = FirebaseFirestore.getInstance()
    private lateinit var adapter: ProjectAdapter
    private val projects = arrayListOf<Project>()
    private val MENU_EDITAR: Int = 105

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        menu.add(MENU_EDITAR, MENU_EDITAR, MENU_EDITAR,"Edit")
            .setIcon(R.drawable.ic_action_editar)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_cadastrar -> { toast("Registered") }
            R.id.action_remover -> { toast("Removed") }
            MENU_EDITAR -> { toast("Menu") }
            else -> { toast("Menu invalid")}
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_projects)

        initialize()

        database.collection("projects")
            .get()
            .addOnSuccessListener { result ->

                if(result.isEmpty) {
                    toast("Empty!")
                    return@addOnSuccessListener
                }

                for(document in result) {
                    projects.add(
                        document.toObject(
                            Project::class.java))
                }

                adapter.updateList()
            }

            .addOnFailureListener{ e ->
                toast("Erro ao consultar produtos!")
            }
    }

    private fun initialize() {
        adapter = ProjectAdapter(projects, this@ListScreen,
            { project: Project -> itemClicked(project)})

        ListScreenRecyclerViewProjects.adapter = adapter
        ListScreenRecyclerViewProjects.layoutManager =
            LinearLayoutManager(this)
    }

    private fun itemClicked(project: Project) {
        toast("You clicked on: ${project.toString()}")
    }

}
