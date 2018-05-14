package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import com.kpfu.itis.khakov.multiremark.repository.task.TaskRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class TaskService {

	private final TaskRepository taskRepository;

	private final WorkTypeRepository workTypeRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository, WorkTypeRepository workTypeRepository) {
		this.taskRepository = taskRepository;
		this.workTypeRepository = workTypeRepository;
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		prepareTasks(tasks);
		return tasks;
	}

	public List<Task> getTasksByUser() {
		return null;
	}

	public Task getTaskByIdResponse(Long taskId) {
		Task t = getTaskById(taskId);
		if (t != null) {
			prepareTask(t);
		}
		return t;
	}

	public void prepareTasks(List<Task> tasks) {
		tasks.forEach(this::prepareTask);
	}

	private void prepareTask(final Task t) {
		if (t.getWorkType() != null && t.getWorkType().getStages() != null) {
			t.getWorkType().getStages().forEach(s -> {
				s.setWorkTypes(null);
				s.setStages(null);
				s.setTasks(null);
			});
		}
		if (t.getQuestions() != null) {
			t.getQuestions().forEach(q -> {
				q.setTasks(null);
				if (q.getAnswers() != null) {
					q.getAnswers().forEach(a -> {
						a.setWorks(null);
						a.setQuestions(null);
					});
				}
			});
		}
		t.setTeacher(null);
		if (t.getStages() != null) {
			t.getStages().forEach(s -> {
				s.setWorkTypes(null);
				s.setStages(null);
				s.setTasks(null);
			});
		}
		if (t.getWorkType() != null) {
			t.getWorkType().setTasks(null);
		}
		t.setWorks(null);
		/*if (t.getWorks() != null) {
			t.getWorks().forEach(w -> {
				w.setTask(null);
				w.setWorkAnswer(null);
				w.setAnswer(null);
				w.setStudent(null);
				w.setNextWork(null);
				w.setPreviousWork(null);
				w.setWorkStages(null);
			});
		}*/
	}

	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElse(null);
	}

	public Task createTask(Task task) {
		WorkType workType;
		switch (task.getWorkType().getType()) {
			case CODE:
			case TEST:
			case PROJECT:
			case TEXT:
				workType = workTypeRepository.findFirstByType(task.getWorkType().getType());
				break;
			default:
				workType = task.getWorkType();
				break;
		}
		if (workType != null) {
			task.setWorkType(workType);
		}
		task = taskRepository.save(task);
		prepareTask(task);
		return task;
	}
}
