package lingogo.logic.commands;

import static java.util.Objects.requireNonNull;
import static lingogo.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static lingogo.logic.parser.CliSyntax.PREFIX_FOREIGN_PHRASE;
import static lingogo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.List;
import java.util.Optional;

import lingogo.commons.core.Messages;
import lingogo.commons.core.index.Index;
import lingogo.commons.util.CollectionUtil;
import lingogo.logic.commands.exceptions.CommandException;
import lingogo.model.Model;
import lingogo.model.flashcard.Flashcard;
import lingogo.model.flashcard.Phrase;

/**
 * Edits the details of an existing flashcard in the flashcard app.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_DESCRIPTION = "Edits a flashcard";
    public static final String COMMAND_USAGE = "edit INDEX [e/ENGLISH_PHRASE] [f/FOREIGN_PHRASE]";
    public static final String COMMAND_EXAMPLES = "edit 1 e/Hi f/Hola\nedit 1 e/Hello\nedit 1 f/Guten Morgen";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ENGLISH_PHRASE + "ENGLISH_PHRASE] "
            + "[" + PREFIX_FOREIGN_PHRASE + "FOREIGN_PHRASE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ENGLISH_PHRASE + "Hello "
            + PREFIX_FOREIGN_PHRASE + "你好";

    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the flashcard app.";

    private final Index index;
    private final EditFlashcardDescriptor editFlashcardDescriptor;

    /**
     * @param index of the flashcard in the filtered flashcard list to edit
     * @param editFlashcardDescriptor details to edit the flashcard with
     */
    public EditCommand(Index index, EditFlashcardDescriptor editFlashcardDescriptor) {
        requireNonNull(index);
        requireNonNull(editFlashcardDescriptor);

        this.index = index;
        this.editFlashcardDescriptor = new EditFlashcardDescriptor(editFlashcardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToEdit = lastShownList.get(index.getZeroBased());
        Flashcard editedFlashcard = createEditedFlashcard(flashcardToEdit, editFlashcardDescriptor);

        if (!flashcardToEdit.isSameFlashcard(editedFlashcard) && model.hasFlashcard(editedFlashcard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.setFlashcard(flashcardToEdit, editedFlashcard);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard));
    }

    /**
     * Creates and returns a {@code Flashcard} with the details of {@code flashcardToEdit}
     * edited with {@code editFlashcardDescriptor}.
     */
    private static Flashcard createEditedFlashcard(Flashcard flashcardToEdit,
            EditFlashcardDescriptor editFlashcardDescriptor) {
        assert flashcardToEdit != null;

        Phrase updatedEnglishPhrase = editFlashcardDescriptor.getEnglishPhrase()
                .orElse(flashcardToEdit.getEnglishPhrase());
        Phrase updatedForeignPhrase = editFlashcardDescriptor.getForeignPhrase()
                .orElse(flashcardToEdit.getForeignPhrase());

        return new Flashcard(updatedEnglishPhrase, updatedForeignPhrase);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFlashcardDescriptor.equals(e.editFlashcardDescriptor);
    }

    /**
     * Stores the details to edit the flashcard with. Each non-empty field value will replace the
     * corresponding field value of the flashcard.
     */
    public static class EditFlashcardDescriptor {
        private Phrase englishPhrase;
        private Phrase foreignPhrase;

        public EditFlashcardDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditFlashcardDescriptor(EditFlashcardDescriptor toCopy) {
            setEnglishPhrase(toCopy.englishPhrase);
            setForeignPhrase(toCopy.foreignPhrase);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(englishPhrase, foreignPhrase);
        }

        public void setEnglishPhrase(Phrase englishPhrase) {
            this.englishPhrase = englishPhrase;
        }

        public Optional<Phrase> getEnglishPhrase() {
            return Optional.ofNullable(englishPhrase);
        }

        public void setForeignPhrase(Phrase foreignPhrase) {
            this.foreignPhrase = foreignPhrase;
        }

        public Optional<Phrase> getForeignPhrase() {
            return Optional.ofNullable(foreignPhrase);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFlashcardDescriptor)) {
                return false;
            }

            // state check
            EditFlashcardDescriptor e = (EditFlashcardDescriptor) other;

            return getEnglishPhrase().equals(e.getEnglishPhrase())
                    && getForeignPhrase().equals(e.getForeignPhrase());
        }
    }
}