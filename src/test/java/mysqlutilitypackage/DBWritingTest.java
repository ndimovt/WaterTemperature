package mysqlutilitypackage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DBWritingTest {

    @Test
    @DisplayName("Testing date functionality")
    public void date(){
        String realDate = "2023-07-30";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String testDate = localDate.format(format);
        assertEquals(testDate,realDate);
    }

}