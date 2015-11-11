package sun.yamorn.blog.admin.spel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by yamorn on 2015/11/11.
 */
@RunWith(JUnit4.class)
public class HelloTest {
    private ExpressionParser parser;

    @Before
    public void init() {
        parser = new SpelExpressionParser();
    }

    @Test
    public void testHello() {
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        assertEquals("Hello World", message);
    }

    @Test
    public void testConcat() {
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        assertEquals(message, "Hello World!");
    }

    @Test
    public void testCallingBeanProperty() {
        // invokes 'getBytes()'
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        assertEquals(bytes.length, 11);
    }

    @Test
    public void testDotNotation() {
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp.getValue();
        assertEquals(length, 11);
    }

    @Test
    public void testConstructor() {
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        assertEquals(message, "HELLO WORLD");
    }

    @Test
    public void testProperty() {
        Person person = new Person("Tom", 11);
        Expression exp = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(person);
        String name = (String) exp.getValue(context);
        assertEquals(name, "Tom");

    }

    @Test
    public void testBoolean() {
        Person person = new Person("Tom", 11);
        Expression exp = parser.parseExpression("name=='Tom'");
        EvaluationContext context = new StandardEvaluationContext(person);
        boolean result = exp.getValue(context, Boolean.class);
        assertTrue(result);
    }

    @Test
    public void testTypeConvert() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        StandardEvaluationContext context = new StandardEvaluationContext(simple);

        // false is passed in here as a string. SpEL and the conversion service will
        // correctly recognize that it needs to be a Boolean and convert it.
        parser.parseExpression("booleanList[0]").setValue(context, "false");
        Boolean result = simple.booleanList.get(0);
        assertFalse(result);
    }

    @Test
    public void testParseConfig() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);

        ExpressionParser parser = new SpelExpressionParser(config);

        Expression exp = parser.parseExpression("list[3]");

        Demo demo = new Demo();
        Object o = exp.getValue(demo);

        // demo.list will now be a real collection of 4 entries
        // Each entry is a new empty String
        assertTrue(("".equals(o)));
    }

    // Expression Template
    @Test
    public void testExpTemplate() {
        String randomPhrase = parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        assertNotNull(randomPhrase);
        System.out.println(randomPhrase);
    }

    @Test
    public void testExpTemplateProperty() {
        Person person = new Person("Tom", 11);
        Expression exp = parser.parseExpression("#{name}, #{age}", new TemplateParserContext());
        String name = (String) exp.getValue(person);
        assertEquals(name, "Tom, 11");
    }

    @Test
    public void testExpObject(){
        Person person = new Person("Tom", 11);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("person", person);

        Expression exp = parser.parseExpression("#person.name");
        String name = (exp.getValue(context,String.class));
        assertEquals(name, "Tom");
    }

    @Test
    public void testExpArray(){
        String[] array = new String[]{"hello", "world"};
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("array", array);
        Expression exp = parser.parseExpression("#array[0]");
        String result = exp.getValue(context, String.class);
        assertEquals(result, "hello");
    }

    @Test
    public void testExpMap(){
        Map<String, String> map = new HashMap<>();
        map.put("a", "hello");
        map.put("b", "world");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("map", map);
        Expression exp = parser.parseExpression("#map['a']");
        String result = exp.getValue(context,String.class);
        assertEquals(result, "hello");
    }

    @Test
    public void testExpList(){
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("list", list);
        Expression exp = parser.parseExpression("#list[0]");
        int result = exp.getValue(context, Integer.class);
        assertEquals(result, 0);
    }

    @Test
    public void testStdSpel() {
        Person person = new Person("Tom", 11);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", person.getName());
        Expression exp = parser.parseExpression("#name");
        String name = exp.getValue(context, person, String.class);
        assertEquals(name, "Tom");
    }


    private class Demo {
        public List<String> list;
    }

    private class Simple {
        public List<Boolean> booleanList = new ArrayList<>();
    }


    private class Person {
        String name;
        int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
