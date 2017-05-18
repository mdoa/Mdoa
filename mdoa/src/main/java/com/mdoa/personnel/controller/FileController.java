package com.mdoa.personnel.controller;

import java.util.Random;
public class FileController {
public static void main(String[] args) {

	Random random = new Random();
	int num=0;
	for(int i = 0; i<=3; i++){
		num = num*10 + random.nextInt(9);
		}
	System.out.println(num);
	}

}