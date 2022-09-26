package henrique.junior.produto.modelo

class Project {

    var title: String? = null
    var description: String? = null
    var startDate: String? = null
    var expectedEndDate: String? = null
    var amountPeople: String? = null

    override fun toString(): String {
        return "Title: $title Description: $description Start date: $startDate Expected end date: $expectedEndDate Amount people $amountPeople"
    }
}