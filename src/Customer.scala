import java.util.Vector
import java.util.Enumeration

class Customer(val name: String) {

  private var rentals =  List.empty[Rental]

  def addRental(rental: Rental): Unit = {
    rentals = rental :: rentals
  }

  def statement: String = {
    var totalAmount = 0.0
    var frequentRenterPoints = 0
    val iter = rentals.reverse.iterator
    var result = "Rental Record for " + name + "\n"
    while (iter.hasNext) {
      var thisAmount = 0.0
      val each = iter.next
      each.movie.priceCode match {
        case Movie.REGULAR =>
          thisAmount += 2
          if (each.daysRented > 2) thisAmount += (each.daysRented - 2) * 1.5

        case Movie.NEW_RELEASE =>
          thisAmount += each.daysRented * 3

        case Movie.CHILDRENS =>
          thisAmount += 1.5
          if (each.daysRented > 3) thisAmount += (each.daysRented - 3) * 1.5

      }

        frequentRenterPoints += 1

      if (each.movie.priceCode == Movie.NEW_RELEASE && each.daysRented > 1) {
        frequentRenterPoints += 1
      }
      result += "\t" + each.movie.title + "\t" + String.valueOf(thisAmount) + "\n"
      totalAmount += thisAmount
    }
    result += "You owed " + String.valueOf(totalAmount) + "\n"
    result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n"
    return result
  }


}

