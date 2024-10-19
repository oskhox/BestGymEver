package bestgymever;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInteractionTest
{
    UserInteraction ui = new UserInteraction();

    //testar att hjälpmetoden returnerar false om tom
    @Test
    final void checkYesNoInputEmptyString() {
        assertFalse(ui.checkYesNoInput(" "));
    }

    //testar att hjälpmetoden returnerar false om irrelevant input
    @Test
    final void checkIrrelevantInput() {
        assertFalse(ui.checkYesNoInput("foo"));
    }

    //testar att hjälpmetoden returnerar true om "ja"
    @Test
    final void checkYesNoInputYes() {
        assertTrue(ui.checkYesNoInput("ja"));
    }

    //testar att hjälpmetoden returnerar true om "nej"
    @Test
    final void checkYesNoInputNo() {
        assertTrue(ui.checkYesNoInput("nej"));
    }
}