package com.example.bitManipulation;

/**
 * 位运算
 *
 * 1 异或位运算（相同为0不同为1，无进位相加）
 * 1.1 0^N = 0, N^N = 0
 * 1.2 a^b = b^a, a^b^c = a^(b^c) （满足交换律和结合律）
 * 1.3 不使用第三个变量实现两个数交换，a = a^b, b = a^b, a = a^b，有 bug，若 a = b，a^b = 0
 * 1.4 一组数中有一个数出现奇数次，其他数都出现偶数次，怎么找到这一个数（所有数异或/Map）
 */
public class BitManipulation {

}
