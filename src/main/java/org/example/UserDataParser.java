package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataParser {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество Датарождения(dd.mm.yyyy) Номертелефона Пол");

            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                System.err.println("Введено неверное кол-во данных.");
                return;
            }

            String surName = data[0];
            String Name = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumberStr = data[4];
            String gender = data[5];

            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                System.err.println("Ошибка: неверный формат даты рождения.");
                return;
            }

            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(phoneNumberStr);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: номер телефона должен быть целым числом.");
                return;
            }

            if (!gender.matches("[fm]")) {
                System.err.println("Ошибка: неверный формат пола (допустимы только 'f' или 'm').");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(surName + ".txt", true))) {
                String userData = surName + " " + Name + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;
                writer.write(userData);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Данные успешно записаны в файл: " + surName + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
