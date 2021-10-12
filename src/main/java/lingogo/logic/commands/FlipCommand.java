package lingogo.logic.commands;

import static java.util.Objects.requireNonNull;
import static lingogo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.List;

import lingogo.commons.core.Messages;
import lingogo.commons.core.index.Index;
import lingogo.logic.commands.exceptions.CommandException;
import lingogo.model.Model;
import lingogo.model.flashcard.Flashcard;
import lingogo.model.flashcard.Phrase;

/**
 * Toggle between the English and Foreign phrase for the flashcard with the specified index
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "flip";
    public static final String FLIP_COMMAND_DESCRIPTION = "Toggles the flashcard to either show English or "
            + " foreign phrase";
    public static final String FLIP_COMMAND_USAGE = "flip INDEX";
    public static final String FLIP_COMMAND_EXAMPLES = "flip 3";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flips the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    /**
     * @param index of the flashcard to flip
     */
    public FlipCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToFlip = lastShownList.get(index.getZeroBased());
        Phrase phraseInEnglish = flashcardToFlip.getEnglishPhrase();
        Phrase phraseInForeign = flashcardToFlip.getForeignPhrase();

        if (flashcardToFlip.getFlipStatus()) {
            flashcardToFlip.setFlipStatus(false);
            model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
            return new CommandResult(phraseInForeign.value);
        } else {
            flashcardToFlip.setFlipStatus(true);
            model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
            return new CommandResult(phraseInEnglish.value);
        }
    }
}
