package lingogo.logic.commands;

import static java.util.Objects.requireNonNull;

import lingogo.model.FlashcardApp;
import lingogo.model.Model;

/**
 * Clears the flashcard app.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = "Clears all flashcards";
    public static final String COMMAND_USAGE = "clear";
    public static final String COMMAND_EXAMPLES = "clear";
    public static final String MESSAGE_SUCCESS = "All your flashcards have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFlashcardApp(new FlashcardApp());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}