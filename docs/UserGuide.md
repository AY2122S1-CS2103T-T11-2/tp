---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## About LingoGO!
LingoGO! is a **desktop app** for **university students who use English as their first language** and are trying to **learn a
new language**, optimized for use via a **Command Line Interface (CLI)** while still having the **benefits of a
Graphical User Interface (GUI)**. With digital flashcards, LingoGO! can make learning faster and more convenient compared
to using traditional flashcards.

LingoGO! currently supports **all languages that can be represented on your computer** and has the following main features:
* Addition, deletion, and editing of flashcards.
* Finding and filtering of flashcards by keywords and conditions.
* Importing and exporting of flashcards to be shared with others.
* Testing your knowledge in a questionnaire of flashcards.

Detailed information about these features can be found under the [Modes](#modes) and [Commands](#commands) sections below.

## Purpose of the user guide
This user guide aims to help users familiarise themselves with the commands of LingoGO! and use the application effectively.

## How to use the user guide
* A [Table of Contents](#) with clickable links can be found above to help with navigating across the user guide quickly.
* New users can refer to the [Quick Start](#quick-start) guide for a quick set-up tutorial.
* New users can also refer to [Modes](#modes) to start understanding how to use LingoGO!.
* A detailed outline of the commands can be found under [Commands](#commands).
* Experienced users can refer to the [Command Summary](#command-summary) for a quick overview of all the commands in LingoGo!.
* A [Glossary](#glossary) is provided to help explain certain important terms used in this guide.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have **Java 11 or above installed** in your Computer (you may download Java from [here](https://www.oracle.com/java/technologies/downloads/)).

1. Download the latest *lingogo.jar* from [here](https://github.com/AY2122S1-CS2103T-T11-2/tp/releases).

1. Copy the file to the folder you want to use as the home folder for LingoGO!.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the
   app contains some sample data.
   ![Ui](images/Ui-explanation.png)

   * Menu
     * A clickable menu bar.
   * Command box
     * Type a command into the command box and press Enter to execute it.
     * Some example commands you can try (refer to the [Commands](#commands) section below for a full list of commands and their details):
       * `list` : Lists all flashcards.
       * `add l/Chinese e/Good Morning f/早安` : Adds a flashcard with the `Chinese` language, English phrase `Good Morning`, and corresponding foreign phrase `早安`.
       * `delete 3` : Deletes the 3rd flashcard shown in the current displayed list.
       * `find e/Good Morning` : Finds flashcard(s) with the matching English phrase `Good Morning`.
       * `filter l/Chinese` : Shows only the flashcard(s) with the `Chinese` language in the current displayed list.
   * Command result
     * Shows a message after you execute a command.
   * Displayed flashcard list
     * Shows a list of your flashcards.

--------------------------------------------------------------------------------------------------------------------

## Modes

LingoGO! has 2 modes:
1. [List mode](#list-mode)
1. [Slideshow mode](#slideshow-mode)

### List mode

Below is an example of what LingoGO! looks like in list mode.

![Ui](images/Ui.png)

LingoGO! always starts in list mode and displays all of your flashcards.

List mode lets you [`add`](#adding-a-flashcard-add), [`delete`](#deleting-a-flashcard--delete),
[`edit`](#editing-a-flashcard--edit), [`import`](#importing-flashcards--import), and [`export`](#exporting-flashcards--export)
flashcards.

List mode also lets you choose what flashcards to display. The displayed flashcards
will be the flashcards you get tested on when you switch to [slideshow mode](#slideshow-mode).
You can use the [`list`](#listing-all-flashcards--list), [`filter`](#filtering-flashcards-by-conditions-filter), or
[`find`](#locating-flashcards-by-keywords-find) command to choose which flashcards to display.

### Slideshow mode

Below is an example of what LingoGO! looks like in slideshow mode.

![Slideshow](images/Slideshow.png)

Slideshow mode tests your knowledge by showing you flashcards one at a time. The flashcards shown to you are the ones
displayed in list mode.

In slideshow mode, you can:
* Move to [`next`](#moving-to-the-next-flashcard-in-slideshow-mode--next) or
  [`previous`](#moving-to-the-previous-flashcard-in-slideshow-mode--previous) flashcards
* Enter an [`answer`](#answering-a-flashcard--answer) for a flashcard

### Navigating between modes

LingoGO!'s default mode is list mode.
* To enter slideshow mode, use the [`slideshow`](#testing-with-a-set-of-flashcards--slideshow) command.
* To exit slideshow mode, use the [`stop`](#exiting-slideshow-mode-stop) command.


## Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are parameters to be supplied by you.
  * e.g. a usage of `add l/LANGUAGE e/ENGLISH_PHRASE f/FOREIGN_PHRASE` could be `add l/Chinese e/Good Morning f/早安`.

* Items in square brackets are optional.
  * e.g. `edit INDEX [l/LANGUAGE] [e/ENGLISH_PHRASE] [f/FOREIGN_PHRASE]` can be used as `edit 1 l/Chinese e/Good Morning f/早安` or `edit 1 e/Good Morning`.

* Parameters can be in any order.
  * e.g. if the command specifies `add l/LANGUAGE e/ENGLISH_PHRASE f/FOREIGN_PHRASE`, `add f/FOREIGN_PHRASE l/LANGUAGE e/ENGLISH_PHRASE` is also acceptable.

* If a parameter is expected only once, but you specified it multiple times, only the last occurrence will be taken.
  * e.g. `edit 2 e/Hi e/Hello` is the same as `edit 2 e/Hello`.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `clear`) will be ignored.
  * e.g. `help 123` is the same as `help`.

</div>


### Adding a flashcard: `add`

Adds a flashcard to LingoGO!.

* The flashcard will be added to the bottom of the displayed flashcard list in [list mode](#list-mode).

Format: `add l/LANGUAGE e/ENGLISH_PHRASE f/FOREIGN_PHRASE`

Examples:
* `add l/Chinese e/Good Morning f/早安`


### Answering a flashcard : `answer`

Checks whether the English phrase of a flashcard matches the phrase you provide.

Format: `answer e/ENGLISH_PHRASE`

* Checks the English phrase of the displayed flashcard against the `ENGLISH_PHRASE` you provide.
* The app will then show the correct English phrase and tell you whether you got it right.
* `ENGLISH_PHRASE` is not case-sensitive (e.g. "HeLLo" matches "hello").
* You can only answer a flashcard in [slideshow mode](#slideshow-mode), and you can only answer it once.

Examples:
* `answer e/hello` checks the English phrase of the flashcard on display to see if `hello` matches it.


### Clearing all flashcards : `clear`

Clears all flashcards from LingoGO!.

Format: `clear`


### Deleting a flashcard : `delete`

Deletes the specified flashcard from LingoGO!.

Format: `delete INDEX`

* Deletes the flashcard at the specified `INDEX`.
* `INDEX` refers to the index number of the flashcard shown in [list mode](#list-mode).
* `INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd flashcard in LingoGO!.
* `find Hello` followed by `delete 1` deletes the 1st flashcard from the results of the `find` command.


### Editing a flashcard : `edit`

Edits an existing flashcard in LingoGO!.

Format: `edit INDEX [l/LANGUAGE] [e/ENGLISH_PHRASE] [f/FOREIGN_PHRASE]`

* Edits the flashcard at the specified `INDEX`.
* `INDEX` refers to the index number of the flashcard shown in [list mode](#list-mode).
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* **At least one** of the optional fields must be provided.

Examples:
* `edit 1 l/German` Edits the language of the 1st flashcard to be `German`.
* `edit 1 e/Good Morning` Edits the English phrase of the 1st flashcard to be `Good Morning`.
* `edit 2 f/Guten Morgen` Edits the foreign phrase of the 2nd flashcard to be `Guten Morgen`.
* `edit 2 l/German e/Good Morning f/Guten Morgen` Edits the language, English phrase, and foreign phrase of the 2nd flashcard to be `German`, `Good Morning`, and `Guten Morgen` respectively.


### Exiting LingoGO! : `exit`

Exits the app.

Format: `exit`


### Exporting flashcards : `export`

Exports flashcards from LingoGO! to a CSV file.

Format: `export FILE_NAME`

* Provides a file name with a .csv extension in which the flashcards will be stored and exported.
* The exported file will be added to the *data* folder (located in the same folder as the *lingogo.jar* file).
* The CSV file will have **3 columns** in this order from left to right:
  1. Language
  1. Foreign phrase
  1. English phrase

Examples:
* `export myCards.csv` will save all cards in LingoGO! to a CSV file named `myCards.csv`.


### Filtering flashcards by condition(s): `filter`

Filters flashcards based on the specified condition(s).

Format: `filter [l/LANGUAGE] [i/INDEX_LIST]`

* `LANGUAGE` is not **case-sensitive** (e.g. "Chinese" matches "CHINESE").
* `INDEX_LIST` is a list of space separated indices, that refer to the indices shown in the current displayed list.
*  The indices **must be positive integers** 1, 2, 3, …

Examples:
* `filter l/Chinese` returns all flashcards with the `Chinese` language like `e/Good Morning f/早安`.
* `filter i/1 2 3` returns the flashcards in [list mode](#list-mode) indexed at 1, 2 and 3.
* `filter i/1 3 6 l/Tamil` returns all the flashcards in [list mode](#list-mode) indexed at 1, 3 and 6 with the `Tamil`
  language.


### Locating flashcards by keyword(s): `find`

Finds flashcards based on the keyword(s) specified.

Format: `find [e/ENGLISH_KEYWORDS] [f/FOREIGN_KEYWORDS]`

* The search is **not case-sensitive**, e.g `HELLO` will match `Hello`.
* The **order** of the keywords **does not matter**, e.g. `Good morning` will match `Morning good`.
* Only **full words** will be matched for **English keywords**, e.g. `Hello` will not match `Helloooo`.
* **Non-full words** match will be accepted for **foreign keywords**, e.g. `早` with match `早安`.
* Flashcard(s) **matching at least one keyword** will be displayed.
* **At least one** of the optional fields must be provided.

Examples:
* `find e/HELLO` returns `e/Hello f/你好`
* `find f/早` returns `e/Good Morning f/早安` and `e/Morning f/早晨`
* `find e/Hello f/早` returns `e/Hello f/你好`, `e/Good Morning f/早安` and `e/Morning f/早晨`
  ![find foreign keywords](images/findMixedKeywords.png)<br>


### Viewing help : `help`

Shows a message explaining how to access the help page, as well as dropdowns with brief explanations
for each command.

![help message](images/helpMessage.png)

Format: `help`


### Importing flashcards : `import`

Imports flashcards to LingoGO! using a CSV file.

Format: `import CSV_FILE_PATH`

* The file path can be absolute or relative to the location of the LingoGO! file.

<div markdown="block" class="alert alert-info">
**:information_source: Notes about `CSV_FILE_PATH`:**<br>
* Users with no knowledge about file paths can just place the CSV file in the same folder that LingoGO! is located in
  and fill the `CSV_FILE_PATH` parameter with the CSV file's name.
</div>

* The CSV file must have **3 columns** in this order from left to right:
  1. Language
  2. Foreign phrase
  3. English phrase

Below is an example of how the CSV file might look like.
     ![sample CSV file](images/SampleCSVFile.png)

Examples:
* `import dictionary.csv` will load all cards stored in the dictionary.csv to LingoGO!.


### Listing all flashcards : `list`

Shows a list of all flashcards in LingoGO!.

Format: `list`


### Moving to the next flashcard in slideshow mode : `next`

Goes forward to the next flashcard (if there is one) in [slideshow mode](#slideshow-mode).

Format: `next`


### Moving to the previous flashcard in slideshow mode : `previous`

Goes back to the previous flashcard (if there is one) in [slideshow mode](#slideshow-mode).

Format: `previous`


### Testing with a set of flashcards : `slideshow`

Switches to [slideshow mode](#slideshow-mode) for you to test yourself using the flashcards shown in [list mode](#list-mode).

Format: `slideshow`


### Exiting slideshow mode: `stop`

Exits [slideshow mode](#slideshow-mode) and returns to [list mode](#list-mode).

Format: `stop`


## Data

### Saving the data

LingoGO!'s data is saved in the hard disk automatically after any command that changes its data. There is no need to save manually.

### Editing the data file

LingoGO!'s data is saved as a JSON file at `{JAR file location}/data/lingogo.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LingoGO! will discard all data and start with an empty data file on the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## Glossary

### CSV
A CSV file, short for comma-separated values, is a special text file that uses commas for formatting.

### File paths
File paths specify the location of a file on the computer, telling the computer how to find a specific file.
An absolute file path tells the computer how to find the file from the root folder.
A relative file path tells the computer how to find a file from the current folder that it is in.
For more on file paths, you may want to visit [here](https://docs.oracle.com/javase/tutorial/essential/io/path.html).


## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LingoGO! home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Example
--------|----------|--------
**Add** | `add l/LANGUAGE e/ENGLISH_PHRASE f/FOREIGN_PHRASE` | `add l/Chinese e/Good Morning f/早安`
**Answer** | `answer e/ENGLISH_PHRASE` | `answer e/hello`
**Clear** | `clear` | `clear`
**Delete** | `delete INDEX` | `delete 3`
**Edit** | `edit INDEX [l/LANGUAGE] [e/ENGLISH_PHRASE] [f/FOREIGN_PHRASE]` | `edit 2 f/Guten Morgen`
**Exit** | `exit` | `exit`
**Export** | `export FILE_NAME` | `export myCards.csv`
**Filter** | `filter [l/LANGUAGE] [i/INDEX_LIST]` | `filter l/Chinese i/1 2 3`
**Find** | `find [e/ENGLISH_KEYWORDS] [f/FOREIGN_KEYWORDS]` | `find e/Hello f/早`
**Help** | `help` | `help`
**Import** | `import CSV_FILE_PATH` | `import ./dictionary.csv`
**List** | `list` | `list`
**Next** | `next` | `next`
**Previous** | `previous` | `previous`
**Slideshow** | `slideshow` | `slideshow`
**Stop** | `stop` | `stop`
