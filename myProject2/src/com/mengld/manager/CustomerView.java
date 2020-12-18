package com.mengld.manager;

/**
 * 主模块，负责菜单的显示和处理用户操作
 */
public class CustomerView {

    private CustomerList customerList = new CustomerList(10);

    /**
     * 显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理
     */
    public void enterMainMenu() {

        boolean isExit = true;
        while (isExit) {
            System.out.println("----------客户信息管理软件----------\n");
            System.out.println("----------1 添加客户----------");
            System.out.println("----------2 修改客户----------");
            System.out.println("----------3 删除客户----------");
            System.out.println("----------4 客户列表----------");
            System.out.println("----------5 退   出----------\n");

            System.out.print("          请选择(1-5): ");

            char selection = CMUtility.readMenuSelection();

            switch (selection) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.println("是否确认退出？");
                    char c = CMUtility.readConfirmSelection();
                    if (c == 'Y')
                        isExit = false;
            }
        }
    }

    /**
     * 添加客户
     */
    private void addNewCustomer() {

        System.out.println("----------添加客户----------");

        System.out.println("请输入姓名：");
        String name = CMUtility.readString(10);
        System.out.println("请输入性别");
        char gender = CMUtility.readChar();
        System.out.println("请输入年龄");
        int age = CMUtility.readInt();
        System.out.println("请输入电话");
        String phone = CMUtility.readString(12);
        System.out.println("请输入电子邮箱");
        String email = CMUtility.readString(12);

        boolean isAdd = customerList.addCustomer(new Customer(name, gender, age, phone, email));
        if (isAdd) {
            System.out.println("----------添加完成----------");
        }
        else {
            System.out.println("----------添加失败----------");
        }
    }

    /**
     * 修改客户
     */
    private void modifyCustomer() {

        System.out.println("----------修改客户----------");
        System.out.println("请选择待修改客户编号(-1退出)");
        int i = CMUtility.readInt(-1);

        Customer customer = customerList.getCustomer(i);
        if (i != -1 && customer != null) {

            System.out.println("姓名(" + customer.getName() + "): ");
            customer.setName(CMUtility.readString(10, customer.getName()));
            System.out.println("性别(" + customer.getGender() + "): ");
            customer.setGender(CMUtility.readChar(customer.getGender()));
            System.out.println("年龄(" + customer.getAge() + "): ");
            customer.setAge(CMUtility.readInt(customer.getAge()));
            System.out.println("电话(" + customer.getPhone() + "): ");
            customer.setPhone(CMUtility.readString(12, customer.getPhone()));
            System.out.println("邮箱(" + customer.getEmail() + "): ");
            customer.setEmail(CMUtility.readString(12, customer.getEmail()));
            System.out.println("----------修改完成----------");
        }
        else if (i == -1){
            System.out.println("取消修改");
        }
        else {
            System.out.println("客户编号错误,请重新输入");
            modifyCustomer();
        }
    }

    /**
     * 删除客户
     */
    private void deleteCustomer() {

        System.out.println("----------删除客户----------");
        System.out.println("请选择待删除客户编号(-1退出)：");
        int i = CMUtility.readInt(-1);
        Customer customer = customerList.getCustomer(i);
        if (i != -1 && customer != null) {
            System.out.println("是否确认删除(Y/N)");
            if (CMUtility.readConfirmSelection() == 'Y') {
                boolean isDelete = customerList.deleteCustomer(i);
                if (isDelete)
                    System.out.println("----------删除成功----------");
                else
                    System.out.println("----------删除失败----------");
            }
            else
                System.out.println("取消删除");
        }
        else if (i == -1) {
            System.out.println("退出删除");
        }
        else {
            System.out.println("客户编号错误,请重新输入");
            deleteCustomer();
        }
    }

    /**
     * 客户列表
     */
    private void listAllCustomers() {

        System.out.println("----------客户列表----------");
        Customer[] allCustomers = customerList.getAllCustomers();
        System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
        int index = 0;
        for (int i = 0; i < customerList.getTotal(); i ++) {
            System.out.println(index + "\t" + customerList.getCustomer(i));
            index++;
        }
    }

    /**
     * 创建CustomerView实例，并调用 enterMainMenu()方法以执行程序
     * @param args
     */
    public static void main(String[] args) {
        CustomerView cv = new CustomerView();
        cv.enterMainMenu();
    }

}
