package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class ConsoleNumberGuess  {
    // == fields ==

    private Game game;
    private MessageGenerator messageGenerator;

    // == CONSTRUCTORS ==

    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }



    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("Container ready for use.");

        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println(messageGenerator.getMainMessage());
            printMessageResult();

            int guess= scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()){
                printMessageResult();
                System.out.println("Play again y/n");

                String playAgainString=scanner.nextLine().trim();
                if (!playAgainString.equalsIgnoreCase("y")) break;

                game.reset();
            }


        }
    }

    private void printMessageResult(){
        System.out.println(messageGenerator.getResultMessage());
    }
}
