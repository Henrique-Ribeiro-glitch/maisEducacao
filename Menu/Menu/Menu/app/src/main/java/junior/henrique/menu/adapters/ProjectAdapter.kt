package henrique.junior.produto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import henrique.junior.produto.modelo.Project
import junior.henrique.menu.R
import kotlinx.android.synthetic.main.lines_projects.view.*

class ProjectAdapter(private val projects: List<Project>, private val context: Context,
                     private val listener: (Project) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.lines_projects, parent,
            false))
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val project = projects[position]

        if(holder is ViewHolder) {
            holder?.let {
                it.bind(project, position)
            }
        }

    }

    public fun updateList() {
        this.notifyDataSetChanged()
    }

}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(project: Project?, position: Int) {

        val textViewTitle = itemView.textViewTitle
        val textViewDescription = itemView.textViewDescription
        val textViewStartDate = itemView.textViewStartDate
        val textViewExpectedEndDate = itemView.textViewExpectedEndDate
        val textViewAmountPeople = itemView.textViewAmountPeople

        project?.let {
            textViewTitle.text = it.title
            textViewDescription.text = it.description
            textViewStartDate.text = it.startDate
            textViewExpectedEndDate.text = it.expectedEndDate
            textViewAmountPeople.text = it.amountPeople
        }

    }
}