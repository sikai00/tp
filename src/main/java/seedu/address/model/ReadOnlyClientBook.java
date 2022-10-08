package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Unmodifiable view of a client book
 */
public interface ReadOnlyClientBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain meetings with any timing conflicts.
     */
    ObservableList<Meeting> getMeetingList();

}
