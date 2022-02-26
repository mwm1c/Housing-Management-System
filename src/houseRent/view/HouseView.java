package houseRent.view;

import houseRent.domain.House;
import houseRent.service.HouseService;
import houseRent.utils.Utility;

/**
 * 1.显示界面
 * 2.接受用户的输入
 * 3.调用HouseService完成对房屋的各种操作
 */

public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private HouseService housesService = new HouseService(10);

    // 接收输入，创建house对象，调用HouseService中的add()
    public void addHouse() {
        System.out.println("====================添加房屋=====================");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
        // 创建一个新的house对象，注意id是系统分配的，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if (housesService.add(newHouse)) {
            System.out.println("添加房屋成功了");
        } else {
            System.out.println("添加房屋失败了");
        }
    }

    public void findHouse() {
        System.out.println("===================查找房屋信息====================");
        System.out.println("请输入你要查找的id：");
        int findId = Utility.readInt();
        House house = housesService.findById(findId);
        if (house == null)
            System.out.println("==================该房屋信息不存在===================");
        else
            System.out.println(house);
    }

    // 接收输入的id，调用HouseService中的delete()
    public void deleteHouse() {
        System.out.println("===================查找房屋信息====================");
        System.out.println("请输入待删除房屋的编号（-1退出）");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("==================放弃删除房屋信息===================");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (housesService.delete(delId))
                System.out.println("==================删除房屋信息成功===================");
            else
                System.out.println("==================删除房屋信息失败===================");
        } else {
            System.out.println("==================放弃删除房屋信息===================");
            return;
        }
    }

    public void update() {
        System.out.println("==================修改房屋信息==================");
        System.out.println("请选择待修改房屋的编号(-1退出)：");
        int findId = Utility.readInt();
        if (findId == -1) {
            System.out.println("==================放弃修改房屋信息===================");
            return;
        }
        House house = housesService.findById(findId);
        if (house == null)
            System.out.println("==================该房屋信息不存在===================");
        else {
            System.out.print("姓名(" + house.getName() + ")：");
            String name = Utility.readString(8, "");
            if (!"".equals(name))
                house.setName(name);

            System.out.print("电话(" + house.getPhoneNumber() + ")：");
            String phone = Utility.readString(12, "");
            if (!"".equals(phone))
                house.setPhoneNumber(phone);

            System.out.print("地址：");
            String address = Utility.readString(16, "");
            if (!"".equals(address))
                house.setAddress(address);

            System.out.print("月租：");
            int rent = Utility.readInt(-1);
            if (rent != -1)
                house.setRent(rent);

            System.out.print("状态：");
            String state = Utility.readString(3, "");
            if (!"".equals(state))
                house.setState(state);

            System.out.println("==================房屋信息修改成功===================");
        }

    }

    public void listHouses() {
        System.out.println("====================房屋列表=====================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = housesService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null)
                break;
            System.out.println(houses[i]);
        }
        System.out.println("=================房屋列表显示完毕=================");
    }

    public void exit() {
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y')
            loop = false;
    }

    public void mainMenu() {
        do {
            System.out.println("=================房屋出租系统菜单=================");
            System.out.println("\t\t\t1.  新 增 房 源");
            System.out.println("\t\t\t2.  查 找 房 屋");
            System.out.println("\t\t\t3.  删 除 房 屋");
            System.out.println("\t\t\t4.  修改房屋信息");
            System.out.println("\t\t\t5.  房 屋 列 表");
            System.out.println("\t\t\t6.  退      出");
            System.out.print("请输入你的选择（1～6）");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    deleteHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);

    }
}
