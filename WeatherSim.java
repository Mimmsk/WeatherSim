import java.util.AbstractMap;
import java.util.Map;
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
                Map.Entry<State, Event> result = getNextState(currentState, randomNumber);
                currentState = result.getKey();
                Event event = result.getValue();
                if (event != null) {
                    System.out.println(" Event " + (i + 1) + ": " + currentState + " (Event: " + event + ")");
                } else {
                  System.out.println(" Event " + (i + 1) + ": " + currentState + " (No Change)");
                }
            }
            System.out.println();
        }

    } 

    public static Map.Entry<State, Event> getNextState(State currentState, int randomNumber) {
        switch (currentState) {
            case CLEAR:
              if (randomNumber == 2) return new AbstractMap.SimpleEntry<> (State.CLOUDY, Event.HUMIDITY_INCREASING);
              break;
            case CLOUDY:
              if (randomNumber == 0) return new AbstractMap.SimpleEntry<> (State.CLEAR, Event.GETTING_WARMER);
              if (randomNumber == 2) return new AbstractMap.SimpleEntry<> (State.RAINING, Event.GETTING_COLDER);
              break;
            case RAINING:
              if (randomNumber == 0) return new AbstractMap.SimpleEntry<> (State.CLOUDY, Event.GETTING_WARMER);
              if (randomNumber == 2) return new AbstractMap.SimpleEntry<> (State.SEVERE_WEATHER, Event.WIND_INCREASING);
              break;
            case SEVERE_WEATHER:
              if (randomNumber == 0) return new AbstractMap.SimpleEntry<> (State.RAINING, Event.WIND_INCREASING);
              break;
        }
        return new AbstractMap.SimpleEntry<> (currentState, null);


        }
    }

