package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.BOB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.product.Product;

public class ClientRebuilderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientRebuilder(null));
    }

    @Test
    public void withName_validName_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Name newName = new Name("ALICIA");
        Client editedClient = cr.withName(newName).build();

        assertEquals(editedClient.getName(), newName);
        assertEquals(editedClient.getAddress(), ALICE.getAddress());
        assertEquals(editedClient.getEmail(), ALICE.getEmail());
        assertEquals(editedClient.getPhone(), ALICE.getPhone());
        assertEquals(editedClient.getProducts(), ALICE.getProducts());
    }

    @Test
    public void withAddress_validAddress_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Address newAddress = BOB.getAddress();
        Client editedClient = cr.withAddress(newAddress).build();

        assertEquals(editedClient.getName(), ALICE.getName());
        assertEquals(editedClient.getAddress(), newAddress);
        assertEquals(editedClient.getEmail(), ALICE.getEmail());
        assertEquals(editedClient.getPhone(), ALICE.getPhone());
        assertEquals(editedClient.getProducts(), ALICE.getProducts());
    }

    @Test
    public void withPhone_validPhone_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Phone newPhone = new Phone("81234567");
        Client editedClient = cr.withPhone(newPhone).build();

        assertEquals(editedClient.getName(), ALICE.getName());
        assertEquals(editedClient.getAddress(), ALICE.getAddress());
        assertEquals(editedClient.getEmail(), ALICE.getEmail());
        assertEquals(editedClient.getPhone(), newPhone);
        assertEquals(editedClient.getProducts(), ALICE.getProducts());
    }

    @Test
    public void withMultipleField_validEmailProductsMeetings_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Email newEmail = new Email("alice123@gmail.com");
        Set<Product> newProducts = BENSON.getProducts();
        List<Meeting> newMeetings = new ArrayList<>();
        Client editedClient = cr
                .withEmail(newEmail)
                .withProducts(newProducts)
                .withMeetings(newMeetings).build();

        assertEquals(editedClient.getName(), ALICE.getName());
        assertEquals(editedClient.getAddress(), ALICE.getAddress());
        assertEquals(editedClient.getEmail(), newEmail);
        assertEquals(editedClient.getPhone(), ALICE.getPhone());
        assertEquals(editedClient.getProducts(), newProducts);
        assertEquals(editedClient.getMeetings(), newMeetings);
    }

    @Test
    public void addMeeting_validMeeting_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Meeting newMeeting = new Meeting(
                ALICE, new Description("desc"), new MeetingDate(LocalDate.now()), new MeetingTime(LocalTime.now()));
        Client editedClient = cr
                .addMeeting(newMeeting).build();

        assertFalse(ALICE.getMeetings().contains(newMeeting));
        assertTrue(editedClient.getMeetings().contains(newMeeting));
        assertNotEquals(editedClient.getMeetings(), ALICE.getMeetings());
    }

    @Test
    public void addRemoveMeeting_validMeeting_success() {
        Meeting newMeeting = new Meeting(
                ALICE, new Description("desc"), new MeetingDate(LocalDate.now()), new MeetingTime(LocalTime.now()));
        ClientRebuilder cr = new ClientRebuilder(ALICE);

        Client editedClient = cr.addMeeting(newMeeting).build();
        assertTrue(editedClient.getMeetings().contains(newMeeting));

        editedClient = cr.removeMeeting(newMeeting).build();
        assertFalse(editedClient.getMeetings().contains(newMeeting));
    }

    @Test
    public void addProduct_validProduct_success() {
        ClientRebuilder cr = new ClientRebuilder(ALICE);
        Product newProduct = new Product("test product");
        Client editedClient = cr
                .addProduct(newProduct).build();

        assertFalse(ALICE.getProducts().contains(newProduct));
        assertTrue(editedClient.getProducts().contains(newProduct));
        assertNotEquals(editedClient.getProducts(), ALICE.getProducts());
    }

    @Test
    public void addRemoveProduct_validProduct_success() {
        Product newProduct = new Product("test product");
        ClientRebuilder cr = new ClientRebuilder(ALICE);

        Client editedClient = cr.addProduct(newProduct).build();
        assertTrue(editedClient.getProducts().contains(newProduct));

        editedClient = cr.removeProduct(newProduct).build();
        assertFalse(editedClient.getProducts().contains(newProduct));
    }
}
