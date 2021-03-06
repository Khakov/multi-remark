package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.StageStatus;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkAnswer;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import com.kpfu.itis.khakov.multiremark.repository.work.ReviewRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
@Service
public class WorkService {
	private final WorkRepository workRepository;
	private final WorkStageRepository workStageRepository;
	private final ReviewRepository reviewRepository;


	@Autowired
	public WorkService(WorkRepository workRepository, WorkStageRepository workStageRepository, ReviewRepository reviewRepository) {
		this.workRepository = workRepository;
		this.workStageRepository = workStageRepository;
		this.reviewRepository = reviewRepository;
	}

	public void createWork(Student student, Task task, List<Answer> answers, String text) {
		Work work = new Work();
		if (task.getStages() != null) {
			task.getStages().forEach(s -> {
				WorkStage stage = new WorkStage();
				stage.setStageStatus(StageStatus.CREATE);
				s.addWorkStage(stage);
				work.addWorkStage(stage);
			});
		}
		if (task.getWorkType() != null && task.getWorkType().getStages() != null) {
			task.getWorkType().getStages().forEach(s -> {
				WorkStage stage = new WorkStage();
				stage.setStageStatus(StageStatus.CREATE);
				s.addWorkStage(stage);
				work.addWorkStage(stage);
			});
		}
		work.setTask(task);
		work.setStudent(student);
		if (answers != null) {
			work.setAnswers(answers);
		} else {
			work.setWorkAnswer(new WorkAnswer(text));
		}
		workRepository.save(work);
	}

	public List<Work> getWorksByStudent(Student student) {
		return workRepository.findAll().stream().filter(w -> student.equals(w.getStudent())).collect(Collectors.toList());
	}

	public List<Work> getWorksByStudentResponse(Student student) {
		List<Work> works = getWorksByStudent(student);
		works.forEach(w -> {
			w.setWorkStages(null);
			w.setTask(null);
			w.setPreviousWork(null);
			w.setNextWork(null);
			w.setAnswers(null);
			w.setWorkAnswer(null);
			w.setStudent(null);
		});
		return works;
	}

	public List<Work> getWorkByTaskAndStudent(Task task, Student student) {
		return workRepository.findAll().stream().filter(w -> task.equals(w.getTask()) && student.equals(w.getStudent())).collect(Collectors.toList());
	}

	public List<Work> getWorkByTaskAndStudentResponse(Task task, Student student) {
		List<Work> works = getWorkByTaskAndStudent(task, student);
		works.forEach(this::prepareWork);
		return works;
	}

	private void prepareWork(Work w) {
		w.setStudent(null);
		if (w.getTask() != null) {
			final Task task = w.getTask();
			task.setWorks(null);
			task.setTeacher(null);
			task.setQuestions(null);
			task.setWorkType(null);
			task.setStages(null);
			task.setStandarts(null);
		}
		if (w.getWorkAnswer() != null) {
			w.getWorkAnswer().setWork(null);
		}
		w.setNextWork(null);
		w.setPreviousWork(null);
		if (w.getAnswers() != null) {
			w.getAnswers().forEach(a -> {
				a.setWorks(null);
				a.getQuestions().stream().forEach(q -> {
					q.setTasks(null);
					q.setAnswers(null);
				});
			});

		}
		if (w.getWorkStages() != null) {
			w.getWorkStages().forEach(s -> {
				s = workStageRepository.findById(s.getId()).orElse(s);
				s.setWork(null);
				s.getStage().setWorkTypes(null);
				s.getStage().setStages(null);
				s.getStage().setTasks(null);
				if (s.getReview() != null) {
					s.setReview(reviewRepository.findById(s.getReview().getId()).orElse(s.getReview()));
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
			if (!student.equals(work.getStudent())) {
				return null;
			}
			prepareWork(work);
		}
		return work;
	}

	public void saveWork(Work work) {
		Task task = work.getTask();
		if (task.getStages() != null) {
			task.getStages().forEach(s -> {
				WorkStage stage = new WorkStage();
				stage.setStageStatus(StageStatus.CREATE);
				s.addWorkStage(stage);
				work.addWorkStage(stage);
			});
		}

		workRepository.save(work);
	}

	public Work getWorkById(Long workId) {
		return workRepository.findById(workId).orElse(null);
	}

	public Work getWorkByIdResponse(Long workId) {
		Work work = workRepository.findById(workId).orElse(null);
		if (work != null) {
			prepareWork(work);
		}
		return work;
	}

	public void analyzeCode(Long studentId, Long workId) {
		try {
			URL url = new URL("http://localhost:9000/api/duplications/show?key=test:test:" + studentId + "/" + workId + ".java");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);

			int code = connection.getResponseCode();

			if (code == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), "utf8"));
				String answer = "";
				String line = null;

				while ((line = reader.readLine()) != null) {
					answer += line;
				}
				reader.close();
				System.out.println(answer);
			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
