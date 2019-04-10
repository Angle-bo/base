package org.huluobo.mpxj.demo_other;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Relation;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;

public class ReadMain {

	public static void main(String[] args) {
		readFile();
	}

	public static List<TaskInfo> readFile() {

		List<TaskInfo> taskList = new ArrayList<TaskInfo>();
		try {
			File file = new File("D:\\360极速浏览器下载\\开办新公司.mpp");
			MPPReader mppRead = new MPPReader();
			ProjectFile pf = mppRead.read(file);
			System.out.println("MPXJUtils.method [readFile]: fileName-" + file.getName());

			List<Task> tasks = pf.getTasks();
			System.out.println("MPXJUtils.method [readFile]: taskSize-" + tasks.size());

			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.get(i);

				Integer task_id = task.getID();
				Integer task_unique_id = task.getUniqueID();
				Integer task_outline_level = task.getOutlineLevel();
				double task_duration = task.getDuration().getDuration();
				Date task_start_date = task.getStart();
				Date task_finish_date = task.getFinish();
				List<Relation> task_predecessors = task.getPredecessors();
				  System.out.println("MPXJUtils.method [readFile] taskInfo:" + task_id + "|" + task_unique_id + "|"
						+ task_outline_level + "|" + task_duration + "|" + task_start_date + "|" + task_finish_date
						+ "|" + task_predecessors);

				// 封装TaskInfo
				//java.sql.Date sqlStartDate = Str2Date.getUKDate(task_start_date.toString()); // StartDate转换
				//java.sql.Date sqlFinishDate = Str2Date.getUKDate(task_finish_date.toString()); // FinishDate转换
				StringBuffer sb = new StringBuffer();
				if (task_predecessors != null) {
					if (task_predecessors.size() > 0) {
						for (Relation relation : task_predecessors) {
							Integer targetTaskId = relation.getTargetTask().getID();
							if (sb.length() == 0) {
								sb.append(targetTaskId);
							} else {
								sb.append("," + targetTaskId);
							}
						}
					}
				}
				String task_predecessors_str = sb.toString(); // 任务流文本

				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setTask_id(task_id);
				taskInfo.setTask_unique_id(task_unique_id);
				taskInfo.setTask_outline_level(task_outline_level);
				taskInfo.setTask_duration(task_duration);
				taskInfo.setTask_start_date(task_start_date);
				taskInfo.setTask_finish_date(task_finish_date);
				taskInfo.setTask_predecessors(task_predecessors_str);

				taskList.add(taskInfo);
			}
		} catch (MPXJException e) {
			 System.out.println("MPXJUtils.method [readFile]: MPXJException-" + e);
			return null;
		} catch (Exception e) {
			 System.out.println("MPXJUtils.method [readFile]: MPXJException-" + e);
			return null;
		}
		return taskList;
	}
}
