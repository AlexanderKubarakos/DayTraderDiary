package com.group50.projectsrc;

/**
 * The Event class represents an event in our game.
 * It contains information such as the effect, ID, image location, title, and description of the event.
 * @author Josh Spasic
 * @author Alexander Kubarakos
 * @author Duncan Finlayson
 */
public class Event {

    private EventEffect effect;
    private int ID;
    private String imageLocation;
    private String title;
    private String description;

    /**
     * Constructs a new Event object.
     * @param effect The effect of the event.
     * @param ID The ID of the event.
     * @param imageLocation The image location associated with the event.
     * @param title The title of the event.
     * @param description The description of the event.
     */
    public Event(EventEffect effect, int ID, String imageLocation, String title, String description) {
        this.effect = effect;
        this.ID = ID;
        this.imageLocation = imageLocation;
        this.title = title;
        this.description = description;
    }

    /**
     * Returns a string representation of the event.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "Event{" +
                "effect=" + effect +
                ", ID=" + ID +
                ", imageLocation='" + imageLocation + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Gets the effect of the event.
     * @return The effect of the event.
     */
    public EventEffect getEffect() {
        return effect;
    }

    /**
     * Gets the ID of the event.
     * @return The ID of the event.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the image location associated with the event.
     * @return The image location of the event.
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * Gets the title of the event.
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the event.
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * checks if this event is playable at given progression level
     * @param progressionLevel what is the progression level of the player
     * @return if the event is playable at their level
     */
    public boolean eventPlayableAtProgressionLevel(int progressionLevel) {
        if (effect.getStockGroupToAffect() == StockGroup.TECH)
            return true;
        if (effect.getStockGroupToAffect() == StockGroup.FOOD && progressionLevel > 1)
            return true;
        if (effect.getStockGroupToAffect() == StockGroup.BANKING && progressionLevel > 2)
            return true;
        if (effect.getStockGroupToAffect() == StockGroup.LUXURY && progressionLevel > 3)
            return true;
        return false;
    }
}