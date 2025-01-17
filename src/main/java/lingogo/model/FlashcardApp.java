package lingogo.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import lingogo.model.flashcard.Flashcard;
import lingogo.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the flashcard app level.
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashcardApp implements ReadOnlyFlashcardApp {
    private final UniqueFlashcardList flashcards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
    }

    public FlashcardApp() {}

    /**
     * Creates a FlashcardApp using the flashcards in the {@code toBeCopied}.
     */
    public FlashcardApp(ReadOnlyFlashcardApp toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code FlashcardApp} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashcardApp newData) {
        requireNonNull(newData);
        setFlashcards(newData.getFlashcardList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashcard app.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the flashcard app.
     * The flashcard must not already exist in the flashcard app.
     */
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashcard app.
     * The flashcard identity of {@code editedFlashcard} must not be the same as another
     * existing flashcard in the flashcard app.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);
        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashcardApp}.
     * {@code key} must exist in the flashcard app.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " flashcards";
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FlashcardApp
                && flashcards.equals(((FlashcardApp) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
