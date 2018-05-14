package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.relationships.QuestionAnswerRelationship;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkAnswer;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class WorkService {
	private final WorkRepository workRepository;

	@Autowired
	public WorkService(WorkRepository workRepository) {
		this.workRepository = workRepository;
	}

	public void createWork(Student student, Task task, Answer answer, String text) {
		Work work = new Work();
		work.setTask(task);
		work.setStudent(student);
		if (answer != null) {
			work.setAnswer(answer);
		} else {
			work.setWorkAnswer(new WorkAnswer(text));
		}
		workRepository.save(work);
	}

	public List<Work> getWorksByStudent(Student student) {
		return workRepository.findAllByStudent(student);
	}

	public List<Work> getWorksByStudentResponse(Student student) {
		List<Work> works = getWorksByStudent(student);
		works.forEach(w -> {
			w.setWorkStages(null);
			w.setTask(null);
			w.setPreviousWork(null);
			w.setNextWork(null);
			w.setAnswer(null);
			w.setWorkAnswer(null);
			w.setStudent(null);
		});
		return works;
	}

	public List<Work> getWorkByTaskAndStudent(Task task, Student student) {
		return workRepository.findAllByTaskAndStudent(task, student);
	}

	public List<Work> getWorkByTaskAndStudentResponse(Task task, Student student) {
		List<Work> works = getWorkByTaskAndStudent(task, student);
		works.forEach(w -> {

		});
		return works;
	}

	private void prepareWork(Work w) {
		w.setStudent(null);
		w.setTask(null);
		if (w.getWorkAnswer() != null) {
			w.getWorkAnswer().setWork(null);
		}
		w.setNextWork(null);
		w.setPreviousWork(null);
		if (w.getAnswer() != null) {
			w.getAnswer().setWorks(null);
			QuestionAnswerRelationship question = w.getAnswer().getQuestions().stream().findFirst().orElse(null);
			if (question != null) {
				question.setAnswer(null);
				Question q = question.getQuestion();
				q.setTasks(null);
				q.setAnswers(null);
			}
		}
		if (w.getWorkStages() != null) {
			w.getWorkStages().forEach(s -> {
				s.setWork(null);
				s.getStage().setWorkTypes(null);
				s.getStage().setStages(null);
				s.getStage().setTasks(null);
				if (s.getReview() != null) {
					s.getReview().setStudent(null);
					s.getReview().setTeacher(null);
					s.getReview().setWorkStage(null);
					if (s.getReview().getComments() != null) {
						s.getReview().getComments().forEach(c -> {
							c.setReview(null);
						});
					}
				}
			});
		}
	}

	public Work getWorkByStudentAndworkIdResponse(Long id, Student student) {
		Work work = workRepository.findById(id).orElse(null);
		if (work != null) {
			prepareWork(work);
		}
		return work;
	}
}
