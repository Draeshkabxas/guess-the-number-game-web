package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;

    @Value("${game.guessCount:4}")
    @Getter
    private int guessCount;

    @Value("${game.minNumber:0}")
    private int minNumber;


    // == Bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber(){return maxNumber;}

    @Bean
    @GuessCount
    public int guessCount(){return guessCount;}

    @Bean
    @MinNumber
    public int minNumber(){return minNumber;}


}
