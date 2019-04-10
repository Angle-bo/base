package org.huluobo.mpxj.demo_other;

import java.util.Date;

public class TaskInfo {
	private int project_id; // 所属项目ID
	private int task_id; // 任务ID
	private int task_unique_id; // 任务唯一ID
	private int parent_id; // 父任务ID
	private int task_outline_level; // 任务级别
	private String task_name; // 任务名称
	private double task_duration; // 任务工期
	private Date task_start_date; // 任务开始时间
	private Date task_finish_date; // 任务结束时间
	private String task_predecessors; // 任务流
	private String task_operator; // 负责人

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getTask_unique_id() {
		return task_unique_id;
	}

	public void setTask_unique_id(int task_unique_id) {
		this.task_unique_id = task_unique_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getTask_outline_level() {
		return task_outline_level;
	}

	public void setTask_outline_level(int task_outline_level) {
		this.task_outline_level = task_outline_level;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public double getTask_duration() {
		return task_duration;
	}

	public void setTask_duration(double task_duration) {
		this.task_duration = task_duration;
	}

	public Date getTask_start_date() {
		return task_start_date;
	}

	public void setTask_start_date(Date task_start_date) {
		this.task_start_date = task_start_date;
	}

	public Date getTask_finish_date() {
		return task_finish_date;
	}

	public void setTask_finish_date(Date task_finish_date) {
		this.task_finish_date = task_finish_date;
	}

	public String getTask_predecessors() {
		return task_predecessors;
	}

	public void setTask_predecessors(String task_predecessors) {
		this.task_predecessors = task_predecessors;
	}

	public String getTask_operator() {
		return task_operator;
	}

	public void setTask_operator(String task_operator) {
		this.task_operator = task_operator;
	}

}
