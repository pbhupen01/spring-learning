package practice.spring.springmvc.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practice.spring.springmvc.commands.CategoryCommand;
import practice.spring.springmvc.model.Category;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";

    CategoryCommandToCategory conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        Assert.assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        Assert.assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        Assert.assertEquals(ID_VALUE, category.getId());
        Assert.assertEquals(DESCRIPTION, category.getDescription());
    }

}
