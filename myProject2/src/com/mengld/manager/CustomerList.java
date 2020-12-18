package com.mengld.manager;

/**
 * CustomerList为Customer对象的管理模块，内部使用数组管理一组Customer对象
 */
public class CustomerList {

    private Customer[] customers;   // 用来保存客户对象的数组
    private int total;      //  记录已保存客户对象的数量

    /**
     * 构造器，用来初始化customers数组
     * @param totalCustomer totalCustomer：指定customers数组的最大空间
     */
    public CustomerList(int totalCustomer) {

        customers = new Customer[totalCustomer];
    }

    /**
     * 将参数customer添加到数组中最后一个客户对象记录之后
     * @param customer (customer指定要添加的客户对象 )
     * @return 添加成功返回true；false表示数组已满，无法添加
     */
    public boolean addCustomer(Customer customer) {

        if (total == customers.length)
            return false;

        customers[total++] = customer;
        return true;
    }

    /**
     * 用参数customer替换数组中由index指定的对象
     * @param index (index指定所替换对象在数组中的位置，从0开始)
     * @param cust (指定替换的新客户对象)
     * @return 替换成功返回true；false表示索引无效，无法替换
     */
    public boolean replaceCustomer(int index, Customer cust) {

        if (index < 0 || index >= total)
            return false;

        customers[index] = cust;
        return true;
    }

    /**
     * 从数组中删除参数index指定索引位置的客户对象记录
     * @param index (index指定所删除对象在数组中的索引位置，从0开始)
     * @return 删除成功返回true；false表示索引无效，无法删除
     */
    public boolean deleteCustomer(int index) {

        if (index < 0 || index >= total)
            return false;

        Customer[] cs = new Customer[--total];

        for (int i = index; i < total; i ++) {
            customers[i] = customers[i + 1];
        }

        for (int i = 0; i < total; i ++) {
            cs[i] = customers[i];
        }

        customers = cs;
        cs = null;

        return true;
    }

    /**
     * 返回数组中记录的所有客户对象
     * @return Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同
     */
    public Customer[] getAllCustomers() {

        if (total == 0)
            return null;

        return customers;
    }

    /**
     * 返回参数index指定索引位置的客户对象记录
     * @param index (index指定所要获取的客户在数组中的索引位置，从0开始)
     * @return 封装了客户信息的Customer对象
     */
    public Customer getCustomer(int index) {

        if (index < 0 || index >= total)
            return null;

        return customers[index];
    }

    /**
     * 返回记录的总客户数
     * @return 当前保存的总客户数
     */
    public int getTotal() {

        return total;
    }

    // test
    public static void main(String[] args) {

        CustomerList cl = new CustomerList(5);
//        cl.addCustomer(new Customer("张三", '男', 15, "123456", "zhangsan@qq.com"));
//        cl.addCustomer(new Customer("李四", '女', 16, "423412", "lisi@qq.com"));
//        cl.addCustomer(new Customer("王五", '男', 17, "371822", "wangwu@qq.com"));
//        cl.addCustomer(new Customer("赵六", '女', 18, "768698", "zhaoliu@qq.com"));
//        cl.addCustomer(new Customer("刘七", '男', 19, "654321", "liuqi@qq.com"));

//        cl.deleteCustomer(0);
//        cl.replaceCustomer(1, new Customer("王五", '男', 17, "371822", "wangwu@qq.com"));

//        cl.deleteCustomer(2);
//        cl.getCustomer(4);
//        for (Customer customer : cl.getAllCustomers()) {
//            System.out.println(customer);
//        }
//      add test ok


//        cl.replaceCustomer(2, new Customer("jobs", '男', 54, "000000", "jobs@icloud.com"));
//        for (Customer customer : cl.getAllCustomers()) {
//            System.out.println(customer);
//        }
//        replace test ok

//        cl.deleteCustomer(3);
//        for (Customer customer : cl.getAllCustomers()) {
//            System.out.println(customer);
//        }
//        delete test ok

//        System.out.println(cl.getCustomer(1));
//        getCustomer test ok

//        System.out.println(cl.getTotal());
//        getTotal test ok
    }
}
