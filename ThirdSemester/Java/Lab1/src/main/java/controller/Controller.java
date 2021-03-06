package controller;

import model.ShowModel;
import model.data.Show;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.ConsoleView;
import utils.ErrorInterceptor;
import utils.FileManager;
import utils.errors.DbException;
import utils.errors.FileManagerException;
import utils.errors.WrongDateException;
import utils.errors.WrongStringException;

public class Controller {
    private final ShowModel model = new ShowModel();
    private final ConsoleView view = new ConsoleView();
    private final UserInput interceptor = new UserInput(view);
    private final Logger logger = LogManager.getLogger(Controller.class);

    public void run() {
        logger.info("App started");

        while (true) {
            view.printMenu();
            int choice = interceptor.inputInt(ConsoleView.INPUT_INT);
            switch (choice) {
                case 1:
                    view.printAll(model.getAll());
                    break;
                case 2:
                    try {
                        String actor = interceptor.inputString(ConsoleView.INPUT_ACTOR);

                        ErrorInterceptor.validString(actor);

                        Show[] resultByActor = model.getShowByActor(actor);
                        view.printAll(resultByActor);
                        if (interceptor.inputConfirmation(ConsoleView.ASK_SAVE)) {
                            FileManager.writeShows(
                                    interceptor.inputString(ConsoleView.INPUT_FILENAME),
                                    resultByActor
                            );
                        }
                    } catch (WrongStringException | FileManagerException e) {
                        view.printMessage(ConsoleView.FAILED_MESSAGE);

                        if (e instanceof FileManagerException) {
                            logger.error(e.getMessage());
                        } else {
                            logger.warn(e.getMessage());
                        }
                    }

                    break;
                case 3:
                    try {
                        String show = interceptor.inputString(ConsoleView.INPUT_SHOW);
                        String date = interceptor.inputString(ConsoleView.INPUT_DATE);

                        ErrorInterceptor.validString(show);
                        ErrorInterceptor.validDate(date);

                        String[] resultByShowAndDate = model.getTheaterByShowAndDate(show, date);
                        view.printStrings(resultByShowAndDate);
                        if (interceptor.inputConfirmation(ConsoleView.ASK_SAVE)) {
                            FileManager.writeTheaters(
                                    interceptor.inputString(ConsoleView.INPUT_FILENAME),
                                    resultByShowAndDate
                            );
                        }
                    } catch (WrongStringException | WrongDateException | FileManagerException e) {
                        view.printMessage(ConsoleView.FAILED_MESSAGE);

                        if (e instanceof FileManagerException) {
                            logger.error(e.getMessage());
                        } else {
                            logger.trace(e.getMessage());
                        }
                    }

                    break;
                case 4:
                    try {
                        model.saveData();
                    } catch (DbException e) {
                        view.printMessage(ConsoleView.FAILED_MESSAGE);
                        logger.error(e.getMessage());
                    }

                    logger.info("App quited");
                    System.exit(0);
                    break;
                default:
                    view.printMessage(ConsoleView.WRONG_INPUT);
            }
        }
    }
}
