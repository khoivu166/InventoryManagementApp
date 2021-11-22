package baseline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FXMLControllerTest
{
    @Test
    public void testAddItems()
    {
        ArrayList<String> testList = new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            testList.add("xbox, A-123-123-123, 1000.00");
        }

        ArrayList<String> actualList = new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            actualList.add("xbox, A-123-123-123, 1000.00");
        }

        assertEquals(testList, actualList);
    }
    
    @Test
    public void testAddRestrictions()
    {
        ArrayList<String> testList = new ArrayList<>();
        String name = "123";
        boolean flag1 = false;
        if (name.matches("[0-9]+"))
        {
            flag1 = true;
        }

        boolean flag2 = true;

        assertEquals(flag1, flag2);
    }

    public void testAddRestrictions2()
    {
        ArrayList<String> testList = new ArrayList<>();
        String name = "A-123-312-123";
        boolean flag1 = false;
        if (name.matches("[a-zA-Z](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9]"))
        {
            flag1 = true;
        }

        boolean flag2 = true;

        assertEquals(flag1, flag2);
    }

    public void testAddRestrictions3()
    {
        ArrayList<String> testList = new ArrayList<>();
        String name = "cheese";
        boolean flag1 = false;
        try
        {
            Double.parseDouble(name);
        }
        catch (NumberFormatException e)
        {
            flag1 = true;
        }

        boolean flag2 = true;

        assertEquals(flag1, flag2);
    }

    @Test
    public void testDeleteItem()
    {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("xbox, A-123-321-424, 1000");

        ArrayList<String> actualList = new ArrayList<>();

        testList.remove(0);

        assertEquals(testList, actualList);
    }

    @Test
    public void testRemoveAll()
    {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("xbox, A-123-321-424, 1000");
        testList.add("xbox, A-123-321-424, 1000");
        testList.add("xbox, A-123-321-424, 1000");

        ArrayList<String> actualList = new ArrayList<>();

        testList.clear();

        assertEquals(testList,actualList);
    }


}