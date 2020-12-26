package com.mengld.service;

import com.mengld.domain.*;

import static com.mengld.service.Data.*;

/**
 * 将Data中的数据封装到Employee[]数组中，同时提供相关操作方法
 */
public class NameListService {

    private Employee[] employees;

    /**
     * 给employee及数组进行初始化
     */
    public NameListService(){

        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < employees.length; i ++) {
            // 获取员工类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            // 获取employee的4个基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    /**
     * 获取指定index上的员工的设备
     * @param index
     * @return
     */
    private Equipment createEquipment(int index) {

        int type = Integer.parseInt(EQIPMENTS[index][0]);
        String model = EQIPMENTS[index][1];
        switch (type) {
            case PC:
                String display = EQIPMENTS[index][2];
                return new PC(model, display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQIPMENTS[index][2]);
                return new NoteBook(model, price);
            case PRINTER:
                String key = EQIPMENTS[index][2];
                return new Printer(model, key);
        }
        return null;
    }

    /**
     * 获取当前所有员工
     * @return
     */
    public Employee[] getAllEmployees() {
        return employees;
    }

    /**
     * 获取指定id员工
     * @param id
     * @return
     */
    public Employee getEmployee(int id) throws TeamException {

        for (int i = 0; i < employees.length; i ++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }

        throw new TeamException("找不到指定员工");
    }
}
