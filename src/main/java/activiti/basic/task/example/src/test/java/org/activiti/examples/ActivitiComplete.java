package activiti.basic.task.example.src.test.java.org.activiti.examples;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 完成任务
 * @author GuKang 2022/08/04
 * */
public class ActivitiComplete {
    public static void main(String []args){

    }

    /**
     * @description 查询代办任务
     * @author GuKang 2022/08/04
     * */
    public static List<String> searchTask(ProcessEngine processEngine,String key,String userId){
        // 流程启动后，各各任务的负责人就可以查询自己当前需要处理的任务，查询出来的任务都是该用户的待办任务
        TaskService taskService=processEngine.getTaskService();
        List<Task> list=taskService.createTaskQuery().processDefinitionKey(key)
                .taskAssignee(userId)//查询谁的任务
                .list();//流程实例key
        List<String> idList=new ArrayList<String>();
        for(Task task:list){
            idList.add(task.getId());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getProcessInstanceId());
        }
        return idList;
    }
}
