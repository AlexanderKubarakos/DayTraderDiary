package com.group50.projectsrc;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The EventManager class manages the events in the game.
 * @author Josh Spasic
 * @author Duncan Finlayson
 * @author Alexander Kubarakos
 */
public class EventManager {
    private ArrayList<Event> eventList = new ArrayList<>();
    private static EventManager instance;

    /**
     * Retrieves the instance of the EventManager (Singleton pattern).
     * @return The EventManager instance.
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private EventManager() {
        setup("events.txt");
    }

    /**
     * Sets up the event manager by reading event data from a file.
     * @param inputFile The input file containing event data.
     */
    private void setup(String inputFile) {
        File file = new File(inputFile);
        try {
            Scanner s = new Scanner(new FileReader(file));
            for (int i = 0; i < 11; i++) {
                s.nextLine(); // Skip first 11 lines (assuming they are headers or metadata)
            }
            while (s.hasNextLine()) {
                String description = "";
                boolean groupEffect = Boolean.parseBoolean(s.nextLine());
                String stockNameToAffect = s.nextLine();
                StockGroup stockGroupToAffect = StockGroup.valueOf(s.nextLine());
                float deltaExpectedReturn = Float.parseFloat(s.nextLine());
                float deltaStandardDeviation = Float.parseFloat(s.nextLine());
                float deltaPrice = Float.parseFloat(s.nextLine());
                EventEffect effect = new EventEffect(groupEffect, stockNameToAffect, stockGroupToAffect,
                        deltaExpectedReturn, deltaStandardDeviation, deltaPrice);
                int ID = Integer.parseInt(s.nextLine());
                String imageLocation = s.nextLine();
                String title = s.nextLine();
                String currLine = "";
                while (s.hasNextLine() ? !(currLine = s.nextLine()).isEmpty() : false) {
                    description += currLine + " ";
                }
                Event event = new Event(effect, ID, imageLocation, title, description);
                eventList.add(event);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("Unable to read event file.");
        }
    }

    /**
     * Prints the list of events (for debugging purposes).
     */
    public void printArrayList() {
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println(eventList.get(i));
        }
    }

    /**
     * Searches for an event by its ID.
     * @param id The ID of the event to search for.
     * @return The event with the specified ID, or null if not found.
     */
    public Event searchByID(int id) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getID() == id) {
                return eventList.get(i);
            }
        }
        return null;
    }

    /**
     * Retrieves a random event ID.
     * @return A random event ID.
     */
    public int getRandomEventID() {
        Random r = new Random();
        return r.nextInt(eventList.size());
    }

    /**
     * Gets all the events names
     * @return array list of all names
     */
    ArrayList<String> getAllEventNames() {
        ArrayList<String> r = new ArrayList<>();
        for (var event : eventList) {
            r.add(event.getTitle());
        }
        return r;
    }

    /**
     * Searches for event with given name
     * @param title name of event to search for
     * @return event with name or null if none found
     */
    public Event searchByTitle(String title) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getTitle() == title) {
                return eventList.get(i);
            }
        }
        return null;
    }
}