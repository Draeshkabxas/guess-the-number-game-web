package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    public static final String MAIN_MESSAGE = "game.main.message";
    public static final String WON="game.won";
    public static final String LOST="game.lost";
    public static final String INVALID_NUMBER_RANGE = "game.invalid.number.range";
    public static final String LOWER="game.lower";
    public static final String HIGHER="game.higher";
    public static final String FIRST_GUESS="game.first.guess";
    public static final String REMAINING ="game.remaining";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructors ==

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }


    // == init ==
    @PostConstruct
    public void init() {
        log.info("Post: game = {} guess count = {}", game, game.getGuessCount());
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE
                ,game.getSmallest()
                ,game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return getMessage(WON,game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOST,game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_NUMBER_RANGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);;

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }

            return getMessage(REMAINING,direction,game.getRemainingGuesses());
        }

    }


    // == private methods ==
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code
                , args, LocaleContextHolder.getLocale());
    }
}
