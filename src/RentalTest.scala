import org.junit._
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: tim-taylor
 * Date: 6/20/11
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */


class RentalTest {
  @Before def setUp: Unit = {
    customer = new Customer("Fred")
  }

  @Test def testSingleNewReleaseStatement: Unit = {
    customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3))
    assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement)
  }

  @Test def testDualNewReleaseStatement: Unit = {
    customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3))
    customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.NEW_RELEASE), 3))
    assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement)
  }

  @Test def testSingleChildrensStatement: Unit = {
    customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDRENS), 3))
    assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement)
  }

  @Test def testMultipleRegularStatement: Unit = {
    customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1))
    customer.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2))
    customer.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3))
    assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement)
  }

  private var customer: Customer = null

}