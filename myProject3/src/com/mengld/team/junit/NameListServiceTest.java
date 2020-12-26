package com.mengld.team.junit;

import com.mengld.domain.Employee;
import com.mengld.service.NameListService;
import com.mengld.service.TeamException;
import org.junit.Test;

/**
 * 对NameListService类进行测试
 */
public class NameListServiceTest {

    @Test
    public void testGetAllEmployees() {
        NameListService service = new NameListService();
        Employee[] allEmployees = service.getAllEmployees();
        for (int i = 0; i < allEmployees.length; i++) {

            System.out.println(allEmployees[i]);
        }
    }

    @Test
    public void testGetEmployee() {
        NameListService service = new NameListService();
        try {
            Employee employee = service.getEmployee(1);
            System.out.println(employee);
        } catch (TeamException e) {
            e.printStackTrace();
        }
    }
}
