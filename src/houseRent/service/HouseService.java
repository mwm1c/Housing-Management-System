package houseRent.service;

import houseRent.domain.House;

/**
 * 1. 定义House[]，保存house对象
 * 2. 响应HouseView的调用
 * 3. 完成对房屋信息的各种操作(CRUD)
 */
public class HouseService {
    private House[] houses;
    private int houseNumber = 0;
    private int idCounter = 0;

    public HouseService(int size) {
        houses = new House[size];
    }

    public House[] list() {
        return houses;
    }

    // 添加新对象，返回boolean值
    public boolean add(House newHouse) {
        if (houseNumber == houses.length) {
            System.out.println("数组已满，不能再添加了。");
            return false;
        }
        newHouse.setId(++idCounter);
        houses[houseNumber++] = newHouse;
        return true;
    }

    public House findById(int findId) {
        for (int i = 0; i < houseNumber; i++)
            if (findId == houses[i].getId())
                return houses[i];
        return null;
    }

    public boolean delete(int delId) {
        int index = -1;
        for (int i = 0; i < houseNumber; i++) {
            if (delId == houses[i].getId()) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return false;
        for (int i = index; i < houseNumber - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNumber] = null;
        return true;
    }
}
