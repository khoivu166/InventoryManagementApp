package baseline;

import org.junit.jupiter.api.Test;

import java.awt.font.NumericShaper;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EditItemTest
{
    @Test
    public void TestEdit()
    {
        //simulate a new Description editted by user
        String editedValue = "xbox, A-123-123-123, 1000.00";
        //the original list
        ArrayList<String> testList = new ArrayList<>();
        testList.add("PlayStation,  B-133-123-123, 1000.00");

        //the list after the edit
        ArrayList<String> editList = new ArrayList<>();
        editList.add("xbox, A-123-123-123, 1000.00");

        //edit the description of the original list
        testList.set(0, editedValue);

        assertEquals(testList, editList);
    }

    @Test
    public void testEditRestrictions1()
    {
        ArrayList<String> testList = new ArrayList<>();
        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("A-123-312-123");
        String name = "A-123-312-123";
        boolean flag1 = false;
        if (name.matches("[a-zA-Z](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9]"))
        {
            testList.add(name);
        }

        boolean flag2 = true;

        assertEquals(testList, actualList);
    }

    @Test
    public void testEditRestrictions2()
    {
        ArrayList<String> testList = new ArrayList<>();
        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("A-123-312-123");
        String name = "A-123-312-123";
        boolean flag1 = false;
        if (actualList.contains(name))
        {
            flag1 = true;
        }

        boolean flag2 = true;

        assertEquals(flag1, flag2);
    }

    @Test
    public void testEditRestrictions3()
    {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("xbox");
        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("xbox");
        String name = "playstation";
        boolean flag1 = false;
        if (name.matches("[0-9]+"))
        {
            testList.add(name);
        }

        boolean flag2 = true;

        assertEquals(testList, actualList);
    }

    @Test
    public void testEditRestrictions4()
    {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("xbox");
        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("xbox");
        String name = "playstation";
        boolean flag1 = false;
        try
        {
            Double.parseDouble(name);
        }
        catch(NumberFormatException e)
        {
            flag1 = true;
        }


        boolean flag2 = true;

        assertEquals(flag1, flag2);
    }

}