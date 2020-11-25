package 库函数实验

/**
 * The difference between associateBy and groupBy is how they process objects with the same key:
 * associateBy uses the last suitable element as the value.
 * groupBy builds a list of all suitable elements and puts it in the value.
 */
fun main() {

    data class Person(val name: String, val city: String, val phone: String) // 1

    val people = listOf(                                                     // 2
        Person("John", "Boston", "+1-888-123456"),
        Person("John1", "Boston", "+1-888-123457"),
        Person("Sarah", "Munich", "+49-777-789123"),
        Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
    )

    val phoneBook = people.associateBy { it.phone }                          // 3
    val cityBook = people.associateBy(Person::city, Person::name)           // 4
    val peopleCities = people.groupBy(Person::city, Person::name)            // 5


    println("People: $people")
    println("Phone book: $phoneBook")
    println("City book: $cityBook")
    println("People living in each city: $peopleCities")
}