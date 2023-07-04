import java.io.IOException;
import java.util.Scanner;

public class Game {
    private DataSet dataSet = new DataSet();
    private int score = 0;
    private City currentCity = null;

    public Game () {
        dataSet.add(new City("Kyiv"));
        dataSet.add(new City("Dnipro"));
        dataSet.add(new City("Lviv"));
        dataSet.add(new City("Vinnitsya"));
        dataSet.add(new City("Antverpen"));
        dataSet.add(new City("Nikolaev"));
    }

    public boolean turn(String value) throws IOException {
        if (dataSet.contains(value)) {
            if(!dataSet.isUsed(value)) {
                if(currentCity == null) {
                    dataSet.use(value);
                    currentCity = new City(value);
                    return true;
                } else {
                    if(currentCity.lastChar() == value.toLowerCase().charAt(0)) {
                        dataSet.use(value);
                        currentCity = new City(value);
                        return true;
                    } else {
                        throw new IOException("Wrong turn");
                    }
                }
            } else {
                throw new IOException("This city has been already used");
            }
        } else if (value.equals("Здаюсь")){
            return false;
        } else {
            throw new IOException("This city is not known");
        }
    }

    public boolean turnAI() {
        char lastChar = currentCity.lastChar();
        currentCity = dataSet.findAITurn(lastChar);
        return (currentCity != null);
    }

    private String getValue() {
        Scanner value = new Scanner(System.in);
        return value.nextLine();
    }

    public int play() {
        try {
            while (true) {
                if (turn(getValue())) {
                    this.score++;
                    if(turnAI()) {
                        System.out.println(currentCity);
                    } else {
                        return score;
                    }
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Невідома помилка");;
    }
}
