package org.parabot.randoms.ikov;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;

public class MathSolver implements Random {
    @Override
    public boolean activate() {
        return Game.getOpenBackDialogId() == 368;
    }

    @Override
    public void execute() {
        String message = Loader.getClient().getInterfaceCache()[372].getMessage();

        if (message.contains("name")) {
            Menu.sendAction(679, -1, -1, 373);
            Time.sleep(1000);

            Keyboard.getInstance().sendKeys("Ikov");
        } else {
            String[] splitText = message.split("\\s+");

            int firstOperand = Integer.parseInt(splitText[4]);
            int secondOperand = Integer.parseInt(splitText[6].replace("?", ""));
            int answer = 0;

            char operator = splitText[5].charAt(0);

            switch(operator) {
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
