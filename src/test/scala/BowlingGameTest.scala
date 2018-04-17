import org.scalatest._

class BowlingGameTest extends FlatSpec with BeforeAndAfter {
  val game = Game()

  before {
    game.resetGame()
  }

  it should "return correct score for gutter game" in {
    rollMany(20, 0)
    assert(game.getScore() === 0)
  }

  it should "return correct score for all ones" in {
    rollMany(20, 1)
    assert(game.getScore() === 20)
  }

  it should "return correct score for one spare" in {
    rollSpare()
    game.roll(3)
    rollMany(17, 0)
    assert(game.getScore() === 16)
  }

  it should "return correct score for one strike" in {
    rollStrike()
    game.roll(3)
    game.roll(4)
    rollMany(16, 0)
    assert(game.getScore() === 24)
  }

  it should "return correct score for perfect game" in {
    rollMany(12, 10)
    assert(game.getScore() === 300)
  }

  private def rollMany(n: Integer, pins: Integer):Unit = {
    for (i <- 1 to n) {
      game.roll(pins)
    }
  }

  private def rollSpare(): Unit = {
    game.roll(5)
    game.roll(5)
  }

  private def rollStrike(): Unit = {
    game.roll(10)
  }
}