import java.util.Random;

public class WeatherSim {

    enum State {
        CLEAR, CLOUDY, RAINING, SEVERE_WEATHER
    }
    enum Event {
        GETTING_WARMER, GETTING_COLDER, HUMIDITY_INCREASING, WIND_INCREASING
    }
    public static void main(String[] arg) {
        State currentState = State.CLEAR;
        Random random = new Random();

        System.out.println("7 Day Weather Simulation:\n");

        for (int day = 1; day <= 7; day++) {
            System.out.print("Day " + day + ":");

            for (int i = 0; i < 5; i++) {
                int randomNumber = random.nextInt(3);
                currentState = getNextState(currentState, randomNumber);
                System.out.println(" Event " + (i + 1) + ": " + currentState);
            }
            System.out.println();
        }

    } 

    public static State getNextState(State currentState, int random) {
        switch (currentState) {
            case CLEAR:
              if (random == 2) return State.CLOUDY;
              break;
            case CLOUDY:
              if (random == 0) return State.CLEAR;
              if (random == 2) return State.RAINING;
              break;
            case RAINING:
              if (random == 0) return State.CLOUDY;
              if (random == 2) return State.SEVERE_WEATHER;
              break;
            case SEVERE_WEATHER:
              if (random == 0) return State.RAINING;
              break;
        }
        return currentState;


        }
    }

