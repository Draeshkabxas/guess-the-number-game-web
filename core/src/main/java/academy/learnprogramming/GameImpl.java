package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@Getter
public class GameImpl implements Game {


    // == fields ==

    @val
    @Getter(AccessLevel.NONE)
     NumberGenerator numberGenerator;

    @val
    int guessCount;

    private int number;

    @Setter
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // == Constructors ==

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        guess = 0;
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        remainingGuesses = guessCount;
        number = numberGenerator.next();


        log.debug("The number is {}", number);

    }

    @PreDestroy
    public void preDestroy() {
        log.info("In Game preDestroy()");
    }

    //== public methods ==



    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }

            if (guess < number) {
                smallest = guess + 1;
            }


        }

        remainingGuesses--;

    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }


    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    //== private methods ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }

    @Override
    public String toString() {
        return "GameImpl{" +
                "numberGenerator=" + numberGenerator +
                ", guessCount=" + guessCount +
                ", number=" + number +
                ", guess=" + guess +
                ", smallest=" + smallest +
                ", biggest=" + biggest +
                ", remainingGuess=" + remainingGuesses +
                ", validNumberRange=" + validNumberRange +
                '}';
    }
}
