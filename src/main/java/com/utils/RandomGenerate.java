package com.utils;

import java.util.Random;

/**
 * Created by GSN on 2017/4/1.
 * 随机生成
 */
public class RandomGenerate {

    private String ndc = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private String nc = "qwertyuiopasdfghjklzxcvbnm1234567890";
    private String n = "1234567890";
    private String nd = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private String customString = null;
    private final Random random = new Random();

    public RandomGenerate() {
    }

    public RandomGenerate(String customString) {
        this.customString = customString;
    }

    public String generateNdc(int number) {
        return this.generateNumberChar(this.ndc, number).toString();
    }

    public String generateNc(int number) {
        return this.generateNumberChar(this.nc, number).toString();
    }

    public String generateN(int number) {
        return this.generateNumberChar(this.n, number).toString();
    }

    public String generateNd(int number) {
        return this.generateNumberChar(this.nd, number).toString();
    }

    public String generateCustom(int number) {
        return this.generateNumberChar(this.customString, number).toString();
    }


    private StringBuilder generateNumberChar(String s, int number) {

        StringBuilder sb = new StringBuilder();
        int length = s.length();

        for (int i = 0; i < number; i++) {
            sb.append(s.charAt(random.nextInt(length)));
        }

        return sb;

    }

    /**
     * 随机数字0-9
     */
    public byte randomNumber() {
        return (byte) random.nextInt(10);
    }


}
