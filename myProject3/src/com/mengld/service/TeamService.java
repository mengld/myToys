package com.mengld.service;

import com.mengld.domain.Architect;
import com.mengld.domain.Designer;
import com.mengld.domain.Employee;
import com.mengld.domain.Programmer;

import static com.mengld.service.Status.BUSY;
import static com.mengld.service.Status.VACATION;

/**
 * 关于开发团队成员的管理：添加、删除等
 */
public class TeamService {

    private static int counter = 1; //给memberId赋值
    private int MAX_MEMBER = 5; //限制开发团队人数
    private Programmer[] team = new Programmer[MAX_MEMBER]; //保存开发团队成员
    private int total;  //记录开发团队中实际人数

    public TeamService() {
        super();
    }

    /**
     * 获取开发团队中所有成员
     * @return
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i ++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * 将指定员工添加到开发团队中
     * @param e
     */
    public void addMember(Employee e) throws TeamException {

        // 成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        // 该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        // 该员工已在本开发团队中
        if(isExit(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
        // 该员工已是某团队成员
        Programmer p = (Programmer)e;
        if (BUSY == p.getStatus()) {
            throw new TeamException("该员工已是某团队成员");
        }

        // 该员工正在休假，无法添加
        else if (VACATION == p.getStatus()) {
            throw new TeamException("该员工正在休假，无法添加");
        }

        //获取team已有成员中架构师、设计师、程序员的人数
        int numOfArch = 0, numOfDes = 0, nuOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numOfArch++;
            }
            else if (team[i] instanceof Designer) {
                numOfDes++;
            }
            else {
                nuOfPro++;
            }
        }
        // 团队中至多只能有一名架构师
        if (p instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        }
        // 团队中至多只能有两名设计师
        else if (p instanceof Designer) {
            if (numOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        }
        // 团队中至多只能有三名程序员
        else if (p instanceof Programmer){
            if (nuOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        team[total++] = p;
        p.setStatus(BUSY);
        p.setMemberId(counter++);
    }

    private boolean isExit(Employee e) {

        for (int i = 0; i < total; i ++) {
            if (team[i].getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从团队中删除成员
     * @param memberId
     */
    public void removeMember(int memberId) throws TeamException {

        int i = 0;
        for (; i < total; i ++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        if (i == total)
            throw new TeamException("找不到指定memberId的员工，删除失败");

        for (int j = i + 1; j < total; j ++) {
            team[j - 1] = team[j];
        }

        team[total - 1] = null;
        total --;


    }
}
