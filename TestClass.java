import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This file is used for testing your code. You can ignore the entire file during your development
 * You can click the button next to TestClass to test your code.
 *
 * This is also how we are going to grade your work! Of course, there will be more test cases
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass {
    private ByteArrayOutputStream out;
    private ByteArrayInputStream in;
    private BlockPuzzle blockPuzzle;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        blockPuzzle = new BlockPuzzle();
    }


    @Test
    @Order(1)
    void testReadValidInput_1() {
        assertTrue(blockPuzzle.validateInput("a5"));
        assertFalse(blockPuzzle.validateInput("A5"));
        assertFalse(blockPuzzle.validateInput("5a"));
        assertFalse(blockPuzzle.validateInput("a5 "));
    }

    @Test
    void testPrintMap() {
        char[][] map = new char[8][9];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 9; j++)
                map[i][j] = '.';
        blockPuzzle.printMap(map);
        String expected = 
        " 012345678\n" +
        "a.........\n" +
        "b.........\n" +
        "c.........\n" +
        "d.........\n" +
        "e.........\n" +
        "f.........\n" +
        "g.........\n" +
        "h.........";

        assertEquals(trimEnter(out.toString()), expected);
    }

    @Test
    void testPrintMap_2() {
        char[][] map = new char[8][9];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 9; j++)
                map[i][j] = '.';

        map[0][0] = 'A';
        map[1][1] = 'B';
        map[2][1] = 'B';
        blockPuzzle.printMap(map);

        String expected = 
        " 012345678\n" +
        "aA........\n" +
        "b.B.......\n" +
        "c.B.......\n" +
        "d.........\n" +
        "e.........\n" +
        "f.........\n" +
        "g.........\n" +
        "h.........";
        assertEquals(trimEnter(out.toString()), expected);
    }

    @Test
    void test_gameover1() {
        char[][] map = new char[8][9];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 9; j++)
                map[i][j] = i == j ? '.' : 'A';
        char[][][] puzzles = {{{'A','A'}}, {{'A','A'}}, {{'A','A'}}};
        assertTrue(blockPuzzle.gameOver(map, puzzles));
    }

    @Test
    void test_gameover2() {
        char[][] map = new char[8][9];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 9; j++)
                map[i][j] = '.';
        char[][][] puzzles = {{{'A','A'}}, {{'A','A'}}, {{'A','A'}}};
        assertFalse(blockPuzzle.gameOver(map, puzzles));
    }

    @Test
    void test_printPuzzle1() {
        char[][][] puzzles = {{{'A','A'}}, {{'A','A'}}, {{'A','A'}}};
        blockPuzzle.printPuzzles(puzzles);
        String expected =
                "Puzzle 0\n"+
                "AA\n"+
                "Puzzle 1\n"+
                "AA\n"+
                "Puzzle 2\n"+
                "AA\n";
        assertEquals(trimEnter(out.toString()), trimEnter(expected));

    }

    @Test
    void test_printPuzzle2() {
        char[][][] puzzles = {{{'A','A'}}, null, {{'A','A'}}};
        blockPuzzle.printPuzzles(puzzles);
        String expected =
                "Puzzle 0\n"+
                        "AA\n"+
                        "Puzzle 1\n"+
                        "used\n"+
                        "Puzzle 2\n"+
                        "AA\n";
        assertEquals(trimEnter(out.toString()), trimEnter(expected));

    }



    @Test
    void test_convertRC() {
        int[] arr = blockPuzzle.convertInputToRC("a5");
        assertArrayEquals(new int[]{0, 5}, arr);
    }

    String trimEnter(String s) {
        if (s==null) return s;
        s = s.replace("\r","");
        while (s.charAt(s.length() - 1) == '\n')
            s = s.substring(0, s.length()-1);

        return s;
    }
}
