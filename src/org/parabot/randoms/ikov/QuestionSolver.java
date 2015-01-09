package org.parabot.randoms.ikov;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;

import java.util.ArrayList;

public class QuestionSolver implements Random {
    @Override
    public boolean activate() {
        return Game.getOpenBackDialogId() == 368;
    }

    @Override
    public void execute() {
        String message = Loader.getClient().getInterfaceCache()[372].getMessage();

        if (message.toLowerCase().contains("name")) {
            Menu.sendAction(679, -1, -1, 373);
            Time.sleep(1000);

            Keyboard.getInstance().sendKeys("Ikov");
        } else if(message.toLowerCase().contains("owner")){
            Menu.sendAction(679, -1, -1, 373);
            Time.sleep(1000);

            Keyboard.getInstance().sendKeys("David");
        } else {
            ArrayList<Character> operators = new ArrayList<>();
            operators.add('+');
            operators.add('-');
            operators.add('*');
            operators.add('/');

            String[] splitText = message.split("\\s+");

            char operator = '+';

            int firstOperand = 0;
            int secondOperand = 0;
            int answer = 0;

            for (int i = 0; i < splitText.length; i++) {
                if (operators.contains(splitText[i].charAt(0))) {
                    operator = splitText[i].charAt(0);

                    firstOperand = Integer.parseInt(splitText[i - 1]);
                    secondOperand = Integer.parseInt(splitText[i + 1].replace("?", ""));

                    break;
                }
            }

            switch (operator) {
                case '+':
                    answer = firstOperand + secondOperand;
                    break;
                case '-':
                    answer = firstOperand - secondOperand;
                    break;
                case '*':
                    answer = firstOperand * secondOperand;
                    break;
                case '/':
                    answer = firstOperand / secondOperand;
                    break;
            }

            Menu.sendAction(679, -1, -1, 373);
            Time.sleep(1000);

            Keyboard.getInstance().sendKeys(Integer.toString(answer));
        }

        Time.sleep(1000);
    }

    @Override
    public String getName() {
        return "Math solver";
    }

    @Override
    public String getServer() {
        return "Ikov";
    }
}
