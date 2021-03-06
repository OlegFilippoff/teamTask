package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStoreTest {

    @Test
    void shouldAddDuplicated() {

        GameStore store = new GameStore();

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = new Game("Нетология Баттл Онлайн", "Аркады", store);


        assertThrows(RuntimeException.class, () -> store.publishGame(game2.getTitle(), game2.getGenre()));

    }

    @Test
    void shouldAddPlayerTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        store.addPlayTime(player1.getName(), 5);

        String[] actual = store.getMostPlayer();
        String[] expected = {"Aaron"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnAllPlayedTimeSum() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        Player player2 = new Player("Bob");
        store.addPlayTime(player1.getName(), 5);
        store.addPlayTime(player2.getName(), 3);

        int actual = store.getSumPlayedTime();
        int expected = 8;

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddPlayerTimeOneHour() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        Player player2 = new Player("Bob");
        store.addPlayTime(player2.getName(), 1);

        String[] actual = store.getMostPlayer();
        String[] expected = {"Bob"};


        // если кол-во часов менее 1 часа(включительно), то работа метода ломается.
    }


    @Test
    void shouldAddPlayerTimeToTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        Player player2 = new Player("Bob");
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 2);
        store.addPlayTime(player2.getName(), 2);
        store.addPlayTime(player2.getName(), 1);

        int actual = store.getSumPlayedTime();
        int expected = 6;

        assertEquals(expected, actual);

        // сложение часов по одному и тому же игроку не выполняется
    }

    @Test
    void shouldFindTheMostPlayerTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        Player player2 = new Player("Bob");
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player2.getName(), 4);
        store.addPlayTime(player2.getName(), 1);

        String[] actual = store.getMostPlayer();
        String[] expected = {"Bob"};

        assertArrayEquals(expected, actual);

        // сложение часов по одному и тому же игроку не выполняется
    }

    @Test
    void returnNullMostPlayer() {
        GameStore store = new GameStore();

        String[] actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    void publishBlankGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("", "");


        assertTrue(store.containsGame(game));
    }

    @Test
    void shouldReturnFalseIfNoGame() {
        GameStore store = new GameStore();
        Game game1 = new Game(" ", " ", store);
        Game game0 = new Game("Zero", "Zero", store);

        assertFalse(store.containsGame(game1));
    }

    @Test
    void shouldCompareEqualPlayedTime() {
        GameStore store = new GameStore();
        Player player1 = new Player("Aaron");
        Player player2 = new Player("Bob");
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 2);
        store.addPlayTime(player2.getName(), 2);
        store.addPlayTime(player2.getName(), 1);

        String[] expected = {"Aaron", "Bob"};
        String[] actual = store.getMostPlayer();

        assertArrayEquals(expected, actual);

        // не предусмотрена ситуация, в которой время равно.
    }

    @Test
    public void shouldAddPlayTimeLessThenZero() {
        GameStore store = new GameStore();
        assertThrows(RuntimeException.class, () -> store.addPlayTime("Bob", -1));

    }   // Нет проверки при вводе отрицательных значений времени.

}
