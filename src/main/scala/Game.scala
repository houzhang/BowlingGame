case class Game() {
  var rolls:Array[Int] = new Array[Int](20)
  var currentRoll = 0

  def roll(pins: Int):Unit = {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  def getScore(): Int = {
    var score = 0
    var frameIndex = 0
    for (frame <- 1 to 10) {
      if (isStrike(frameIndex)) {
        score += 10 + strikeBonus(frameIndex)
        frameIndex += 1
      }
      else if (isSpare(frameIndex)) {
        score += 10 + spareBonus(frameIndex)
        frameIndex += 2
      } else {
        score += sumOfBallsInFrame(frameIndex)
        frameIndex += 2
      }

    }
    score
  }

  def resetGame(): Unit = {
    currentRoll = 0
    rolls = new Array[Int](20)
  }

  private def isStrike(frameIndex: Int): Boolean = {
    rolls(frameIndex) == 10
  }

  private def isSpare(frameIndex: Int):Boolean = {
    rolls(frameIndex) + rolls(frameIndex+1) == 10
  }

  private def sumOfBallsInFrame(frameIndex: Int): Int = {
    rolls(frameIndex) + rolls(frameIndex+1)
  }

  private def spareBonus(frameIndex: Int): Int = {
    rolls(frameIndex+2)
  }

  private def strikeBonus(frameIndex: Int): Int = {
    rolls(frameIndex+1) + rolls(frameIndex+2)
  }

}
