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

  it should "return correct score for random game 1" in {
    game.roll(5)
    game.roll(2)
    game.roll(3)
    game.roll(5)
    game.roll(5)
    game.roll(0)
    game.roll(4)
    game.roll(1)
    game.roll(9)
    game.roll(1)
    game.roll(5)
    game.roll(1)
    game.roll(7)
    game.roll(1)
    game.roll(10)
    game.roll(5)
    game.roll(2)
    game.roll(10)
    game.roll(10)
    game.roll(2)
    assert(game.getScore() === 100)
  }

  it should "return correct score for random game 2" in {
    rollMany(18, 4)
    rollStrike()
    rollStrike()
    rollStrike()
    assert(game.getScore() === 102)
  }

  it should "return correct score for random game 3" in {
    rollMany(18, 4)
    rollSpare()
    game.roll(2)
    assert(game.getScore() === 84)
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